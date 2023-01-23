package com.intershop.oms.test.servicehandler.inventoryservice.v2_0.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.reservation.v2_0.model.ReservationState;
import com.intershop.oms.test.businessobject.OMSReservationState;

@Mapper
public interface ReservationStateMapper
{
    ReservationStateMapper INSTANCE = Mappers.getMapper(ReservationStateMapper.class);

    OMSReservationState fromApiReservationState(ReservationState reservationState);

    @InheritInverseConfiguration
    ReservationState toApiReservationState(OMSReservationState omsReservationState);
}
