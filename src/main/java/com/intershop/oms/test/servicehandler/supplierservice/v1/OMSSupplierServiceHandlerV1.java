package com.intershop.oms.test.servicehandler.supplierservice.v1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intershop.oms.rest.communication.v2.api.DispatchApi;
import com.intershop.oms.rest.communication.v2.api.ResponseApi;
import com.intershop.oms.rest.communication.v2.api.ReturnApi;
import com.intershop.oms.rest.communication.v2.model.Dispatch;
import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.rest.shared.ApiResponse;
import com.intershop.oms.test.businessobject.OMSShop;
import com.intershop.oms.test.businessobject.OMSSupplier;
import com.intershop.oms.test.businessobject.communication.OMSDispatch;
import com.intershop.oms.test.businessobject.communication.OMSDispatchPosition;
import com.intershop.oms.test.businessobject.communication.OMSOrderResponse;
import com.intershop.oms.test.businessobject.communication.OMSOrderResponsePosition;
import com.intershop.oms.test.businessobject.communication.OMSReturn;
import com.intershop.oms.test.businessobject.communication.OMSReturnPosition;
import com.intershop.oms.test.businessobject.order.OMSOrder;
import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.RESTServiceHandler;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;
import com.intershop.oms.test.servicehandler.supplierservice.v1.mapping.DispatchMapper;
import com.intershop.oms.test.servicehandler.supplierservice.v1.mapping.OrderResponseMapper;
import com.intershop.oms.test.servicehandler.supplierservice.v1.mapping.ReturnMapper;
import com.intershop.oms.test.util.SupplierServiceUtil;

