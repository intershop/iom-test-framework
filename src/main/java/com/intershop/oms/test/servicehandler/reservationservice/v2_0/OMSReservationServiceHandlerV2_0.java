package com.intershop.oms.test.servicehandler.reservationservice.v2_0;

import java.util.Collection;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intershop.oms.rest.reservation.v2_0.api.ReservationApi;
import com.intershop.oms.rest.reservation.v2_0.model.HttpResponseReservation;
import com.intershop.oms.rest.reservation.v2_0.model.Reservation;
import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.test.businessobject.OMSReservation;
import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.RESTServiceHandler;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;
import com.intershop.oms.test.servicehandler.reservationservice.OMSReservationServiceHandler;
import com.intershop.oms.test.servicehandler.reservationservice.v2_0.mapping.ReservationMapper;

class OMSReservationServiceHandlerV2_0 extends RESTServiceHandler implements OMSReservationServiceHandler
{
    private final OMSDbHandler dbHandler;
    private final ReservationApi reservationApi;
    private static final Logger log = LoggerFactory.getLogger(OMSReservationServiceHandlerV2_0.class);

    public OMSReservationServiceHandlerV2_0(OMSDbHandler dbHandler, ServiceConfiguration serviceConfig)
    {
        super(serviceConfig, "/servlets/services/reservation", log);
        this.dbHandler = dbHandler;
        reservationApi = new ReservationApi(apiClient);
    }

    @Override
    public OMSReservation createReservation(Long shopId, OMSReservation omsReservation) throws ApiException
    {
        Reservation reservation = ReservationMapper.INSTANCE.toApiReservation(omsReservation);
        HttpResponseReservation reservationResponse = reservationApi.createReservation(shopId, omsReservation.getReservationType(), omsReservation.getLifetime(), reservation.getItems());
        OMSReservation omsReservationResponse = ReservationMapper.INSTANCE.fromApiReservation(reservationResponse.getData());
        return omsReservationResponse;
    }

    @Override
    protected Collection<Object> unwrapApiClient()
    {
        return Set.of(reservationApi);
    }
}
