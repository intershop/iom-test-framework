package com.intershop.oms.test.servicehandler.scheduleservice.v1.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.schedule.v1.model.ScheduleCollectionContainer;
import com.intershop.oms.test.businessobject.schedule.OMSScheduleCollectionContainer;

@Mapper()
public interface ScheduleCollectionContainerMapper
{
    ScheduleCollectionContainerMapper INSTANCE = Mappers.getMapper(ScheduleCollectionContainerMapper.class);

    OMSScheduleCollectionContainer fromApiScheduleCollectionContainer(ScheduleCollectionContainer scheduleCollectionContainer);

    @InheritInverseConfiguration
    public abstract ScheduleCollectionContainer toApiScheduleCollectionContainer(OMSScheduleCollectionContainer omsScheduleCollectionContainer);
}