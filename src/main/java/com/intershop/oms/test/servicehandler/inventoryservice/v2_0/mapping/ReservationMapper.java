package com.intershop.oms.test.servicehandler.inventoryservice.v2_0.mapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.reservation.v2_0.model.ReservationRequest;
import com.intershop.oms.rest.reservation.v2_0.model.ReservationResponse;
import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.test.businessobject.OMSReservation;

@Mapper(uses= {ReservationItemMapper.class, ReservationTypeMapper.class, ReservationStateMapper.class})
public interface ReservationMapper
{
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    // ignore request parameters
    @Mapping(target = "lifetime", ignore = true)
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "shop", ignore = true)
    @Mapping(target = "validUntil", ignore = true)
    OMSReservation fromApiReservation(ReservationResponse reservation) throws ApiException;

    // ignore response values
    @BeanMapping(ignoreUnmappedSourceProperties = { "validUntil", "resvId", "shop" } )
    @InheritInverseConfiguration
    ReservationRequest toApiReservation(OMSReservation omsReservation);

    @AfterMapping
    default void mapValidUntil(ReservationResponse reservation, @MappingTarget OMSReservation omsReservation) throws ApiException
    {
        Date date;
        try
        {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(reservation.getValidUntil());
        }
        catch (ParseException e)
        {
            throw new ApiException("Cannot parse validUntil date '"+reservation.getValidUntil()+"'!");
        }
        omsReservation.setValidUntil(
                        Instant.ofEpochMilli(date.getTime())
                            .atZone(ZoneId.systemDefault())
                            .toOffsetDateTime());
    }
}