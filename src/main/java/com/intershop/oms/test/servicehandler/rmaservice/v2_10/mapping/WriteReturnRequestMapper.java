package com.intershop.oms.test.servicehandler.rmaservice.v2_10.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.rma.v2_10.model.WriteReturnRequest;
import com.intershop.oms.test.businessobject.rma.OMSWriteReturnRequest;

@Mapper
public interface WriteReturnRequestMapper
{
    WriteReturnRequestMapper INSTANCE = Mappers.getMapper(WriteReturnRequestMapper.class);

    @Mapping(target = "contactPersons", ignore = true) // introduced with v2.11
    OMSWriteReturnRequest fromApiWriteReturnRequest(WriteReturnRequest writeReturnRequest);

    @InheritInverseConfiguration
    public abstract WriteReturnRequest toApiWriteReturnRequest(OMSWriteReturnRequest omsWriteReturnRequest);
}