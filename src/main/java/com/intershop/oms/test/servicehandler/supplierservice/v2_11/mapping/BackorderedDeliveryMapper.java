package com.intershop.oms.test.servicehandler.supplierservice.v2_11.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.communication.v2_11.model.BackorderedDelivery;
import com.intershop.oms.test.businessobject.communication.OMSBackorderedDelivery;

@Mapper
public interface BackorderedDeliveryMapper
{
    BackorderedDeliveryMapper INSTANCE = Mappers.getMapper(BackorderedDeliveryMapper.class);

    OMSBackorderedDelivery fromApiBackorderedDelivery(BackorderedDelivery backorderedDelivery);

    @InheritInverseConfiguration
    public abstract BackorderedDelivery toApiBackorderedDelivery(OMSBackorderedDelivery omsBackorderedDelivery);
}
