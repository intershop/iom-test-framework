package com.intershop.oms.test.servicehandler.scheduleservice.v1.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.schedule.v1.model.ScheduleState;
import com.intershop.oms.test.businessobject.schedule.OMSScheduleState;

@Mapper
public interface ScheduleStateMapper
{
    ScheduleStateMapper INSTANCE = Mappers.getMapper(ScheduleStateMapper.class);

    OMSScheduleState fromApiScheduleState(ScheduleState scheduleUpdate);

    @InheritInverseConfiguration
    public abstract ScheduleState toApiScheduleState(OMSScheduleState omsScheduleState);
}