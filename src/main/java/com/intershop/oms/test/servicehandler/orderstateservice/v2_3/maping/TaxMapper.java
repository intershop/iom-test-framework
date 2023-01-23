package com.intershop.oms.test.servicehandler.orderstateservice.v2_3.maping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.order.v2_3.model.Tax;
import com.intershop.oms.test.businessobject.prices.OMSTax;

@Mapper
public interface TaxMapper
{
    TaxMapper INSTANCE = Mappers.getMapper(TaxMapper.class);
//
//    @Mapping(target = "rate", ignore = true)
//    @Mapping(target = "location", ignore = true)
    OMSTax fromApiTax(Tax tax);

    @InheritInverseConfiguration
    public abstract Tax toApiTax(OMSTax omsTax);
}