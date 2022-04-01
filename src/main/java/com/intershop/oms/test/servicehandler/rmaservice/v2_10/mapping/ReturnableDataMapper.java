package com.intershop.oms.test.servicehandler.rmaservice.v2_10.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.rma.v2_10.model.ReturnableData;
import com.intershop.oms.test.businessobject.rma.OMSReturnableData;

@Mapper
public interface ReturnableDataMapper
{
    ReturnableDataMapper INSTANCE = Mappers.getMapper(ReturnableDataMapper.class);

    OMSReturnableData fromApiReturnableData(ReturnableData returnableData);

    @InheritInverseConfiguration
    public abstract ReturnableData toApiReturnableData(OMSReturnableData omsReturnableData);
}