package com.intershop.oms.test.servicehandler.reservationservice.v2_0.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.reservation.v2_0.model.ReservationItem;
import com.intershop.oms.test.businessobject.OMSReservationItem;

@Mapper
public interface ReservationItemMapper
{
    ReservationItemMapper INSTANCE = Mappers.getMapper(ReservationItemMapper.class);

    OMSReservationItem fromApiReservationItem(ReservationItem reservationItem);

    @InheritInverseConfiguration
    ReservationItem toApiReservationItem(OMSReservationItem omsReservationItem);
}