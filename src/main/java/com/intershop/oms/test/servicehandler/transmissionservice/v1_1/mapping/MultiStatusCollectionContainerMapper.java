package com.intershop.oms.test.servicehandler.transmissionservice.v1_1.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.transmission.v1_1.model.MultiStatusCollectionContainer;
import com.intershop.oms.test.businessobject.OMSMultiStatusCollectionContainer;

@Mapper(uses={MultiStatusMapper.class})
public interface MultiStatusCollectionContainerMapper
{
    MultiStatusCollectionContainerMapper INSTANCE = Mappers.getMapper(MultiStatusCollectionContainerMapper.class);

    OMSMultiStatusCollectionContainer fromApiMultiStatusCollectionContainer(MultiStatusCollectionContainer multiStatusCollectionContainer);

    @InheritInverseConfiguration
    public abstract MultiStatusCollectionContainer toApiMultiStatusCollectionContainer(OMSMultiStatusCollectionContainer omsMultiStatusCollectionContainer);
}