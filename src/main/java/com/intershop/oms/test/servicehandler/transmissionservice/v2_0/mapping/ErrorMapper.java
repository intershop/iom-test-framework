package com.intershop.oms.test.servicehandler.transmissionservice.v2_0.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.transmission.v2_0.model.Error;
import com.intershop.oms.test.businessobject.OMSError;

@Mapper
public interface ErrorMapper
{
    ErrorMapper INSTANCE = Mappers.getMapper(ErrorMapper.class);

    @Mapping(target = "value", ignore = true)
    OMSError fromApiError(Error error);

    @InheritInverseConfiguration
    public abstract Error toApiError(OMSError omsError);
}
