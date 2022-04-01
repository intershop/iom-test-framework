package com.intershop.oms.test.servicehandler.supplierservice.v2_10.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.communication.v2_10.model.ErrorReport;
import com.intershop.oms.test.businessobject.OMSErrorReport;

@Mapper
public interface ErrorReportMapper
{
    ErrorReportMapper INSTANCE = Mappers.getMapper(ErrorReportMapper.class);

    OMSErrorReport fromApiErrorReport(ErrorReport errorReport);

    @InheritInverseConfiguration
    public abstract ErrorReport toApiErrorReport(OMSErrorReport omsErrorReport);
}
