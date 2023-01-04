package com.intershop.oms.test.servicehandler.reservationservice.v2_0.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.reservation.v2_0.model.Reservation;
import com.intershop.oms.test.businessobject.OMSReservation;

public interface ReservationMapper
{
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    OMSReservation fromApiReservation(Reservation reservation);

    @InheritInverseConfiguration
    Reservation toApiReservation(OMSReservation omsReservation);
}