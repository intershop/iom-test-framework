package com.intershop.oms.test.servicehandler.orderservice.v2_2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intershop.oms.rest.order.v2_2.api.OrderApi;
import com.intershop.oms.rest.order.v2_2.model.ChangeRequestCreate;
import com.intershop.oms.rest.order.v2_2.model.ChangeRequestView;
import com.intershop.oms.rest.order.v2_2.model.Order;
import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.rest.shared.ApiResponse;
import com.intershop.oms.test.businessobject.OMSShop;
import com.intershop.oms.test.businessobject.order.OMSChangeRequest;
import com.intershop.oms.test.businessobject.order.OMSOrder;
import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.RESTServiceHandler;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;
import com.intershop.oms.test.servicehandler.orderservice.OMSOrderServiceHandler;
import com.intershop.oms.test.servicehandler.orderservice.v2_2.mapping.ChangeRequestCreateMapper;
import com.intershop.oms.test.servicehandler.orderservice.v2_2.mapping.ChangeRequestViewMapper;
import com.intershop.oms.test.servicehandler.orderservice.v2_2.mapping.OrderMapper;

class OMSOrderServiceHandlerV2_2 extends RESTServiceHandler implements OMSOrderServiceHandler
{
    private static final Logger log = LoggerFactory.getLogger(OMSOrderServiceHandlerV2_2.class);
    private final OMSDbHandler dbHandler;
    private final OrderApi orderApi;

    public OMSOrderServiceHandlerV2_2(OMSDbHandler dbHandler, ServiceConfiguration serviceConfig)
    {
        super(serviceConfig, "/rest/order-service", log);
        this.dbHandler = dbHandler;
        this.orderApi = new OrderApi(apiClient);
    }

    /**
     * sends an order
     *
     * @param expectedEndState
     *            wait for the order to be in the given state before returning
     * @return the orderId
     * @throws ApiException
     */
    @Override
    @SuppressWarnings("removal")
    public Long sendOrder(OMSOrder omsOrder, int expectedEndState) throws ApiException
    {
        Long omsOrderCreatedId = sendOrder(omsOrder);

        assert dbHandler.waitForOrderState(omsOrderCreatedId, expectedEndState);

        return omsOrderCreatedId;
    }

    /**
     * sends an order
     *
     * @return the orderId
     * @throws ApiException
     */
    public Long sendOrder(OMSOrder omsOrder) throws ApiException
    {
        Long shopId = omsOrder.getShopId();
        if (shopId == null)
        {
            throw new RuntimeException("shopId not set!");
        }

        Order order = OrderMapper.INSTANCE.toApiOrder(omsOrder);

        orderApi.getApiClient().setReadTimeout(120000);
        orderApi.createOrder(shopId, order);

        long orderId = dbHandler.getOrderId(shopId, omsOrder.getShopOrderNumber());
        omsOrder.setId(orderId);
        return orderId;
    }

    @Override
    public Long sendOrderChangeRequest(Long shopId, String shopOrderNumber, OMSChangeRequest orderChangeRequest,
                    int expectedEndState) throws ApiException
    {
        createOrderChangeRequest(shopId, shopOrderNumber, orderChangeRequest);
        long orderId = dbHandler.getOrderId(shopId, shopOrderNumber);
        long orderChangeRequestId = dbHandler.getOrderChangeRequestId(orderId, orderChangeRequest.getChangeRequestId());

        if (!dbHandler.waitForOrderChangeRequestState(orderChangeRequestId, expectedEndState))
        {
            throw new RuntimeException("Order change request " + orderChangeRequest.getChangeRequestId() + " for order "
                            + orderId + " didn't reach status " + expectedEndState + "!");
        }

        return orderChangeRequestId;
    }

    @Override
    public OMSChangeRequest getOrderChangeRequest(Long shopId, String shopOrderNumber, String changeRequestId,
                    List<String> excludes) throws ApiException
    {
        ApiResponse<ChangeRequestView> response;

        response = orderApi.getChangeRequestWithHttpInfo(shopId, shopOrderNumber, changeRequestId, excludes);
        ChangeRequestView changeRequestView = response.getData();
        return ChangeRequestViewMapper.INSTANCE.fromApiChangeRequestView(changeRequestView);
    }

    @Override
    @SuppressWarnings("removal")
    public ApiResponse<OMSOrder> createOrder(Long shopId, OMSOrder omsOrder) throws ApiException
    {
        ApiResponse<Void> response;

        Order order = OrderMapper.INSTANCE.toApiOrder(omsOrder);

        response = orderApi.createOrderWithHttpInfo(shopId, order);

        // map into response
        // currently data not returned on order creation, so the value will be
        // null
        return new ApiResponse<>(response.getStatusCode(), response.getHeaders(), null);

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
    public List<OMSChangeRequest> getOrderChangeRequests(Long shopId, String shopOrderNumber, List<String> excludes)
                    throws ApiException
    {
        List<OMSChangeRequest> OMSChangeRequests = new ArrayList<>();
        ApiResponse<List<ChangeRequestView>> response;

        response = orderApi.getChangeRequestViewListWithHttpInfo(shopId, shopOrderNumber, excludes);

        // map
        List<ChangeRequestView> changeRequestViews = response.getData();
        ChangeRequestViewMapper.INSTANCE.fromApiChangeRequestViewList(changeRequestViews, OMSChangeRequests);

        return OMSChangeRequests;
    }

    @Override
    public ApiResponse<OMSChangeRequest> createOrderChangeRequest(Long shopId, String shopOrderNumber,
                    OMSChangeRequest omsChangeRequestCreate) throws ApiException
    {
        ApiResponse<Void> response;

        ChangeRequestCreate changeRequestCreate = ChangeRequestCreateMapper.INSTANCE
                        .toApiChangeRequestCreate(omsChangeRequestCreate);

        response = orderApi.createChangeRequestWithHttpInfo(shopId, shopOrderNumber, changeRequestCreate);
        // currently data not returned on order creation, so the value will be
        // null
        return new ApiResponse<>(response.getStatusCode(), response.getHeaders(), null);

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
        return omsOrder;
    }

    @Override
    @Deprecated
    public OMSOrder createOrder(String user, String password, OMSOrder orderData)
    {
        log.info("Create order called in service handler version 2.2 ---- doing nothing, anymore!");
        return orderData;
    }

    @Override
    @Deprecated
    public Long sendOrder(String host, String port, OMSOrder order, int expectedEndState) throws ApiException
    {
        sendOrder(order, expectedEndState);
        return order.getId();
    }

    @Override
    @Deprecated
    public Long sendOrder(String host, String port, OMSOrder order) throws ApiException
    {
        sendOrder(order);
        return order.getId();
    }

    @Override
    protected Collection<Object> unwrapApiClient()
    {
        return Set.of(orderApi);
    }
}