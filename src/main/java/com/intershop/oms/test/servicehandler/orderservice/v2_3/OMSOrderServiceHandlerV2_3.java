package com.intershop.oms.test.servicehandler.orderservice.v2_3;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intershop.oms.rest.order.v2_3.api.OrderApi;
import com.intershop.oms.rest.order.v2_3.api.OrderStateApi;
import com.intershop.oms.rest.order.v2_3.model.ChangeRequestView;
import com.intershop.oms.rest.order.v2_3.model.Order;
import com.intershop.oms.rest.order.v2_3.model.OrderPositionReturned;
import com.intershop.oms.rest.order.v2_3.model.OrderStateCollectionContainer;
import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.rest.shared.ApiResponse;
import com.intershop.oms.test.businessobject.OMSShop;
import com.intershop.oms.test.businessobject.order.OMSChangeRequest;
import com.intershop.oms.test.businessobject.order.OMSOrder;
import com.intershop.oms.test.businessobject.orderstate.OMSLatestOrderStateCollectionContainer;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderFilter;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderStateCollectionContainer;
import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.RESTServiceHandler;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;
import com.intershop.oms.test.servicehandler.orderservice.OMSOrderServiceHandler;
import com.intershop.oms.test.servicehandler.orderservice.v2_3.mapping.ChangeRequestCreateMapper;
import com.intershop.oms.test.servicehandler.orderservice.v2_3.mapping.ChangeRequestViewMapper;
import com.intershop.oms.test.servicehandler.orderservice.v2_3.mapping.OrderMapper;
import com.intershop.oms.test.servicehandler.orderservice.v2_3.mapping.OrderPositionReturnedMixIn;
import com.intershop.oms.test.servicehandler.orderservice.v2_3.mapping.OrderStateCollectionContainerMapper;
import com.intershop.oms.test.util.OMSSearchParams;

class OMSOrderServiceHandlerV2_3 extends RESTServiceHandler implements OMSOrderServiceHandler
{
    private static final Logger log = LoggerFactory.getLogger(OMSOrderServiceHandlerV2_3.class);
    private final OMSDbHandler dbHandler;
    private final OrderApi orderApi;
    private final OrderStateApi orderStateApi;

    public OMSOrderServiceHandlerV2_3(OMSDbHandler dbHandler, ServiceConfiguration serviceConfig)
    {
        super(serviceConfig, "/rest/order-service", log);
        apiClient.getJSON().getMapper().addMixIn(OrderPositionReturned.class, OrderPositionReturnedMixIn.class);
        this.dbHandler = dbHandler;
        this.orderApi = new OrderApi(apiClient);
        this.orderStateApi = new OrderStateApi(apiClient);
    }

    @Override
    public List<OMSOrder> createOrders(List<OMSOrder> omsOrders, Integer targetState) throws ApiException
    {
        List<OMSOrder> createdOrders = new ArrayList<>();
        // ... regular loop due to exception handling
        for (OMSOrder omsOrder : omsOrders)
        {
            orderApi.createOrderWithHttpInfo(omsOrder.getShopId(), OrderMapper.INSTANCE.toApiOrder(omsOrder));
        }
        // second loop - this will take a bit
        for (OMSOrder omsOrder : omsOrders)
        {
            long orderId = dbHandler.getOrderId(omsOrder.getShopId(), omsOrder.getShopOrderNumber());
            assert targetState == null || dbHandler.waitForOrderState(orderId, targetState);
            OMSOrder upToDateOrder = getOrder(omsOrder.getShopId(), omsOrder.getShopOrderNumber());
            upToDateOrder.setId(orderId);
            createdOrders.add(upToDateOrder);
        }

        return createdOrders;
    }

    @Override
    public List<OMSChangeRequest> createOrderChangeRequests(List<OMSChangeRequest> orderChangeRequests,
                    Integer targetState) throws ApiException
    {
        List<OMSChangeRequest> createdChangeRequests = new ArrayList<>();
        // create all CRs first
        for (OMSChangeRequest orderChangeRequest : orderChangeRequests)
        {
            orderApi.createChangeRequest(orderChangeRequest.getChangeRequest().getShopId(),
                            orderChangeRequest.getChangeRequest().getShopOrderNumber(),
                            ChangeRequestCreateMapper.INSTANCE.toApiChangeRequestCreate(orderChangeRequest));
        }

        for (OMSChangeRequest orderChangeRequest : orderChangeRequests)
        {
            long changeRequestId = dbHandler.getOrderChangeRequestId(orderChangeRequest.getChangeRequest().getShopId(),
                            orderChangeRequest.getChangeRequest().getShopOrderNumber(),
                            orderChangeRequest.getChangeRequestId());

            assert targetState == null || dbHandler.waitForOrderChangeRequestState(changeRequestId, targetState);

            ChangeRequestView changeRequest = orderApi.getChangeRequest(
                            orderChangeRequest.getChangeRequest().getShopId(),
                            orderChangeRequest.getChangeRequest().getShopOrderNumber(),
                            orderChangeRequest.getChangeRequestId(), Collections.emptyList());
            OMSChangeRequest createdChangeRequest = ChangeRequestViewMapper.INSTANCE
                            .fromApiChangeRequestView(changeRequest);
            createdChangeRequest.setId(changeRequestId);
            createdChangeRequests.add(createdChangeRequest);
        }
        return createdChangeRequests;
    }

    @Override
    public OMSOrder getOrder(Long shopId, String shopOrderNumber) throws ApiException
    {
        ApiResponse<Order> response;

        response = orderApi.getOrderWithHttpInfo(shopId, shopOrderNumber);
        Order order = response.getData();
        OMSOrder omsOrder = OrderMapper.INSTANCE.fromApiOrder(order);
        omsOrder.setShop(new OMSShop(shopId));
        omsOrder.setId(dbHandler.getOrderId(shopId, omsOrder.getShopOrderNumber()));
        return omsOrder;
    }

    @Override
    protected Collection<Object> unwrapApiClient()
    {
        return Set.of(orderApi, orderStateApi);
    }

    @Override
    public OMSOrderStateCollectionContainer getOrderStatesBySortCriterias(Long shopId, OMSOrderFilter filter,
                    @Nullable OMSSearchParams searchParams) throws ApiException
    {
        if (searchParams == null)
        {
            searchParams = new OMSSearchParams();
        }
        ApiResponse<OrderStateCollectionContainer> response = orderStateApi.getOrderStatesBySortCriteriasWithHttpInfo(
                        shopId, filter.getShopOrderNumbers(), filter.getShopCustomerNumbers(),
                        filter.getProductNumbers(), filter.getStatuses(), filter.getEmails(),
                        filter.getShopOrderCreationDateFrom(), filter.getShopOrderCreationDateTo(),
                        searchParams.getSortableAttribute(),
                        // use a String to avoid an API break (TODO: check if
                        // required)
                        searchParams.getSortDirection().name(), searchParams.getOffset(), searchParams.getLimit());
        return OrderStateCollectionContainerMapper.INSTANCE.fromApiOrderStateCollectionContainer(response.getData());
    }
    
    @Override
    public OMSLatestOrderStateCollectionContainer getModifiedOrderStates(Long shopId, OffsetDateTime modifiedSince,
                    Long minCursor, Integer limit) throws ApiException
    {
        throw new RuntimeException("Method not supported for version < 2.4!");
    }
}