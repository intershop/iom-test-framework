package com.intershop.oms.test.servicehandler.rmaservice.v2_12.mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.rma.v2_12.model.ReadCustomAttribute;
import com.intershop.oms.rest.rma.v2_12.model.WriteCustomAttribute;

@Mapper
public interface CustomAttributesMapper
{
    CustomAttributesMapper INSTANCE = Mappers.getMapper(CustomAttributesMapper.class);

    default Map<String, String> mapReadCustomAttributes(List<ReadCustomAttribute> customAttributes)
    {
        return customAttributes.stream()
            .collect(Collectors.toMap(ReadCustomAttribute::getKey, ReadCustomAttribute::getValue, (t, u) -> t, HashMap::new));
    }

    default List<ReadCustomAttribute> toApiReadCustomAttributes(Map<String,String>  customAttributes)
    {
        return customAttributes.entrySet().stream().map( in ->
            {
                ReadCustomAttribute wca = new ReadCustomAttribute();
                wca.setKey(in.getKey());
                wca.setValue(in.getValue());
                return wca;
            }).toList();
    }

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