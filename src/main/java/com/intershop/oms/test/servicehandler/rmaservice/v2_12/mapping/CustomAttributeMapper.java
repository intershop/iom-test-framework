package com.intershop.oms.test.servicehandler.rmaservice.v2_12.mapping;

import java.util.Collection;
import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.rma.v2_12.model.ReadCustomAttribute;
import com.intershop.oms.rest.rma.v2_12.model.WriteCustomAttribute;
import com.intershop.oms.test.businessobject.rma.OMSReadCustomAttribute;
import com.intershop.oms.test.businessobject.rma.OMSWriteCustomAttribute;

@Mapper
public interface CustomAttributeMapper
{
    CustomAttributeMapper INSTANCE = Mappers.getMapper(CustomAttributeMapper.class);

    OMSReadCustomAttribute fromApiCustomAttribute(ReadCustomAttribute writeCustomAttribute);

    List<OMSReadCustomAttribute> fromApiCustomAttributes(List<ReadCustomAttribute> response);

    @InheritInverseConfiguration
    WriteCustomAttribute toApiCustomAttribute(OMSWriteCustomAttribute omsWriteCustomAttribute);
    
    List<WriteCustomAttribute> toApiCustomAttributes(Collection<OMSWriteCustomAttribute> omsWriteCustomAttributes);
}