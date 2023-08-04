package com.intershop.oms.test.servicehandler.rmaservice.v2_12.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.rma.v2_12.model.WriteCustomAttribute;
import com.intershop.oms.test.businessobject.rma.OMSWriteCustomAttribute;

@Mapper
public interface CustomAttributeMapper
{
    CustomAttributeMapper INSTANCE = Mappers.getMapper(CustomAttributeMapper.class);

    OMSWriteCustomAttribute fromApiCustomAttribute(WriteCustomAttribute writeCustomAttribute);

    @InheritInverseConfiguration
    WriteCustomAttribute toApiCustomAttribute(OMSWriteCustomAttribute omsWriteCustomAttribute);
}