package com.intershop.oms.test.servicehandler.orderservice.v2_1;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intershop.oms.rest.order.v2_1.api.OrderApi;
import com.intershop.oms.rest.order.v2_1.model.Order;
import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.test.businessobject.order.OMSChangeRequest;
import com.intershop.oms.test.businessobject.order.OMSOrder;
import com.intershop.oms.test.businessobject.orderstate.OMSLatestOrderStateCollectionContainer;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderFilter;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderStateCollectionContainer;
import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.RESTServiceHandler;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;
import com.intershop.oms.test.servicehandler.orderservice.v2_1.mapping.OrderMapper;
import com.intershop.oms.test.util.OMSSearchParams;

class OMSOrderServiceHandlerV2_1 extends RESTServiceHandler
                implements com.intershop.oms.test.servicehandler.orderservice.OMSOrderServiceHandler
{
    private static final Logger log = LoggerFactory.getLogger(OMSOrderServiceHandlerV2_1.class);
    private final OMSDbHandler dbHandler;
    private final OrderApi orderApi;

    public OMSOrderServiceHandlerV2_1(OMSDbHandler dbHandler, ServiceConfiguration serviceConfig)
    {
        super(serviceConfig, "/rest/order-service", log);
        this.dbHandler = dbHandler;
        this.orderApi = new OrderApi(apiClient);
    }

    @Override
    public List<OMSOrder> createOrders(List<OMSOrder> omsOrders, Integer targetState) throws ApiException
    {
        List<OMSOrder> omsOrdersCreated = new ArrayList<>();

        for (OMSOrder omsOrder: omsOrders)
        {
            Long shopId = omsOrder.getShopId();

            Order order = OrderMapper.INSTANCE.toApiOrder(omsOrder);
            orderApi.createOrder(shopId, order);
            long orderId = dbHandler.getOrderId(shopId, omsOrder.getShopOrderNumber());
            omsOrder.setId(orderId);
            omsOrdersCreated.add(omsOrder);
        }

        for (OMSOrder o : omsOrdersCreated)
        {
            assert dbHandler.waitForOrderState(o.getId(), targetState);
        }

        return omsOrdersCreated;
    }

    @Override
    protected Collection<Object> unwrapApiClient()
    {
        return Set.of(orderApi);
    }

    @Override
    public List<OMSChangeRequest> createOrderChangeRequests(List<OMSChangeRequest> orderChangeRequests,
                    Integer targetState) throws ApiException
    {
        throw new RuntimeException("Method not supported for version < 2.2!");
    }

    @Override
    public OMSOrder getOrder(Long shopId, String shopOrderNumber) throws ApiException
    {
        throw new RuntimeException("Method not supported for version < 2.2!");
    }

    @Override
    public OMSChangeRequest getOrderChangeRequest(Long shopId, String shopOrderNumber, String changeRequestId,
                    List<String> excludes) throws ApiException
    {
        throw new RuntimeException("Method not supported for version < 2.2!");
    }

    @Override
    public List<OMSChangeRequest> getOrderChangeRequests(Long shopId, String shopOrderNumber, List<String> excludes)
                    throws ApiException
    {
        throw new RuntimeException("Method not supported for version < 2.2!");
    }

    @Override
    public OMSOrderStateCollectionContainer getOrderStatesBySortCriterias(Long shopId, OMSOrderFilter filter,
                    @Nullable OMSSearchParams searchParams) throws ApiException
    {
        throw new RuntimeException("Method not supported for version < 2.3!");
    }
    
    @Override
    public OMSLatestOrderStateCollectionContainer getModifiedOrderStates(Long shopId, OffsetDateTime modifiedSince,
                    Long minCursor, Integer limit) throws ApiException
    {
        throw new RuntimeException("Method not supported for version < 2.4!");
    }
}