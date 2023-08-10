package com.intershop.oms.test.businessobject.rma.mapper;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.test.businessobject.rma.OMSCustomAttribute;
import com.intershop.oms.test.businessobject.rma.OMSWriteCustomAttribute;

@Mapper
public interface OMSCustomAttributeMapper  
{
    OMSCustomAttributeMapper INSTANCE = Mappers.getMapper(OMSCustomAttributeMapper.class);

    default Map<String, String> fromReadCustomAttributes(Collection<OMSCustomAttribute> customAttributes)
    {
        return customAttributes.stream()
            .collect(Collectors.toMap(OMSCustomAttribute::getKey, OMSCustomAttribute::getValue, (t, u) -> t, HashMap::new));
    }
    
    default List<OMSWriteCustomAttribute> toWriteCustomAttributes(Map<String, String> customAttributes)
    {
        return customAttributes.entrySet().stream().map( in ->
            {
                OMSWriteCustomAttribute wca = new OMSWriteCustomAttribute();
                wca.setKey(in.getKey());
                wca.setValue(in.getValue());
                return wca;
            }).toList();
    }
}