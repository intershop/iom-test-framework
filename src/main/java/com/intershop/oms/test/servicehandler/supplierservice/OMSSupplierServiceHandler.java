package com.intershop.oms.test.servicehandler.supplierservice;

import java.util.List;

import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.test.businessobject.communication.OMSDispatch;
import com.intershop.oms.test.businessobject.communication.OMSOrderResponse;
import com.intershop.oms.test.businessobject.communication.OMSReturn;
import com.intershop.oms.test.servicehandler.OMSServiceHandler;

public interface OMSSupplierServiceHandler extends OMSServiceHandler
{
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
