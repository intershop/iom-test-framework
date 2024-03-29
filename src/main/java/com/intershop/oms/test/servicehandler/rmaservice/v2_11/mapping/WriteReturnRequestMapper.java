package com.intershop.oms.test.servicehandler.rmaservice.v2_11.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.rma.v2_11.model.WriteReturnRequest;
import com.intershop.oms.test.businessobject.rma.OMSWriteReturnRequest;
import com.intershop.oms.test.servicehandler.rmaservice.v2_11.mapping.WriteReturnRequestPositionMapper;

@Mapper( uses = { WriteReturnRequestPositionMapper.class } )
public interface WriteReturnRequestMapper
{
    WriteReturnRequestMapper INSTANCE = Mappers.getMapper(WriteReturnRequestMapper.class);

    @Mapping(target = "customAttributes", ignore = true) // introduced in 2.12
    OMSWriteReturnRequest fromApiWriteReturnRequest(WriteReturnRequest writeReturnRequest);

    @InheritInverseConfiguration
    WriteReturnRequest toApiWriteReturnRequest(OMSWriteReturnRequest omsWriteReturnRequest);
}