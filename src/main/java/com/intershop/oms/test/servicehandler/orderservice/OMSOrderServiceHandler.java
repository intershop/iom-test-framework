package com.intershop.oms.test.servicehandler.orderservice;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.rest.shared.ApiResponse;
import com.intershop.oms.test.businessobject.order.OMSChangeRequest;
import com.intershop.oms.test.businessobject.order.OMSOrder;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderFilter;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderStateCollectionContainer;
import com.intershop.oms.test.servicehandler.OMSServiceHandler;
import com.intershop.oms.test.util.Experimental;
import com.intershop.oms.test.util.OMSSearchParams;

public interface OMSOrderServiceHandler extends OMSServiceHandler
{
    /**
     * creates an order and waits until at least the expectedEndState is reached
     *
     * for the current timeout handling  have a look into DBHandler
     *
     * @return the ID of the created order
     * @throws ApiException
     */
    @Deprecated(forRemoval = true, since = "4.0.0")
    Long sendOrder(OMSOrder order, int expectedEndState) throws ApiException;

    /**
     * Creates an order.
     *
     * Note, that the response currently doesn't return data, so the field will be null.
     *
     * @return response
     */
    @Deprecated(forRemoval = true, since = "4.0.0")
    ApiResponse<OMSOrder> createOrder(Long shopId, OMSOrder omsOrder) throws ApiException;

    /**
     * Create an order, optionally waiting for it to reach a target status.<br/>
     * <b>Impl Note:</b> currently the default integration will not return the up-to-date order status,
     * only the OMSOrder from the initial request, extended by the orderId. Will be fixed in a future iteration.
     */
    default OMSOrder createOrder(OMSOrder omsOrder, Integer targetState) throws ApiException
    {
        return createOrders(List.of(omsOrder), targetState).get(0);
    }

    /**
     * Create multiple orders, optionally waiting for them to reach a target status.<br/>
     * This method will create all order first, then wait for the target status, to optimize throughput in certain
     * configuration scenarios.<br/>
     * <b>Impl Note:</b> currently the default integration will not return the up-to-date order status,
     * only the OMSOrder from the initial request, extended by the orderId. Will be fixed in a future iteration.
     */
    List<OMSOrder> createOrders(List<OMSOrder> omsOrders, Integer targetState) throws ApiException;

    /**
     * retrieve the order
     *
     */
    OMSOrder getOrder(Long shopId, String shopOrderNumber) throws ApiException;

    /**
     * creates an order change request and waits until at least the expectedEndState is reached
     *
     * for the current timeout handling  have a look into DBHandler
     *
     * @return the ID of the created order change request
     */
    @Deprecated(forRemoval = true)
    default Long sendOrderChangeRequest(Long shopId, String shopOrderNumber, OMSChangeRequest orderChangeRequest,
                    int expectedEndState) throws ApiException
    {
        throw new RuntimeException("Method not supported for version < 2.2!");
    }

    /**
     * create an order change request
     *
     * Note, that the response currently doesn't return data, so the field will be null.
     *
     * @return response
     */
    @Deprecated(forRemoval = true)
    ApiResponse<OMSChangeRequest> createOrderChangeRequest(Long shopId, String shopOrderNumber,
                    OMSChangeRequest orderChangeRequest) throws ApiException;

    /**
     * create an order change request, optionally waiting for an expected target state.
     *
     * @return response
     */
    default OMSChangeRequest createOrderChangeRequest(
                    OMSChangeRequest orderChangeRequest, Integer targetState) throws ApiException
    {
        return createOrderChangeRequests(List.of(orderChangeRequest), targetState).get(0);
    }

    /**
     * create multiple order change requests, optionally waiting for an expected target state.
     *
     * @return response
     */
    List<OMSChangeRequest> createOrderChangeRequests(
                    List<OMSChangeRequest> orderChangeRequests, Integer targetState) throws ApiException;

    /**
     * retrieve an order change request
     *
     * @param excludes Excludes attributes from the returned data
     * @deprecated we don't need excludes in the test framework, only for technical tests of the REST API
     */
    @Deprecated
    OMSChangeRequest getOrderChangeRequest(Long shopId, String shopOrderNumber, String changeRequestId,
                    List<String> excludes) throws ApiException;
    default OMSChangeRequest getOrderChangeRequest(Long shopId, String shopOrderNumber, String changeRequestId) throws ApiException
    {
        return getOrderChangeRequest(shopId, shopOrderNumber, changeRequestId, new ArrayList<>());
    }

    /**
     * retrieve all order change requests for a shopId/shopOrderNumber-combination
     *
     * @param excludes Excludes attributes from the returned data
     * @return a list of order change requests for the given shopId/shopOrderNumber-combination
     * @deprecated we don't need excludes in the test framework, only for technical tests of the REST API
     */
    @Deprecated
    List<OMSChangeRequest> getOrderChangeRequests(Long shopId, String shopOrderNumber, List<String> excludes) throws ApiException;
    default List<OMSChangeRequest> getOrderChangeRequests(Long shopId, String shopOrderNumber) throws ApiException
    {
        return getOrderChangeRequests(shopId, shopOrderNumber, new ArrayList<>());
    }

    /**
     * creates an order
     *
     * @deprecated only for compatibility with the old SOAP-Service --- will be removed after removing the SOAP-Service
     */
    @Deprecated
    OMSOrder createOrder(String user, String password, OMSOrder orderData);

    /**
     * sends an order
     *
     * @param expectedEndState wait for the order to be in the given state before returning
     *
     * @return the orderId
     * @throws ApiException
     *
     * @deprecated only for compatibility with the old SOAP-Service --- will be removed after removing the SOAP-Service
     */
    @Deprecated
    Long sendOrder(String host, String port, OMSOrder order, int expectedEndState) throws ApiException;

    /**
     * sends an order
     *
     * @return  the orderId
     * @throws ApiException
     *
     * @deprecated only for compatibility with the old SOAP-Service --- will be removed after removing the SOAP-Service
     */
    @Deprecated
    Long sendOrder(String host, String port, OMSOrder order) throws ApiException;

    @Experimental("Proposed to be replaced by a method returning Collection<OMSOrder / OMSOrderState>")
    // FIXME: Proposal = return Collection<OMSOrder> instead..?
    // Note: Currently this doesn't make sense because those classes are too
    // different, OMSOrder has to be extended first
    OMSOrderStateCollectionContainer getOrderStatesBySortCriterias(Long shopId, OMSOrderFilter filter,
                    @Nullable OMSSearchParams searchParams) throws ApiException;

}
