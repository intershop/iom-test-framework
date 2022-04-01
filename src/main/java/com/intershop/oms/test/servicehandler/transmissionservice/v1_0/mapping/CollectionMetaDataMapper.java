package com.intershop.oms.test.servicehandler.transmissionservice.v1_0.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.transmission.v1.model.CollectionMetaData;
import com.intershop.oms.test.businessobject.OMSCollectionMetaData;

@Mapper
public interface CollectionMetaDataMapper
{
    CollectionMetaDataMapper INSTANCE = Mappers.getMapper(CollectionMetaDataMapper.class);

    OMSCollectionMetaData fromApiCollectionMetaData(CollectionMetaData collectionMetaData);

    @InheritInverseConfiguration
    public abstract CollectionMetaData toApiCollectionMetaData(OMSCollectionMetaData omsCollectionMetaData);
}