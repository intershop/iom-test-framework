package com.intershop.oms.test.servicehandler.rmaservice.v2_12.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.rma.v2_12.model.WriteReturnRequest;
import com.intershop.oms.test.businessobject.rma.OMSWriteReturnRequest;

@Mapper(uses= {CustomAttributesMapper.class} )
public interface WriteReturnRequestMapper
{
    WriteReturnRequestMapper INSTANCE = Mappers.getMapper(WriteReturnRequestMapper.class);

    OMSWriteReturnRequest fromApiWriteReturnRequest(WriteReturnRequest writeReturnRequest);

    @InheritInverseConfiguration
    WriteReturnRequest toApiWriteReturnRequest(OMSWriteReturnRequest omsWriteReturnRequest);
}