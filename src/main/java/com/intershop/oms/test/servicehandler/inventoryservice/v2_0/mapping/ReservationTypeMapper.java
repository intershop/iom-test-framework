package com.intershop.oms.test.servicehandler.inventoryservice.v2_0.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.reservation.v2_0.model.ReservationType;
import com.intershop.oms.test.businessobject.OMSReservation;

@Mapper
public  class ReservationTypeMapper
{
    ReservationTypeMapper INSTANCE = Mappers.getMapper(ReservationTypeMapper.class);

    OMSReservation.RESERVATION_TYPE fromApiReservationType(ReservationType reservationType)
    {
        return OMSReservation.RESERVATION_TYPE.valueOf(reservationType.getValue());
    }

    @InheritInverseConfiguration
    ReservationType toApiReservation(OMSReservation.RESERVATION_TYPE omsReservationType)
    {
        return ReservationType.valueOf(omsReservationType.toString());
    }
}
