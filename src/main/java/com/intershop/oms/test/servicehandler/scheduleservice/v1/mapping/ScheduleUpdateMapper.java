package com.intershop.oms.test.servicehandler.scheduleservice.v1.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.schedule.v1.model.ScheduleUpdate;
import com.intershop.oms.test.businessobject.schedule.OMSScheduleUpdate;

@Mapper
public interface ScheduleUpdateMapper
{
    ScheduleUpdateMapper INSTANCE = Mappers.getMapper(ScheduleUpdateMapper.class);

    OMSScheduleUpdate fromApiScheduleUpdate(ScheduleUpdate scheduleUpdate);

    @InheritInverseConfiguration
    public abstract ScheduleUpdate toApiScheduleUpdate(OMSScheduleUpdate omsScheduleUpdate);
}