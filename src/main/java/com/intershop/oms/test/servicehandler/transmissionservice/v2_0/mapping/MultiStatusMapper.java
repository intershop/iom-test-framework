package com.intershop.oms.test.servicehandler.transmissionservice.v2_0.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.transmission.v2_0.model.MultiStatus;
import com.intershop.oms.test.businessobject.OMSMultiStatus;

@Mapper(uses = { ErrorMapper.class })
public interface MultiStatusMapper
{
    MultiStatusMapper INSTANCE = Mappers.getMapper(MultiStatusMapper.class);

    OMSMultiStatus fromApiMultiStatus(MultiStatus multiStatus);

    @InheritInverseConfiguration
    public abstract MultiStatus toApiMultiStatus(OMSMultiStatus omsMultiStatus);
}