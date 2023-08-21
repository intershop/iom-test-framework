package com.intershop.oms.test.servicehandler.rmaservice.v2_12;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intershop.oms.rest.rma.v2_12.api.ShopApi;
import com.intershop.oms.rest.rma.v2_12.model.ContactPerson;
import com.intershop.oms.rest.rma.v2_12.model.ReadCustomAttribute;
import com.intershop.oms.rest.rma.v2_12.model.ReadPickupAddress;
import com.intershop.oms.rest.rma.v2_12.model.ReadReturnRequest;
import com.intershop.oms.rest.rma.v2_12.model.ReadReturnRequestItem;
import com.intershop.oms.rest.rma.v2_12.model.ReadReturnRequestPosition;
import com.intershop.oms.rest.rma.v2_12.model.ReturnableData;
import com.intershop.oms.rest.rma.v2_12.model.ShopReturnReason;
import com.intershop.oms.rest.rma.v2_12.model.WriteCustomAttribute;
import com.intershop.oms.rest.rma.v2_12.model.WriteReturnRequest;
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
import com.intershop.oms.test.servicehandler.rmaservice.v2_12.mapping.CustomAttributeMapper;
import com.intershop.oms.test.servicehandler.rmaservice.v2_12.mapping.ReadReturnRequestMapper;
import com.intershop.oms.test.servicehandler.rmaservice.v2_12.mapping.ReadReturnRequestPositionMapper;
import com.intershop.oms.test.servicehandler.rmaservice.v2_12.mapping.ReturnRequestMapper;
import com.intershop.oms.test.servicehandler.rmaservice.v2_12.mapping.ReturnableDataMapper;
import com.intershop.oms.test.servicehandler.rmaservice.v2_12.mapping.ShopReturnReasonMapper;
import com.intershop.oms.test.servicehandler.rmaservice.v2_12.mapping.WriteReturnRequestMapper;

