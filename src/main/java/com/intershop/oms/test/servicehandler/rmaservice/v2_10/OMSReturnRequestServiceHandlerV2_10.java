package com.intershop.oms.test.servicehandler.rmaservice.v2_10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intershop.oms.rest.rma.v2_10.api.ShopApi;
import com.intershop.oms.rest.rma.v2_10.model.ReadReturnRequest;
import com.intershop.oms.rest.rma.v2_10.model.ReadReturnRequestPosition;
import com.intershop.oms.rest.rma.v2_10.model.ReturnableData;
import com.intershop.oms.rest.rma.v2_10.model.ShopReturnReason;
import com.intershop.oms.rest.rma.v2_10.model.WriteReturnRequest;
import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.rest.shared.ApiResponse;
import com.intershop.oms.test.businessobject.order.OMSOrder;
import com.intershop.oms.test.businessobject.rma.OMSCustomAttribute;
import com.intershop.oms.test.businessobject.rma.OMSReadReturnRequest;
import com.intershop.oms.test.businessobject.rma.OMSReadReturnRequestPosition;
import com.intershop.oms.test.businessobject.rma.OMSReturnRequest;
import com.intershop.oms.test.businessobject.rma.OMSReturnableData;
import com.intershop.oms.test.businessobject.rma.OMSShopReturnReason;
import com.intershop.oms.test.businessobject.rma.OMSWriteCustomAttribute;
import com.intershop.oms.test.businessobject.rma.OMSWriteReturnRequest;
import com.intershop.oms.test.businessobject.rma.OMSWriteReturnRequestPosition;
import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.RESTServiceHandler;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;
import com.intershop.oms.test.servicehandler.rmaservice.v2_10.mapping.ReadReturnRequestMapper;
import com.intershop.oms.test.servicehandler.rmaservice.v2_10.mapping.ReadReturnRequestPositionMapper;
import com.intershop.oms.test.servicehandler.rmaservice.v2_10.mapping.ReturnableDataMapper;
import com.intershop.oms.test.servicehandler.rmaservice.v2_10.mapping.ShopReturnReasonMapper;
import com.intershop.oms.test.servicehandler.rmaservice.v2_10.mapping.WriteReturnRequestMapper;

