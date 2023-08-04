package com.intershop.oms.test.servicehandler.rmaservice;

import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.test.businessobject.order.OMSOrder;
import com.intershop.oms.test.businessobject.rma.OMSReadReturnRequest;
import com.intershop.oms.test.businessobject.rma.OMSReadReturnRequestPosition;
import com.intershop.oms.test.businessobject.rma.OMSReturnRequest;
import com.intershop.oms.test.businessobject.rma.OMSReturnableData;
import com.intershop.oms.test.businessobject.rma.OMSShopReturnReason;
import com.intershop.oms.test.businessobject.rma.OMSWriteCustomAttribute;
import com.intershop.oms.test.businessobject.rma.OMSWriteReturnRequestPosition;
import com.intershop.oms.test.servicehandler.OMSServiceHandler;
import com.intershop.oms.test.util.Experimental;

import java.util.Collection;
import java.util.List;

@Experimental("Full rework of the handler is still pending")
public interface OMSReturnRequestServiceHandler extends OMSServiceHandler
{

    /**
     * sends a new return request and checks them for the expected end state
     * <p>
     * this uses host, port, wsUser, and wsPassword as set in the Service
     * Constructor per default
     * <p>
     * this uses only generated code from the swagger as service client
     *
     * @param shopName    the name of the shop
     * @param shopOrderNo the order number as used in the shop
     * @return the location of the newly created return request
     */
    String sendReturnRequest(String shopName, String shopOrderNo,
                    Collection<OMSWriteReturnRequestPosition> returnrequestPositions) throws ApiException;

    /**
     * @param order      the order to return request
     * @return a return request location
     */
    String sendFullReturnRequest(OMSOrder order) throws ApiException;

    /**
     * returns a list of return requests for a given order
     *
     * @return list of return requests for a given order
     */
    List<OMSReadReturnRequest> getReturnRequests(OMSOrder order) throws ApiException;

    /**
     * returns a list of return request positions for a given order, returnRequestId
     *
     * @return list of return request positions for a given order, returnRequestId
     */
    List<OMSReadReturnRequestPosition> getReturnRequestPositions(OMSOrder order, Long returnRequestId)
                    throws ApiException;

    /**
     * returns a list of returnable order positions wrapped into a
     * ReturnableData object for a given order.
     *
     */
    OMSReturnableData getReturnableData(OMSOrder order) throws ApiException;

    /**
     * returns the list of configured return reasons for a IOM shop instance
     * with the given shopName.<br>
     * The return reasons can be filtered by name of 1..n return types.
     *
     */
    List<OMSShopReturnReason> getShop2ReturnReasons(String shopName, List<String> returnTypes)
                    throws ApiException;

    /**
     * Send a return request to IOM, optionally waiting for it to reach a specific targetState
     *
     * @param returnRequest the prepared return request
     * @param targetState an optional targetState (ReturnStatesDefDO.id) that has to be reached
     *                   before returning the ReturnRequest
     */
    OMSReturnRequest createReturnRequest(OMSReturnRequest returnRequest, Integer targetState) throws ApiException;

    /**
     * retrieve a ReturnRequest by id
     */
    OMSReturnRequest getReturnRequest(Long id) throws ApiException;
    
    Long createReturnRequestPositionCustomAttribute(OMSOrder order, Long returnRequestId, Long returnRequestPositionId, OMSWriteCustomAttribute customAttribute) throws ApiException;
    void deleteReturnRequestPositionCustomAttribute(OMSOrder order, Long returnRequestId, Long returnRequestPositionId, Long customAttributeId) throws ApiException;

}
