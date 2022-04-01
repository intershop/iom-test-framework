package com.intershop.oms.test.servicehandler.supplierservice.v2_11.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.communication.v2_11.model.ConfirmedDelivery;
import com.intershop.oms.test.businessobject.communication.OMSConfirmedDelivery;

@Mapper
public interface ConfirmedDeliveryMapper
{
    ConfirmedDeliveryMapper INSTANCE = Mappers.getMapper(ConfirmedDeliveryMapper.class);

    OMSConfirmedDelivery fromApiConfirmedDelivery(ConfirmedDelivery confirmedDelivery);

    @InheritInverseConfiguration
    public abstract ConfirmedDelivery toApiConfirmedDelivery(OMSConfirmedDelivery omsConfirmedDelivery);
}
