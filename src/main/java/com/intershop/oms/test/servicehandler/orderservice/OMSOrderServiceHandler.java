package com.intershop.oms.test.servicehandler.orderservice;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.test.businessobject.order.OMSChangeRequest;
import com.intershop.oms.test.businessobject.order.OMSOrder;
import com.intershop.oms.test.businessobject.orderstate.OMSLatestOrderStateCollectionContainer;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderFilter;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderStateCollectionContainer;
import com.intershop.oms.test.servicehandler.OMSServiceHandler;
import com.intershop.oms.test.util.Experimental;
import com.intershop.oms.test.util.OMSSearchParams;

public interface OMSOrderServiceHandler extends OMSServiceHandler
{
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
     * This method will create all orders first, then wait for the target status, to optimize throughput in certain
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
     * used i.e. for getting the current state of an possible asynchronously applied change request
     *
     * @param excludes Excludes attributes from the returned data
     */
    OMSChangeRequest getOrderChangeRequest(Long shopId, String shopOrderNumber, String changeRequestId, List<String> excludes) throws ApiException;
    default OMSChangeRequest getOrderChangeRequest(Long shopId, String shopOrderNumber, String changeRequestId) throws ApiException
    {
        return getOrderChangeRequest(shopId, shopOrderNumber, changeRequestId, new ArrayList<>());
    }

    /**
     * retrieve all order change requests for a shopId/shopOrderNumber-combination
     *
     * used i.e. for getting the current state of an possible asynchronously applied change request
     * 
     * @param excludes Excludes attributes from the returned data
     * @return a list of order change requests for the given shopId/shopOrderNumber-combination
     */
    List<OMSChangeRequest> getOrderChangeRequests(Long shopId, String shopOrderNumber, List<String> excludes) throws ApiException;
    default List<OMSChangeRequest> getOrderChangeRequests(Long shopId, String shopOrderNumber) throws ApiException
    {
        return getOrderChangeRequests(shopId, shopOrderNumber, new ArrayList<>());
    }

    @Experimental("Proposed to be replaced by a method returning Collection<OMSOrder / OMSOrderState>")
    // FIXME: Proposal = return Collection<OMSOrder> instead..?
    // Note: Currently this doesn't make sense because those classes are too
    // different, OMSOrder has to be extended first
    OMSOrderStateCollectionContainer getOrderStatesBySortCriterias(Long shopId, OMSOrderFilter filter,
                    @Nullable OMSSearchParams searchParams) throws ApiException;
    
    /**
     * Returns order states new or modified since a given time.
     * This operation returns the order states of orders that are new or were modified since a given timestamp. The response is always sorted with the oldest modified item first. Only one of the two query parameters must be set. Use the &#x60;minCursor&#x60; parameter to paginate safely without gaps. The cursor is a simple long value increased with any change on any order state; it is hence volatile, has gaps and has no other usage beside paging. The min/max cursor values of a response can be found in the meta information of the response.
     * @param shopId The ID of the shop (required)
     * @param modifiedSince The least time an order was modified (or created) (optional)
     * @param minCursor The min value of the modified cursor. The previous cursor values can be found in the metadata of the previous response. (As two orders may have the same modification date, using the timestamp as pagination limit is not fully deterministic.) (optional)
     * @param limit The number of items to return (optional, default to 50)
     * @return LatestOrderStateCollectionContainer
     * @throws ApiException if fails to make API call
     * @http.response.details
       <table summary="Response Details" border="1">
         <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
         <tr><td> 200 </td><td> OK </td><td>  -  </td></tr>
         <tr><td> 400 </td><td> Bad Request </td><td>  -  </td></tr>
         <tr><td> 401 </td><td> Authentication information is missing or invalid </td><td>  -  </td></tr>
         <tr><td> 403 </td><td> Forbidden </td><td>  -  </td></tr>
         <tr><td> 406 </td><td> Not Acceptable </td><td>  -  </td></tr>
         <tr><td> 500 </td><td> An unexpected error occured </td><td>  -  </td></tr>
       </table>
     */
    public OMSLatestOrderStateCollectionContainer getModifiedOrderStates(Long shopId, OffsetDateTime modifiedSince,
                    Long minCursor, Integer limit) throws ApiException;
}
