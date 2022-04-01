package com.intershop.oms.test.servicehandler.rmaservice.v2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intershop.oms.rest.rma.v2.api.ShopApi;
import com.intershop.oms.rest.rma.v2.model.ReadReturnRequest;
import com.intershop.oms.rest.rma.v2.model.ReadReturnRequestPosition;
import com.intershop.oms.rest.rma.v2.model.WriteReturnRequest;
import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.rest.shared.ApiResponse;
import com.intershop.oms.test.businessobject.order.OMSOrder;
import com.intershop.oms.test.businessobject.rma.OMSReadReturnRequest;
import com.intershop.oms.test.businessobject.rma.OMSReadReturnRequestPosition;
import com.intershop.oms.test.businessobject.rma.OMSReturnableData;
import com.intershop.oms.test.businessobject.rma.OMSShopReturnReason;
import com.intershop.oms.test.businessobject.rma.OMSWriteReturnRequest;
import com.intershop.oms.test.businessobject.rma.OMSWriteReturnRequestPosition;
import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.RESTServiceHandler;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;
import com.intershop.oms.test.servicehandler.rmaservice.v2.mapping.ReadReturnRequestMapper;
import com.intershop.oms.test.servicehandler.rmaservice.v2.mapping.ReadReturnRequestPositionMapper;
import com.intershop.oms.test.servicehandler.rmaservice.v2.mapping.WriteReturnRequestMapper;

public class OMSReturnRequestServiceHandlerV2 extends RESTServiceHandler
                implements com.intershop.oms.test.servicehandler.rmaservice.OMSReturnRequestServiceHandler
{
    private static final Logger log = LoggerFactory.getLogger(OMSReturnRequestServiceHandlerV2.class);
    private final ShopApi shopApi;
    private final OMSDbHandler dbHandler;

    OMSReturnRequestServiceHandlerV2(ServiceConfiguration serviceConfiguration, OMSDbHandler dbHandler)
    {
        super(serviceConfiguration, "/rest/rma", log);
        this.shopApi = new ShopApi(apiClient);
        this.dbHandler = dbHandler;
    }

    @Override
    public String sendReturnRequest(String shopName, String shopOrderNo,
                    Collection<OMSWriteReturnRequestPosition> returnRequestPositions) throws ApiException
    {
        OMSWriteReturnRequest omsReturnRequest = new OMSWriteReturnRequest(shopOrderNo, returnRequestPositions);
        ApiResponse<Void> response;

        WriteReturnRequest returnRequest = WriteReturnRequestMapper.INSTANCE.toApiWriteReturnRequest(omsReturnRequest);

        response = shopApi.createReturnRequestWithHttpInfo(shopOrderNo, shopName, returnRequest);
        return response.getHeaders().get("Location").get(0);
    }

    @Override
    public String sendFullReturnRequest(OMSOrder order) throws ApiException
    {
        log.info("Sending full return request for: " + order.toString());
        Collection<OMSWriteReturnRequestPosition> returnRequestPositions = dbHandler
                        .getReturnRequestPositionsForOrder(order);
        return sendReturnRequest(order.getShopName(), order.getShopOrderNumber(), returnRequestPositions);
    }

    @Override
    public List<OMSReadReturnRequest> getReturnRequests(OMSOrder order) throws ApiException
    {
        List<OMSReadReturnRequest> omsReturnRequests = new ArrayList<>();

        List<ReadReturnRequest> returnRequests = shopApi.getReturnRequests(order.getShopOrderNumber(),
                        order.getShopName());
        ReadReturnRequestMapper.INSTANCE.fromApiReadReturnRequestList(returnRequests, omsReturnRequests);
        return omsReturnRequests;
    }

    @Override
    public List<OMSReadReturnRequestPosition> getReturnRequestPositions(OMSOrder order, Long returnRequestId)
                    throws ApiException
    {
        List<OMSReadReturnRequestPosition> omsReturnRequestPositions = new ArrayList<>();

        try
        {
            List<ReadReturnRequestPosition> returnRequestPositions = shopApi.getReturnRequestPositions(returnRequestId,
                            order.getShopOrderNumber(), order.getShopName());

            ReadReturnRequestPositionMapper.INSTANCE.fromApiReadReturnRequestPositionList(returnRequestPositions,
                            omsReturnRequestPositions);
        }
        catch(ApiException e)
        {
            log.error("Request failed with status " + e.getCode() + " and response body '" + e.getResponseBody() + "'",
                            e);
            throw e;
        }
        return omsReturnRequestPositions;
    }

    @Override
    public OMSReturnableData getReturnableData(OMSOrder order) throws ApiException
    {
        throw new ApiException("getReturnableData(OMSOrder order) not implemented before v2.10");
    }

    @Override
    public List<OMSShopReturnReason> getShop2ReturnReasons(String shopName, List<String> returnTypes)
                    throws ApiException
    {
        throw new ApiException(
                        "getShop2ReturnReasons(String shopName, List<String> returnTypes) not implemented before v2.10");
    }

    @Override
    protected Collection<Object> unwrapApiClient()
    {
        return Set.of(shopApi);
    }
}
