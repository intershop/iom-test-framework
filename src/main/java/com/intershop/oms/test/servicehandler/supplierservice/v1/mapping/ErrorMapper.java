package com.intershop.oms.test.servicehandler.supplierservice.v1.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.communication.v2.model.Error;
import com.intershop.oms.test.businessobject.OMSError;

@Mapper
public interface ErrorMapper
{
    ErrorMapper INSTANCE = Mappers.getMapper(ErrorMapper.class);

    OMSError fromApiError(Error error);

    @InheritInverseConfiguration
    public abstract Error toApiError(OMSError omsError);
}
