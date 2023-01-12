package com.intershop.oms.test.servicehandler.inventoryservice.v2_0.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.reservation.v2_0.model.ReservationState;
import com.intershop.oms.test.businessobject.OMSReservation;

@Mapper
public  class ReservationStateMapper
{
    ReservationStateMapper INSTANCE = Mappers.getMapper(ReservationStateMapper.class);

    OMSReservation.RESERVATION_STATE fromApiReservationState(ReservationState reservationState)
    {
        return OMSReservation.RESERVATION_STATE.valueOf(reservationState.getValue());
    }

    @InheritInverseConfiguration
    ReservationState toApiReservationState(OMSReservation.RESERVATION_STATE omsReservationState)
    {
        return ReservationState.valueOf(omsReservationState.toString());
    }
}
