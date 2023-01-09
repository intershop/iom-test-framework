package com.intershop.oms.test.servicehandler.inventoryservice.v2_0.mapping;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.reservation.v2_0.model.ReservationRequest;
import com.intershop.oms.rest.reservation.v2_0.model.ReservationResponse;
import com.intershop.oms.test.businessobject.OMSReservation;

@Mapper(uses= {ReservationItemMapper.class})
public interface ReservationMapper
{
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lifetime", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "validUntil", dateFormat = "yyyy-MM-dd HH:mm:ss")
    OMSReservation fromApiReservation(ReservationResponse reservation);

    @InheritInverseConfiguration
    @BeanMapping(ignoreUnmappedSourceProperties = { "validUntil", "lifetime", "id", "resvId" } )
    ReservationRequest toApiReservation(OMSReservation omsReservation);
}