public class OMSReturnRequestServiceHandlerV2_12 extends RESTServiceHandler
                implements com.intershop.oms.test.servicehandler.rmaservice.OMSReturnRequestServiceHandler
{
    private static final Logger log = LoggerFactory.getLogger(OMSReturnRequestServiceHandlerV2_12.class);
    private final ShopApi shopApi;
    private final OMSDbHandler dbHandler;

    OMSReturnRequestServiceHandlerV2_12(ServiceConfiguration serviceConfiguration, OMSDbHandler dbHandler)
    {
        super(serviceConfiguration, "/rest/rma", log);
        this.shopApi = new ShopApi(apiClient);
        this.dbHandler = dbHandler;
    }

    @Override
    public String sendReturnRequest(String shopName, String shopOrderNo,
                    Collection<OMSWriteReturnRequestPosition> returnRequestPositions) throws ApiException
    {
        OMSWriteReturnRequest omsWriteReturnRequest = new OMSWriteReturnRequest(shopOrderNo, returnRequestPositions);

        WriteReturnRequest writeReturnRequest = WriteReturnRequestMapper.INSTANCE
                        .toApiWriteReturnRequest(omsWriteReturnRequest);

        ApiResponse<Void> response = shopApi.createReturnRequestWithHttpInfo(shopOrderNo, shopName, writeReturnRequest);
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
            log.error("Request failed with status " + e.getCode() + " and response body '" + e.getResponseBody() + "'", e);
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
    public OMSReturnRequest createReturnRequest(OMSReturnRequest returnRequest, Integer targetState) throws ApiException
    {
        WriteReturnRequest writeReturnRequest = ReturnRequestMapper.INSTANCE.toWriteReturnRequest(returnRequest);

        ApiResponse<Void> resp = shopApi.createReturnRequestWithHttpInfo(returnRequest.getShopOrderNumber(),
                        returnRequest.getShopName(), writeReturnRequest);
        Long id = parseIdFromLocation(resp.getHeaders().get(HTTP_HEADER_LOCATION).get(0));

        if (targetState != null)
        {
            dbHandler.waitForReturnAnnouncementState(id, targetState);
        }

        return getReturnRequest(id, returnRequest.getShopOrderNumber(), returnRequest.getShopName());
    }

    @Override
    public OMSReturnRequest replaceReturnRequest(OMSReturnRequest returnRequest, Integer targetState)
                    throws ApiException
    {
        WriteReturnRequest writeReturnRequest = ReturnRequestMapper.INSTANCE.toWriteReturnRequest(returnRequest);

        shopApi.replaceReturnRequest(returnRequest.getId(), returnRequest.getShopOrderNumber(), returnRequest.getShopName(), writeReturnRequest);

        if (targetState != null)
        {
            dbHandler.waitForReturnAnnouncementState(returnRequest.getId(), targetState);
        }

        return getReturnRequest(returnRequest.getId(), returnRequest.getShopOrderNumber(), returnRequest.getShopName());
    }
    
    @Override
    public OMSReturnRequest getReturnRequest(Long id) throws ApiException
    {
        // we can't reference an entity via it's unique id in the API because of
        // reasons... so this is a complete hack until we refactor the API
        String sql = """
                        select o."shopOrderNo", s."shopName"
                        from "OrderDO" o
                        inner join "ShopDO" s on o."shopRef" = s.id
                        inner join "ReturnAnnouncementDO" ra on ra."orderRef" = o.id
                        where ra.id = ?;
                        """;

        String shopOrderNo;
        String shopName;
        try (Connection con = dbHandler.getConnection(); PreparedStatement stmt = con.prepareStatement(sql))
        {
            stmt.setLong(1, id);
            stmt.execute();
            try (ResultSet rs = stmt.getResultSet())
            {
                if (rs.next())
                {
                    shopOrderNo = rs.getString(1);
                    shopName = rs.getString(2);
                }
                else
                {
                    throw new ApiException("id doesn't exist - " + id);
                }
            }
        }
        catch(SQLException e)
        {
            throw new ApiException(e);
        }

        return getReturnRequest(id, shopOrderNo, shopName);
    }

    private OMSReturnRequest getReturnRequest(Long id, String shopOrderNo, String shopName) throws ApiException
    {
        ReadReturnRequest rReq = shopApi.getReturnRequest(id, shopOrderNo, shopName);
        List<ReadReturnRequestPosition> returnRequestPositions = shopApi.getReturnRequestPositions(id, shopOrderNo,
                        shopName);
        Map<ReadReturnRequestPosition, List<ReadReturnRequestItem>> posToItems = new HashMap<>();

        for (ReadReturnRequestPosition pos : returnRequestPositions)
        {
            List<ReadReturnRequestItem> items = shopApi.getReturnRequestItems(pos.getId(), id, shopOrderNo, shopName);
            posToItems.put(pos, items);
        }

        List<ContactPerson> contactPersons = shopApi.getContactPersons(id, shopOrderNo, shopName);
        List<ReadCustomAttribute> customAttributes = shopApi.getCustomAttributes(id, shopOrderNo, shopName);
        List<ReadPickupAddress> pickupAddresses = shopApi.getReturnRequestPickupAddresses(id, shopOrderNo, shopName);
        return ReturnRequestMapper.INSTANCE.fromApiReturnRequest(rReq, posToItems, contactPersons, customAttributes,
                        pickupAddresses.isEmpty() ? null : pickupAddresses.get(0));
    }

    @Override
    protected Collection<Object> unwrapApiClient()
    {
        return Set.of(shopApi);
    }

    @Override
    public OMSCustomAttribute getReturnRequestPositionCustomAttribute(OMSOrder order, Long returnRequestId,
                    Long returnRequestPositionId, Long returnRequestPositionPropertyId) throws ApiException
    {
        ReadCustomAttribute response = shopApi.getReturnRequestPositionCustomAttribute(returnRequestPositionPropertyId, returnRequestPositionId, returnRequestId, order.getShopOrderNumber(), order.getShopName());
        
        return CustomAttributeMapper.INSTANCE.fromApiCustomAttribute(response);
    }

    @Override
    public List<OMSCustomAttribute> getReturnRequestPositionCustomAttributes(OMSOrder order, Long returnRequestId, Long returnRequestPositionId) throws ApiException
    {
        List<ReadCustomAttribute> response = shopApi.getReturnRequestPositionCustomAttributes(returnRequestPositionId, returnRequestId, order.getShopOrderNumber(), order.getShopName());
        
        return CustomAttributeMapper.INSTANCE.fromApiCustomAttributes(response);
    }

    @Override
    public List<OMSCustomAttribute> createReturnRequestPositionCustomAttributes(OMSOrder order, Long returnRequestId, Long returnRequestPositionId, List<OMSWriteCustomAttribute> customAttributes)
                    throws ApiException
    {
        List<WriteCustomAttribute> ca = CustomAttributeMapper.INSTANCE.toApiCustomAttributes(customAttributes);
        List<ReadCustomAttribute> response = shopApi.createPositionCustomAttributes(returnRequestPositionId, returnRequestId, order.getShopOrderNumber(), order.getShopName(), ca);
        return CustomAttributeMapper.INSTANCE.fromApiCustomAttributes(response);
    }

    @Override
    public List<OMSCustomAttribute> replaceReturnRequestPositionCustomAttributes(OMSOrder order,
                    Long returnRequestId, Long returnRequestPositionId, List<OMSWriteCustomAttribute> customAttributes)
                    throws ApiException
    {
        List<WriteCustomAttribute> ca = CustomAttributeMapper.INSTANCE.toApiCustomAttributes(customAttributes);
        List<ReadCustomAttribute> response = shopApi.replaceReturnRequestPositionCustomAttributes(returnRequestPositionId, returnRequestId, order.getShopOrderNumber(), order.getShopName(), ca);
        return CustomAttributeMapper.INSTANCE.fromApiCustomAttributes(response);
    }
    
    @Override
    public void deleteReturnRequestPositionCustomAttribute(OMSOrder order, Long returnRequestId,
                    Long returnRequestPositionId, Long customAttributeId) throws ApiException
    {
        shopApi.deletePositionCustomAttribute(customAttributeId, returnRequestPositionId, returnRequestId, order.getShopOrderNumber(), order.getShopName());
    }

    @Override
    public OMSCustomAttribute getReturnRequestCustomAttribute(OMSOrder order, Long returnRequestId, Long returnRequestPropertyId) throws ApiException
    {
        ReadCustomAttribute response = shopApi.getReturnRequestCustomAttribute(returnRequestPropertyId, returnRequestId, order.getShopOrderNumber(), order.getShopName());
        
        return CustomAttributeMapper.INSTANCE.fromApiCustomAttribute(response);
    }

    @Override
    public List<OMSCustomAttribute> getReturnRequestCustomAttributes(OMSOrder order, Long returnRequestId) throws ApiException
    {
        List<ReadCustomAttribute> response = shopApi.getCustomAttributes(returnRequestId, order.getShopOrderNumber(), order.getShopName());
        
        return CustomAttributeMapper.INSTANCE.fromApiCustomAttributes(response);
    }

    @Override
    public List<OMSCustomAttribute> createReturnRequestCustomAttributes(OMSOrder order, Long returnRequestId, List<OMSWriteCustomAttribute> customAttributes) throws ApiException
    {
        List<WriteCustomAttribute> ca = CustomAttributeMapper.INSTANCE.toApiCustomAttributes(customAttributes);
        List<ReadCustomAttribute> response = shopApi.createCustomAttributes(returnRequestId, order.getShopOrderNumber(), order.getShopName(), ca);
        return CustomAttributeMapper.INSTANCE.fromApiCustomAttributes(response);
    }

    @Override
    public List<OMSCustomAttribute> replaceReturnRequestCustomAttributes(OMSOrder order, Long returnRequestId, List<OMSWriteCustomAttribute> customAttributes) throws ApiException
    {
        List<WriteCustomAttribute> ca = CustomAttributeMapper.INSTANCE.toApiCustomAttributes(customAttributes);
        List<ReadCustomAttribute> response = shopApi.replaceReturnRequestCustomAttributes(returnRequestId, order.getShopOrderNumber(), order.getShopName(), ca);
        return CustomAttributeMapper.INSTANCE.fromApiCustomAttributes(response);
    }
    
    @Override
    public void deleteReturnRequestCustomAttribute(OMSOrder order, Long returnRequestId, Long customAttributeId) throws ApiException
    {
        shopApi.deleteCustomAttribute(customAttributeId, returnRequestId, order.getShopOrderNumber(), order.getShopName());
    }
}
