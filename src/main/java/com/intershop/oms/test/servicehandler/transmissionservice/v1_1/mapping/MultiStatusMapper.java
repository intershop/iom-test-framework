package com.intershop.oms.test.servicehandler.transmissionservice.v1_1.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.transmission.v1_1.model.MultiStatus;
import com.intershop.oms.test.businessobject.OMSMultiStatus;

@Mapper()
public interface MultiStatusMapper
{
    MultiStatusMapper INSTANCE = Mappers.getMapper(MultiStatusMapper.class);

    OMSMultiStatus fromApiMultiStatus(MultiStatus multiStatus);

    @InheritInverseConfiguration
    public abstract MultiStatus toApiMultiStatus(OMSMultiStatus omsMultiStatus);
}