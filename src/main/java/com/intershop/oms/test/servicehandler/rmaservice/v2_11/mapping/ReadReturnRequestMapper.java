package com.intershop.oms.test.servicehandler.rmaservice.v2_11.mapping;

import java.util.List;

import com.intershop.oms.test.businessobject.rma.OMSReturnRequest;
import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.rma.v2_11.model.ReadReturnRequest;
import com.intershop.oms.test.businessobject.rma.OMSReadReturnRequest;

@Mapper
public interface ReadReturnRequestMapper
{
    ReadReturnRequestMapper INSTANCE = Mappers.getMapper(ReadReturnRequestMapper.class);

    OMSReadReturnRequest fromApiReadReturnRequest(ReadReturnRequest readReturnRequest);

    @InheritInverseConfiguration
    ReadReturnRequest toApiReadReturnRequest(OMSReadReturnRequest omsReadReturnRequest);

    @AfterMapping
    default void fromApiReadReturnRequestList(final List<ReadReturnRequest> readReturnRequests, @MappingTarget final List<OMSReadReturnRequest> omsReadReturnRequests)
    {
        readReturnRequests.stream().forEach(readReturnRequest -> omsReadReturnRequests.add(fromApiReadReturnRequest(readReturnRequest)));
    }
}