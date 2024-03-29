package com.intershop.oms.test.servicehandler.transmissionservice.v2_0.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.transmission.v2_0.model.TransmissionCollectionContainer;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionCollectionContainer;

@Mapper(uses={TransmissionMapper.class/*, TransmissionTypeGroupMapper.class*/, CollectionMetaDataMapper.class})
public interface TransmissionCollectionContainerMapper
{
    TransmissionCollectionContainerMapper INSTANCE = Mappers.getMapper(TransmissionCollectionContainerMapper.class);

    OMSTransmissionCollectionContainer fromApiTransmissionCollectionContainer(TransmissionCollectionContainer transmissionCollectionContainer);

    @InheritInverseConfiguration
    public abstract TransmissionCollectionContainer toApiTransmissionCollectionContainer(OMSTransmissionCollectionContainer omsTransmissionCollectionContainer);
}