package com.intershop.oms.test.servicehandler.supplierservice.v2_11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intershop.oms.rest.communication.v2_11.api.DispatchApi;
import com.intershop.oms.rest.communication.v2_11.api.ResponseApi;
import com.intershop.oms.rest.communication.v2_11.api.ReturnApi;
import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.rest.shared.ApiResponse;
import com.intershop.oms.test.businessobject.communication.OMSDispatch;
import com.intershop.oms.test.businessobject.communication.OMSOrderResponse;
import com.intershop.oms.test.businessobject.communication.OMSReturn;
import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.RESTServiceHandler;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;
import com.intershop.oms.test.servicehandler.supplierservice.v2_11.mapping.DispatchMapper;
import com.intershop.oms.test.servicehandler.supplierservice.v2_11.mapping.OrderResponseMapper;
import com.intershop.oms.test.servicehandler.supplierservice.v2_11.mapping.ReturnMapper;

public class OMSSupplierServiceHandlerV2_11 extends RESTServiceHandler
                implements com.intershop.oms.test.servicehandler.supplierservice.OMSSupplierServiceHandler
{
    private static final Logger log = LoggerFactory.getLogger(OMSSupplierServiceHandlerV2_11.class);
    private final DispatchApi dispatchApi;
    private final ReturnApi returnApi;
    private final ResponseApi responseApi;
    private final OMSDbHandler dbHandler;

    OMSSupplierServiceHandlerV2_11(ServiceConfiguration serviceConfiguration, OMSDbHandler dbHandler)
    {
        super(serviceConfiguration, "/rest/communication", log);
        dispatchApi = new DispatchApi(apiClient);
        returnApi = new ReturnApi(apiClient);
        responseApi = new ResponseApi(apiClient);
        this.dbHandler = dbHandler;
    }

    @Override
    protected Collection<Object> unwrapApiClient()
    {
        return Set.of(dispatchApi, returnApi, responseApi);
    }

    @Override
    public List<OMSDispatch> createDispatches(List<OMSDispatch> omsDispatches, Integer targetState) throws ApiException
    {
        List<Long> dispatchIds = new ArrayList<>();
        for (OMSDispatch dispatch : omsDispatches)
        {
            ApiResponse<Void> result = dispatchApi
                            .createDispatchWithHttpInfo(DispatchMapper.INSTANCE.toApiDispatch(dispatch));
            Long dispatchId = parseIdFromLocation(result.getHeaders().get(HTTP_HEADER_LOCATION).get(0));
            dispatch.setId(dispatchId);
            dispatchIds.add(dispatchId);
        }

        assert targetState == null || dbHandler.waitForDispatchesState(dispatchIds, targetState);

        return omsDispatches;
    }

    @Override
    public List<OMSReturn> createReturns(List<OMSReturn> omsReturns, Integer targetState) throws ApiException
    {
        List<Long> returnIds = new ArrayList<>();
        for (OMSReturn omsReturn : omsReturns)
        {
            ApiResponse<Void> result = returnApi.createReturnWithHttpInfo(ReturnMapper.INSTANCE.toApiReturn(omsReturn));
            Long returnId = parseIdFromLocation(result.getHeaders().get(HTTP_HEADER_LOCATION).get(0));
            omsReturn.setId(returnId);
            returnIds.add(returnId);
        }

        assert targetState == null || dbHandler.waitForReturnsState(returnIds, targetState);

        return omsReturns;
    }

    @Override
    public List<OMSOrderResponse> createResponses(List<OMSOrderResponse> omsResponses, Integer targetState)
                    throws ApiException
    {
        List<Long> responseIds = new ArrayList<>();
        for (OMSOrderResponse omsReponse : omsResponses)
        {
            ApiResponse<Void> result = responseApi.createOrderResponseWithHttpInfo(
                            OrderResponseMapper.INSTANCE.toApiOrderResponse(omsReponse));
            Long responseId = parseIdFromLocation(result.getHeaders().get(HTTP_HEADER_LOCATION).get(0));
            omsReponse.setId(responseId);
            responseIds.add(responseId);
        }

        assert targetState == null || dbHandler.waitForOrderResponsesState(responseIds, targetState);

        return omsResponses;
    }

}