class OMSSupplierServiceHandlerV1 extends RESTServiceHandler
                implements com.intershop.oms.test.servicehandler.supplierservice.OMSSupplierServiceHandler
{
    private static final Logger log = LoggerFactory.getLogger(OMSSupplierServiceHandlerV1.class);
    private final DispatchApi dispatchApi;
    private final ResponseApi responseApi;
    private final ReturnApi returnApi;
    private final OMSDbHandler dbHandler;

    OMSSupplierServiceHandlerV1(ServiceConfiguration serviceConfiguration, OMSDbHandler dbHandler)
    {
        super(serviceConfiguration, "/rest/communication", log);
        dispatchApi = new DispatchApi(apiClient);
        responseApi = new ResponseApi(apiClient);
        returnApi = new ReturnApi(apiClient);
        this.dbHandler = dbHandler;
    }

    private String sendDispatch(OMSDispatch omsDispatch) throws ApiException
    {
        ApiResponse<Void> response;

        Dispatch dispatch = DispatchMapper.INSTANCE.toApiDispatch(omsDispatch);
        response = dispatchApi.createDispatchWithHttpInfo(dispatch);
        return response.getHeaders().get("Location").get(0);

    }

    @Override
    public Collection<String> sendFullDispatch(OMSOrder order, boolean useSupplierData) throws ApiException
    {
        log.info("Sending full dispatch for: " + order.toString());

        Map<OMSSupplier, Collection<OMSDispatchPosition>> supplierDispatchPositions = dbHandler
                        .getDispatchPositionsForOrder(order, useSupplierData);

        return sendDispatchPositions(order, null, supplierDispatchPositions);
    }

    @Override
    public Collection<String> sendDispatchPositions(OMSOrder order, String messageId,
                    Map<OMSSupplier, Collection<OMSDispatchPosition>> supplierDispatchPositions) throws ApiException
    {
        log.info("Sending dispatch positions for: " + order.toString());

        Collection<String> dispatchLocations = new ArrayList<>();
        for (Map.Entry<OMSSupplier, Collection<OMSDispatchPosition>> supplierDispatchPosition : supplierDispatchPositions
                        .entrySet())
        {
            OMSShop shop = order.getShop();
            OMSSupplier supplier = supplierDispatchPosition.getKey();
            OMSDispatch dispatch = SupplierServiceUtil.prepareDispatch(shop, supplier, order.getShopOrderNumber(),
                            supplierDispatchPosition.getValue(), null);
            dispatchLocations.add(sendDispatch(dispatch));
        }

        return dispatchLocations;
    }

    @Override
    public Collection<String> sendFullOrderResponse(OMSOrder order, boolean useSupplierData) throws ApiException
    {
        log.info("Sending full order response for: " + order.toString());

        List<OMSOrderResponse> responses = SupplierServiceUtil.prepareFullResponse(order, useSupplierData);
        return createResponses(responses, null).stream().map(r -> "sendFullOrderResponse-is-deprecated/" + r.getId())
                        .collect(Collectors.toList());
    }

    @Override
    public Collection<String> sendOrderResponsePositions(OMSOrder order, String messageId,
                    Map<OMSSupplier, Collection<OMSOrderResponsePosition>> supplierOrderResponsePositions)
                    throws ApiException
    {
        log.info("Sending order response positions for: " + order.toString());

        Collection<String> orderResponseLocations = new ArrayList<>();
        for (Map.Entry<OMSSupplier, Collection<OMSOrderResponsePosition>> supplierOrderResponsePosition : supplierOrderResponsePositions
                        .entrySet())
        {
            OMSOrderResponse response = SupplierServiceUtil.prepareResponse(order.getShop(),
                            supplierOrderResponsePosition.getKey(), order.getShopOrderNumber(),
                            supplierOrderResponsePosition.getValue(), messageId);

            orderResponseLocations.add("sendReturnPositions-is-deprecated/" + createResponse(response, null).getId());
        }

        return orderResponseLocations;
    }

    @Override
    public Collection<String> sendFullReturn(OMSOrder order, boolean useSupplierData) throws ApiException
    {
        log.info("Sending full return for: " + order.toString());

        Map<OMSSupplier, Collection<OMSReturnPosition>> supplierReturnPositions = dbHandler
                        .getReturnPositionsForOrder(order, useSupplierData);

        return sendReturnPositions(order, null, null, supplierReturnPositions);
    }

    @Override
    public Collection<String> sendReturnPositions(OMSOrder order, String messageId, String reason,
                    Map<OMSSupplier, Collection<OMSReturnPosition>> supplierReturnPositions) throws ApiException
    {
        log.info("Sending return positions for: " + order.toString());

        Collection<String> returnLocations = new ArrayList<>();
        for (Map.Entry<OMSSupplier, Collection<OMSReturnPosition>> supplierReturnPosition : supplierReturnPositions
                        .entrySet())
        {
            OMSReturn returnMessage = SupplierServiceUtil.prepareReturn(order.getShop(),
                            supplierReturnPosition.getKey(), order.getShopOrderNumber(),
                            supplierReturnPosition.getValue(), messageId);
            returnMessage.setReason(Objects.requireNonNullElse(reason, "RET010"));

            returnLocations.add("sendReturnPositions-is-deprecated/" + createReturn(returnMessage, null).getId());
        }

        return returnLocations;
    }

    @Override
    public String sendCrossDockingOrderResponse(OMSSupplier collectingSupplier, OMSOrder order, boolean useSupplierData)
    {
        throw new RuntimeException("Cross Docking is only supported from version 2.11 (here called within 1.0)!");
    }

    @Override
    public String sendCrossDockingDispatch(OMSSupplier collectingSupplier, OMSOrder order, boolean useSupplierData)
    {
        throw new RuntimeException("Cross Docking is only supported from version 2.11 (here called within 1.0)!");
    }

    @Override
    public String sendCrossDockingReturn(OMSSupplier collectingSupplier, OMSOrder order, String returnReason,
                    boolean useSupplierData)
    {
        throw new RuntimeException("Cross Docking is only supported from version 2.11 (here called within 1.0)!");
    }

    @Override
    protected Collection<Object> unwrapApiClient()
    {
        return Set.of(dispatchApi, responseApi, returnApi);
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
