package com.intershop.oms.test.servicehandler.rmaservice.v2.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.rma.v2.model.WriteReturnRequest;
import com.intershop.oms.test.businessobject.rma.OMSWriteReturnRequest;

@Mapper
public interface WriteReturnRequestMapper
{
    WriteReturnRequestMapper INSTANCE = Mappers.getMapper(WriteReturnRequestMapper.class);

    OMSWriteReturnRequest fromApiWriteReturnRequest(WriteReturnRequest writeReturnRequest);

    @InheritInverseConfiguration
    public abstract WriteReturnRequest toApiWriteReturnRequest(OMSWriteReturnRequest omsWriteReturnRequest);
}