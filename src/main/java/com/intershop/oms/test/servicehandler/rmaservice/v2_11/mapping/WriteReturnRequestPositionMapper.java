package com.intershop.oms.test.servicehandler.rmaservice.v2_11.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.rma.v2_11.model.WriteReturnRequestPosition;
import com.intershop.oms.test.businessobject.rma.OMSWriteReturnRequestPosition;

@Mapper
public interface WriteReturnRequestPositionMapper
{
    WriteReturnRequestPositionMapper INSTANCE = Mappers.getMapper(WriteReturnRequestPositionMapper.class);

    @Mapping(target = "customAttributes", ignore = true) // introduced in 2.12
    OMSWriteReturnRequestPosition fromApiWriteReturnRequestPosition(WriteReturnRequestPosition writeReturnRequestPosition);

    @InheritInverseConfiguration
    public abstract WriteReturnRequestPosition toApiWriteReturnRequestPosition(OMSWriteReturnRequestPosition omsWriteReturnRequestPosition);
}