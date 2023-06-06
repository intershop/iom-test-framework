package com.intershop.oms.test.servicehandler.orderservice.v2_4.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.order.v2_4.model.Tax;
import com.intershop.oms.test.businessobject.prices.OMSTax;

@Mapper
public interface TaxMapper
{
    TaxMapper INSTANCE = Mappers.getMapper(TaxMapper.class);
    OMSTax fromApiTax(Tax tax);

    @InheritInverseConfiguration
    public abstract Tax toApiTax(OMSTax omsTax);
}