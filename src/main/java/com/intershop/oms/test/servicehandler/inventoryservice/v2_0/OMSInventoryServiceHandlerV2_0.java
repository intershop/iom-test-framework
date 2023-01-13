package com.intershop.oms.test.servicehandler.inventoryservice.v2_0;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intershop.oms.rest.inventory.v2_0.api.InventoryApi;
import com.intershop.oms.rest.inventory.v2_0.model.HttpResponseListAtp;
import com.intershop.oms.rest.reservation.v2_0.api.ReservationApi;
import com.intershop.oms.rest.reservation.v2_0.model.HttpResponseReservation;
import com.intershop.oms.rest.reservation.v2_0.model.ReservationRequest;
import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.rest.shared.ApiResponse;
import com.intershop.oms.test.businessobject.OMSInventory;
import com.intershop.oms.test.businessobject.OMSReservation;
import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.RESTServiceHandler;
import com.intershop.oms.test.servicehandler.inventoryservice.OMSInventoryServiceHandler;
import com.intershop.oms.test.servicehandler.inventoryservice.v2_0.mapping.InventoryMapper;
import com.intershop.oms.test.servicehandler.inventoryservice.v2_0.mapping.ReservationMapper;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;
import com.intershop.oms.test.util.CommaSeparatedList;

class OMSInventoryServiceHandlerV2_0 extends RESTServiceHandler implements OMSInventoryServiceHandler
{
    private final OMSDbHandler dbHandler;
    private final InventoryApi inventoryApi;
    private final ReservationApi reservationApi;
    private static final Logger log = LoggerFactory.getLogger(OMSInventoryServiceHandlerV2_0.class);

    public OMSInventoryServiceHandlerV2_0(OMSDbHandler dbHandler, ServiceConfiguration serviceConfig)
    {
        super(serviceConfig, "/servlets/services", log);
        this.dbHandler = dbHandler;
        reservationApi = new ReservationApi(apiClient);
        inventoryApi = new InventoryApi(apiClient);
    }

    @Override
    public OMSReservation createReservation(OMSReservation omsReservation) throws ApiException
    {
        ReservationRequest reservation = ReservationMapper.INSTANCE.toApiReservation(omsReservation);
        HttpResponseReservation reservationResponse = reservationApi.createReservation(omsReservation.getShop().getId(), reservation);
        return ReservationMapper.INSTANCE.fromApiReservation(reservationResponse.getData());
    }

    @Override
    public OMSReservation updateReservation(OMSReservation omsReservation) throws ApiException
    {
        ReservationRequest reservation = ReservationMapper.INSTANCE.toApiReservation(omsReservation);
        HttpResponseReservation reservationResponse = reservationApi.updateReservation(omsReservation.getResvId(), reservation);
        return ReservationMapper.INSTANCE.fromApiReservation(reservationResponse.getData());
    }

    @Override
    public OMSReservation getReservation(Long reservationId) throws ApiException
    {
        HttpResponseReservation reservationResponse = reservationApi.getReservation(reservationId);
        return ReservationMapper.INSTANCE.fromApiReservation(reservationResponse.getData());
    }

    @Override
    public void deleteReservation(Long reservationId) throws ApiException
    {
        reservationApi.deleteReservationWithHttpInfo(reservationId);
    }

    @Override
    public List<OMSInventory> getInventory(Long shopId, List<String> productIds, Long reservationId) throws ApiException
    {
        CommaSeparatedList<String> myCommaSepraratedProductIds = new CommaSeparatedList<>();
        myCommaSepraratedProductIds.addAll(productIds);
        HttpResponseListAtp inventoryResponse  = inventoryApi.getAvailableQuantity(shopId, myCommaSepraratedProductIds, reservationId);
        return InventoryMapper.INSTANCE.fromApiInventory(inventoryResponse.getData());
    }

    @Override
    protected Collection<Object> unwrapApiClient()
    {
        return Set.of(reservationApi, inventoryApi);
    }
}
