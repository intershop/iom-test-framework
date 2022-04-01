package com.intershop.oms.test.servicehandler.supplierservice.v2_11.mapping;

import com.intershop.oms.rest.communication.v2_11.model.DispatchItem;
import com.intershop.oms.test.businessobject.communication.OMSDispatchItem;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { PropertyGroupMapper.class })
public interface DispatchItemMapper
{
    DispatchItemMapper INSTANCE = Mappers.getMapper(DispatchItemMapper.class);

    OMSDispatchItem fromApiDispatchItem(DispatchItem dispatchItem);

    @InheritInverseConfiguration
    public abstract DispatchItem toApiDispatchItem(OMSDispatchItem omsDispatchItem);
}
