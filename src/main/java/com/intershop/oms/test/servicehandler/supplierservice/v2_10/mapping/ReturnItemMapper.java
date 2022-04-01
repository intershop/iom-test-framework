package com.intershop.oms.test.servicehandler.supplierservice.v2_10.mapping;

import com.intershop.oms.rest.communication.v2_10.model.ReturnItem;
import com.intershop.oms.test.businessobject.communication.OMSReturnItem;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { PropertyGroupMapper.class })
public interface ReturnItemMapper
{
    ReturnItemMapper INSTANCE = Mappers.getMapper(ReturnItemMapper.class);

    OMSReturnItem fromApiReturnItem(ReturnItem modelReturnItem);

    @InheritInverseConfiguration
    public abstract ReturnItem toApiReturnItem(OMSReturnItem omsReturnItem);
}