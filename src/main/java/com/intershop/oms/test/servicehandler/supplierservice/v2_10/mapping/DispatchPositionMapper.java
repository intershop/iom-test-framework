package com.intershop.oms.test.servicehandler.supplierservice.v2_10.mapping;

import com.intershop.oms.rest.communication.v2_10.model.DispatchPosition;
import com.intershop.oms.test.businessobject.communication.OMSDispatchPosition;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { ProductMapper.class, PropertyGroupMapper.class })
public interface DispatchPositionMapper
{
    DispatchPositionMapper INSTANCE = Mappers.getMapper(DispatchPositionMapper.class);

    @Mapping(target = "articleNo", ignore = true)
    @Mapping(target = "id", ignore = true)
    OMSDispatchPosition fromApiDispatchPosition(DispatchPosition dispatchPosition);

    @InheritInverseConfiguration
    public abstract DispatchPosition toApiDispatchPosition(OMSDispatchPosition omsDispatchPosition);
}