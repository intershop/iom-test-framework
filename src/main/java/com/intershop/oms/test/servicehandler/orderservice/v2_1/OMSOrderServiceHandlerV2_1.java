package com.intershop.oms.test.servicehandler.orderservice.v2_1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intershop.oms.rest.order.v2_1.api.OrderApi;
import com.intershop.oms.rest.order.v2_1.model.Order;
import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.rest.shared.ApiResponse;
import com.intershop.oms.test.businessobject.OMSShop;
import com.intershop.oms.test.businessobject.order.OMSChangeRequest;
import com.intershop.oms.test.businessobject.order.OMSOrder;
import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.RESTServiceHandler;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;
import com.intershop.oms.test.servicehandler.orderservice.v2_1.mapping.OrderMapper;

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

    /**
     * sends an order
     *
     * @param host
     * @param port
     * @param order
     * @param expectedEndState
     *            wait for the order to be in the given state before returning
     *
     * @return the orderId
     * @throws ApiException
     */
    @Override
    public Long sendOrder(OMSOrder omsOrder, int expectedEndState) throws ApiException
    {
        Long omsOrderCreatedId = sendOrder(omsOrder);

        assert dbHandler.waitForOrderState(omsOrderCreatedId, expectedEndState);

        return omsOrderCreatedId;
    }

    /**
     * sends an order
     *
     * @param host
     * @param port
     * @param order
     * @return the orderId
     * @throws ApiException
     */
    public Long sendOrder(OMSOrder omsOrder) throws ApiException
    {
        Long shopId = omsOrder.getShopId();

        Order order = OrderMapper.INSTANCE.toApiOrder(omsOrder);

        orderApi.createOrder(shopId, order);
        long orderId = dbHandler.getOrderId(shopId, omsOrder.getShopOrderNumber());
        omsOrder.setId(orderId);
        return orderId;
    }

    @Override
    public ApiResponse<OMSOrder> createOrder(Long shopId, OMSOrder omsOrder) throws ApiException
    {
        throw new RuntimeException("Method not yet implemented for 2.1!");
    }

    @Override
    //this just calls sendOrder(OMSOrder, int) in order to be upwards-compatible with tests from OrderServiceSpec
    public List<OMSOrder> createOrders(List<OMSOrder> omsOrders, Integer targetState) throws ApiException
    {
        for (OMSOrder omsOrder : omsOrders)
        {
            sendOrder(omsOrder, targetState);
        }
        return omsOrders;
    }

    @Override
    public Long sendOrderChangeRequest(Long shopId, String shopOrderNumber, OMSChangeRequest orderChangeRequest,
                    int expectedEndState)
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
    public ApiResponse<OMSChangeRequest> createOrderChangeRequest(Long shopId, String shopOrderNumber,
                    OMSChangeRequest omsChangeRequestCreate) throws ApiException
    {
        throw new RuntimeException("Method not supported for version < 2.2!");
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
    @Deprecated
    public OMSOrder createOrder(String user, String password, OMSOrder orderData)
    {
        log.info("Create order called in service handler version 2.1 ---- doing nothing, anymore!");
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
