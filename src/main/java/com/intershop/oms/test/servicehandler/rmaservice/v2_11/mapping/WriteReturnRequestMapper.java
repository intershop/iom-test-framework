package com.intershop.oms.test.servicehandler.rmaservice.v2_11.mapping;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.rma.v2_11.model.WriteReturnRequest;
import com.intershop.oms.test.businessobject.rma.OMSWriteReturnRequest;

@Mapper
public interface WriteReturnRequestMapper
{
    WriteReturnRequestMapper INSTANCE = Mappers.getMapper(WriteReturnRequestMapper.class);

    @BeanMapping(ignoreUnmappedSourceProperties = "contactPersons")
    OMSWriteReturnRequest fromApiWriteReturnRequest(WriteReturnRequest writeReturnRequest);

    @InheritInverseConfiguration
    @Mapping(target = "contactPersons", ignore = true) // introduced with v2.11
    WriteReturnRequest toApiWriteReturnRequest(OMSWriteReturnRequest omsWriteReturnRequest);
}