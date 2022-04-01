package com.intershop.oms.test.servicehandler.supplierservice.v1.mapping;

import com.intershop.oms.rest.communication.v2.model.DispatchPosition;
import com.intershop.oms.test.businessobject.communication.OMSDispatchPosition;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { ProductMapper.class, PropertyGroupMapper.class })
public interface DispatchPositionMapper
{
    DispatchPositionMapper INSTANCE = Mappers.getMapper(DispatchPositionMapper.class);

    DispatchPosition toApiDispatchPosition(OMSDispatchPosition omsDispatchPosition);
}