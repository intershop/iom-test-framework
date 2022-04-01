package com.intershop.oms.test.servicehandler.transmissionservice.v1_1.mapping;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.transmission.v1_1.model.ReceiverType;
import com.intershop.oms.test.businessobject.transmission.OMSReceiverType;

@Mapper
public interface ReceiverTypeMapper
{
    ReceiverTypeMapper INSTANCE = Mappers.getMapper(ReceiverTypeMapper.class);

    OMSReceiverType fromApiReceiverType(ReceiverType ReceiverType);

    @InheritInverseConfiguration
    public abstract ReceiverType toApiReceiverType(OMSReceiverType omsReceiverType);

    @AfterMapping
    public default void fromApiReceiverTypeList(final List<ReceiverType> receiverTypes, @MappingTarget final List<OMSReceiverType> omsReceiverTypes)
    {
        receiverTypes.stream().forEach(receiverType -> omsReceiverTypes.add(fromApiReceiverType(receiverType)));
    }

    @AfterMapping
    public default void toApiReceiverTypeList(final List<OMSReceiverType> omsReceiverTypes, @MappingTarget final List<ReceiverType> receiverTypes)
    {
        omsReceiverTypes.stream().forEach(omsReceiverType -> receiverTypes.add(toApiReceiverType(omsReceiverType)));
    }
}