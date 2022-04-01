package com.intershop.oms.test.servicehandler.supplierservice.v2_11.mapping;

import com.intershop.oms.rest.communication.v2_11.model.DispatchPosition;
import com.intershop.oms.test.businessobject.communication.OMSDispatchPosition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = PropertyGroupMapper.class)
public interface DispatchPositionMapper
{
    DispatchPositionMapper INSTANCE = Mappers.getMapper(DispatchPositionMapper.class);

    DispatchPosition toApiDispatchPosition(OMSDispatchPosition omsDispatchPosition);
}