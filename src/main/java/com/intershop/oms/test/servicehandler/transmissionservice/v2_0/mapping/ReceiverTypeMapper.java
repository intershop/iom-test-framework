package com.intershop.oms.test.servicehandler.transmissionservice.v2_0.mapping;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.control.MappingControl;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.test.businessobject.transmission.OMSReceiverType;

@Mapper
public interface ReceiverTypeMapper
{
    ReceiverTypeMapper INSTANCE = Mappers.getMapper(ReceiverTypeMapper.class);

    @Mapping(target = "value", expression = "java(receiverType)")
    OMSReceiverType fromApiReceiverType(String receiverType);

    default String toApiReceiverType(OMSReceiverType omsReceiverType)
    {
        return omsReceiverType.getValue();
    }

    public static void main(String[] args)
    {
        OMSReceiverType oms = ReceiverTypeMapper.INSTANCE.fromApiReceiverType("OMS");
        System.out.println(oms);
        System.out.println(ReceiverTypeMapper.INSTANCE.toApiReceiverType(oms));
    }
}