public class OMSReturnRequestServiceHandlerV2_10 extends RESTServiceHandler
                implements com.intershop.oms.test.servicehandler.rmaservice.OMSReturnRequestServiceHandler
{
    private static final Logger log = LoggerFactory.getLogger(OMSReturnRequestServiceHandlerV2_10.class);
    private final ShopApi shopApi;
    private final OMSDbHandler dbHandler;

    OMSReturnRequestServiceHandlerV2_10(ServiceConfiguration serviceConfiguration, OMSDbHandler dbHandler)
    {
        super(serviceConfiguration, "/rest/rma", log);
        shopApi = new ShopApi(apiClient);
        this.dbHandler = dbHandler;
    }

    @Override
    public String sendReturnRequest(String shopName, String shopOrderNo,
                    Collection<OMSWriteReturnRequestPosition> returnRequestPositions) throws ApiException
    {
        OMSWriteReturnRequest omsWriteReturnRequest = new OMSWriteReturnRequest(shopOrderNo, returnRequestPositions);

        ApiResponse<Void> response;

        WriteReturnRequest writeReturnRequest = WriteReturnRequestMapper.INSTANCE
                        .toApiWriteReturnRequest(omsWriteReturnRequest);

        response = shopApi.createReturnRequestWithHttpInfo(shopOrderNo, shopName, writeReturnRequest);
        return response.getHeaders().get(HTTP_HEADER_LOCATION).get(0);

    }

    @Override
    public String sendFullReturnRequest(OMSOrder order) throws ApiException
    {
        log.info("Sending full return request for: {}", order);
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
        catch (ApiException e)
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
        OMSReturnableData omsReturnableData;

        ReturnableData returnableData = shopApi.getReturnableData(order.getShopOrderNumber(), order.getShopName());
        omsReturnableData = ReturnableDataMapper.INSTANCE.fromApiReturnableData(returnableData);
        return omsReturnableData;
    }

    @Override
    public List<OMSShopReturnReason> getShop2ReturnReasons(String shopName, List<String> returnTypes)
                    throws ApiException
    {
        List<OMSShopReturnReason> omsShopReturnReasons = new ArrayList<>();

        List<ShopReturnReason> shopReturnReasons = shopApi.getShop2ReturnReasons(shopName, returnTypes);
        ShopReturnReasonMapper.INSTANCE.fromApiShopReturnReasonList(shopReturnReasons, omsShopReturnReasons);
        return omsShopReturnReasons;
    }

    @Override
    protected Collection<Object> unwrapApiClient()
    {
        return Set.of(shopApi);
    }

    @Override
    public OMSReturnRequest createReturnRequest(OMSReturnRequest returnRequest, Integer targetState)
    {
        throw new RuntimeException("not supported before V2_11");
    }

    @Override
    public OMSReturnRequest getReturnRequest(Long id) throws ApiException
    {
        throw new RuntimeException("not supported before V2_11");
    }

    @Override
    public OMSReturnRequest replaceReturnRequest(OMSReturnRequest returnRequest, Integer targetState)
                    throws ApiException
    {
        throw new RuntimeException("not supported before V2_12");
    }

    @Override
    public List<OMSCustomAttribute> getReturnRequestPositionCustomAttributes(OMSOrder order, Long returnRequestId,
                    Long returnRequestPositionId) throws ApiException
    {
        throw new RuntimeException("not supported before V2_12");
    }
    
    @Override
    public List<OMSCustomAttribute> createReturnRequestPositionCustomAttributes(OMSOrder order,
                    Long returnRequestId, Long returnRequestPositionId, List<OMSWriteCustomAttribute> customAttributes)
                    throws ApiException
    {
        throw new RuntimeException("not supported before V2_12");
    }
    
    @Override
    public List<OMSCustomAttribute> replaceReturnRequestPositionCustomAttributes(OMSOrder order,
                    Long returnRequestId, Long returnRequestPositionId, List<OMSWriteCustomAttribute> customAttributes)
                    throws ApiException
    {
        throw new RuntimeException("not supported before V2_12");
    }
    
    @Override
    public OMSCustomAttribute getReturnRequestPositionCustomAttribute(OMSOrder order, Long returnRequestId,
                    Long returnRequestPositionId, Long customAttributeId) throws ApiException
    {
        throw new RuntimeException("not supported before V2_12");
    }
    
    @Override
    public void deleteReturnRequestPositionCustomAttribute(OMSOrder order, Long returnRequestId,
                    Long returnRequestPositionId, Long customAttributeId) throws ApiException
    {
        throw new RuntimeException("not supported before V2_12");
    }

    @Override
    public List<OMSCustomAttribute> getReturnRequestCustomAttributes(OMSOrder order, Long returnRequestId)
                    throws ApiException
    {
        throw new RuntimeException("not supported before V2_12");
    }

    @Override
    public List<OMSCustomAttribute> createReturnRequestCustomAttributes(OMSOrder order, Long returnRequestId,
                    List<OMSWriteCustomAttribute> customAttributes) throws ApiException
    {
        throw new RuntimeException("not supported before V2_12");
    }

    @Override
    public List<OMSCustomAttribute> replaceReturnRequestCustomAttributes(OMSOrder order, Long returnRequestId,
                    List<OMSWriteCustomAttribute> customAttributes) throws ApiException
    {
        throw new RuntimeException("not supported before V2_12");
    }

    @Override
    public OMSCustomAttribute getReturnRequestCustomAttribute(OMSOrder order, Long returnRequestId,
                    Long returnRequestPropertyId) throws ApiException
    {
        throw new RuntimeException("not supported before V2_12");
    }

    @Override
    public void deleteReturnRequestCustomAttribute(OMSOrder order, Long returnRequestId, Long customAttributeId)
                    throws ApiException
    {
        throw new RuntimeException("not supported before V2_12");
    }
}
