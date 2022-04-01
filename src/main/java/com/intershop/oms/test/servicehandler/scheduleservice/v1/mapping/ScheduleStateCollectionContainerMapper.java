package com.intershop.oms.test.servicehandler.scheduleservice.v1.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.schedule.v1.model.ScheduleStateCollectionContainer;
import com.intershop.oms.test.businessobject.schedule.OMSScheduleStateCollectionContainer;

@Mapper(uses={ScheduleStateMapper.class})
public interface ScheduleStateCollectionContainerMapper
{
    ScheduleStateCollectionContainerMapper INSTANCE = Mappers.getMapper(ScheduleStateCollectionContainerMapper.class);

    OMSScheduleStateCollectionContainer fromApiScheduleStateCollectionContainer(ScheduleStateCollectionContainer scheduleStateCollectionContainer);

    @InheritInverseConfiguration
    public abstract ScheduleStateCollectionContainer toApiScheduleStateCollectionContainer(OMSScheduleStateCollectionContainer omsScheduleStateCollectionContainer);
}