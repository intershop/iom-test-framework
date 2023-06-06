package com.intershop.oms.test.servicehandler.orderservice.v2_4.mapping;

import com.intershop.oms.test.businessobject.order.OMSChangeRequest;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.order.v2_4.model.ChangeRequestCreate;

@Mapper(uses= {OrderMapper.class})
public interface ChangeRequestCreateMapper {
    ChangeRequestCreateMapper INSTANCE = Mappers.getMapper(ChangeRequestCreateMapper.class);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "id", ignore = true)
    OMSChangeRequest fromApiChangeRequestCreate(ChangeRequestCreate changeRequestCreate);

    @InheritInverseConfiguration
    public abstract ChangeRequestCreate toApiChangeRequestCreate(OMSChangeRequest omsChangeRequestCreate);
}
