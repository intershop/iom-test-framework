package com.intershop.oms.test.servicehandler.supplierservice.v1.mapping;

import com.intershop.oms.rest.communication.v2.model.DispatchItem;
import com.intershop.oms.test.businessobject.communication.OMSDispatchItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = PropertyGroupMapper.class)
public interface DispatchItemMapper
{
    DispatchItemMapper INSTANCE = Mappers.getMapper(DispatchItemMapper.class);

    DispatchItem toApiDispatchItem(OMSDispatchItem omsDispatchItem);
}
