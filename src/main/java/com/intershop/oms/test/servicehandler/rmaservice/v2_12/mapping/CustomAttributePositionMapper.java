package com.intershop.oms.test.servicehandler.rmaservice.v2_12.mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.rma.v2_12.model.WriteCustomAttribute;

@Mapper
public interface CustomAttributePositionMapper extends CustomAttributeMapper  
{
    CustomAttributePositionMapper INSTANCE = Mappers.getMapper(CustomAttributePositionMapper.class);

    default Map<String, String> mapWriteCustomAttributes(List<WriteCustomAttribute> customAttributes)
    {
        return customAttributes.stream()
            .collect(Collectors.toMap(WriteCustomAttribute::getKey, WriteCustomAttribute::getValue, (t, u) -> t, HashMap::new));
    }
    
    default List<WriteCustomAttribute> toApiWriteCustomAttributes(Map<String, String> customAttributes)
    {
        return customAttributes.entrySet().stream().map( in ->
            {
                WriteCustomAttribute wca = new WriteCustomAttribute();
                wca.setKey(in.getKey());
                wca.setValue(in.getValue());
                return wca;
            }).toList();
    }
}