package com.intershop.oms.test.servicehandler.scheduleservice.v1.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.schedule.v1.model.SortDirection;
import com.intershop.oms.test.businessobject.OMSSortDirection;

@Mapper
public interface SortDirectionMapper
{
    SortDirectionMapper INSTANCE = Mappers.getMapper(SortDirectionMapper.class);

    OMSSortDirection fromApiSortDirection(SortDirection sortDirection);

    @InheritInverseConfiguration
    public abstract SortDirection toApiSortDirection(OMSSortDirection omsSortDirection);
}
