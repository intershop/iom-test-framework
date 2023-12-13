package com.intershop.oms.test.servicehandler.supplierservice;

import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.test.businessobject.OMSSupplier;
import com.intershop.oms.test.businessobject.communication.OMSDispatch;
import com.intershop.oms.test.businessobject.communication.OMSDispatchPosition;
import com.intershop.oms.test.businessobject.communication.OMSOrderResponse;
import com.intershop.oms.test.businessobject.communication.OMSOrderResponsePosition;
import com.intershop.oms.test.businessobject.communication.OMSReturn;
import com.intershop.oms.test.businessobject.communication.OMSReturnPosition;
import com.intershop.oms.test.businessobject.order.OMSOrder;
import com.intershop.oms.test.servicehandler.OMSServiceHandler;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface OMSSupplierServiceHandler extends OMSServiceHandler
{

    /**
     * @param order           the order to order response
     * @param useSupplierData if true - use supplierArticleName and supplierArticleNumber,
     *                        if false - use shopArticleName and shopArticleNumber
     * @return a collection of order response locations
     */
    @Deprecated
    Collection<String> sendFullOrderResponse(OMSOrder order, boolean useSupplierData) throws ApiException;

    /**
     * @param order                          the order to order response
     * @param supplierOrderResponsePositions a map supplierName -&gt; orderResponsePositions
     * @return a collection of order response locations
     */
    @Deprecated
    Collection<String> sendOrderResponsePositions(OMSOrder order, String messageId,
                    Map<OMSSupplier, Collection<OMSOrderResponsePosition>> supplierOrderResponsePositions)
                    throws ApiException;

    /**
     * @param order           the order to dispatch
     * @param useSupplierData if true  - use supplierArticleName and supplierArticleNumber,
     *                        if false - use shopArticleName and shopArticleNumber
     * @return a collection of dispatch locations
     */
    @Deprecated
    Collection<String> sendFullDispatch(OMSOrder order, boolean useSupplierData) throws ApiException;

    /**
     * @param order                     the order to dispatch
     * @param supplierDispatchPositions a map supplierName -&gt; dispatchPositions
     * @return a collection of dispatch locations
     */
    @Deprecated
    Collection<String> sendDispatchPositions(OMSOrder order, String messageId,
                    Map<OMSSupplier, Collection<OMSDispatchPosition>> supplierDispatchPositions) throws ApiException;

    /**
     * @param order           the order to return
     * @param useSupplierData if true - use supplierArticleName and supplierArticleNumber,
     *                        if false - use shopArticleName and shopArticleNumber
     * @return a collection of return locations
     */
    @Deprecated
    Collection<String> sendFullReturn(OMSOrder order, boolean useSupplierData) throws ApiException;

    /**
     * @param order                   the order to return
     * @param messageId               the messageId of the return
     * @param reason                  the reason of the return
     * @param supplierReturnPositions a map supplierName -&gt; returnPositions
     * @return a collection of return locations
     */
    @Deprecated
    Collection<String> sendReturnPositions(OMSOrder order, String messageId, String reason,
                    Map<OMSSupplier, Collection<OMSReturnPosition>> supplierReturnPositions) throws ApiException;

    default OMSDispatch createDispatch(OMSDispatch omsDispatch, Integer targetState) throws ApiException
    {
        return createDispatches(List.of(omsDispatch), targetState).get(0);
    }
    default OMSReturn createReturn(OMSReturn omsReturn, Integer targetState) throws ApiException
    {
        return createReturns(List.of(omsReturn), targetState).get(0);
    }
    default OMSOrderResponse createResponse(OMSOrderResponse omsResponse, Integer targetState) throws ApiException
    {
        return createResponses(List.of(omsResponse), targetState).get(0);
    }
    List<OMSDispatch> createDispatches(List<OMSDispatch> omsDispatches, Integer targetState) throws ApiException;
    List<OMSReturn> createReturns(List<OMSReturn> omsReturns, Integer targetState) throws ApiException;
    List<OMSOrderResponse> createResponses(List<OMSOrderResponse> omsResponses, Integer targetState) throws ApiException;
}
