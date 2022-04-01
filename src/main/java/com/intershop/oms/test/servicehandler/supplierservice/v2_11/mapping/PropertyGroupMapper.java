package com.intershop.oms.test.servicehandler.supplierservice.v2_11.mapping;

import com.intershop.oms.rest.communication.v2_11.model.Property;
import com.intershop.oms.rest.communication.v2_11.model.PropertyGroup;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper
public interface PropertyGroupMapper
{
    PropertyGroupMapper INSTANCE = Mappers.getMapper(PropertyGroupMapper.class);

    static Map<String, Map<String, String>> fromApiPropertyGroup(List<PropertyGroup> propertyGroup)
    {
        Map<String, Map<String, String>> mappedProperties = new HashMap<>();
        for (PropertyGroup group : propertyGroup)
        {
            Map<String, String> mappedGroup = new HashMap<>();
            group.getProperties().forEach(prop -> mappedGroup.put(prop.getKey(), prop.getValue()));
            mappedProperties.put(group.getName(), mappedGroup);
        }
        return mappedProperties;
    }

    static List<PropertyGroup> toApiPropertyGroup(Map<String, Map<String, String>> omsPropertyGroup)
    {
        return omsPropertyGroup.entrySet().stream().map(entry -> {
            PropertyGroup propGrp = new PropertyGroup();
            propGrp.setName(entry.getKey());
            entry.getValue().forEach((key, value) -> {
                Property prop = new Property();
                prop.setKey(key);
                prop.setValue(value);
                propGrp.getProperties().add(prop);
            });
            return propGrp;
        }).collect(Collectors.toCollection(ArrayList::new));
    }
}