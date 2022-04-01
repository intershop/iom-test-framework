package com.intershop.oms.test.servicehandler.scheduleservice.v1.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.schedule.v1.model.SortableScheduleAttribute;
import com.intershop.oms.test.businessobject.schedule.OMSSortableScheduleAttribute;

@Mapper
public interface SortableScheduleAttributeMapper
{
    SortableScheduleAttributeMapper INSTANCE = Mappers.getMapper(SortableScheduleAttributeMapper.class);

    OMSSortableScheduleAttribute fromApiSortableScheduleAttribute(SortableScheduleAttribute sortableScheduleAttribute);

    @InheritInverseConfiguration
    public abstract SortableScheduleAttribute toApiSortableScheduleAttribute(OMSSortableScheduleAttribute omsSortableScheduleAttribute);
}
