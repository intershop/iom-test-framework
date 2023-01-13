package com.intershop.oms.test.servicehandler.inventoryservice.v2_0.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.reservation.v2_0.model.ReservationType;
import com.intershop.oms.test.businessobject.OMSReservationType;

@Mapper
public interface ReservationTypeMapper
{
    ReservationTypeMapper INSTANCE = Mappers.getMapper(ReservationTypeMapper.class);

    OMSReservationType fromApiReservationType(ReservationType reservationType);

    @InheritInverseConfiguration
    ReservationType toApiReservation(OMSReservationType omsReservationType);
}
