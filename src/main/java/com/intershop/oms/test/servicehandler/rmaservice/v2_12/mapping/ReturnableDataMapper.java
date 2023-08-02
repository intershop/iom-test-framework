package com.intershop.oms.test.servicehandler.rmaservice.v2_12.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.rma.v2_12.model.ReturnableData;
import com.intershop.oms.test.businessobject.rma.OMSReturnableData;

@Mapper
public interface ReturnableDataMapper
{
    ReturnableDataMapper INSTANCE = Mappers.getMapper(ReturnableDataMapper.class);

    OMSReturnableData fromApiReturnableData(ReturnableData returnableData);

    @InheritInverseConfiguration
    ReturnableData toApiReturnableData(OMSReturnableData omsReturnableData);
}