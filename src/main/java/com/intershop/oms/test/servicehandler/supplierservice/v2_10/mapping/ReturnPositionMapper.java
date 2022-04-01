package com.intershop.oms.test.servicehandler.supplierservice.v2_10.mapping;

import com.intershop.oms.rest.communication.v2_10.model.ReturnPosition;
import com.intershop.oms.test.businessobject.communication.OMSReturnPosition;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { ProductMapper.class, PropertyGroupMapper.class })
public interface ReturnPositionMapper
{
    ReturnPositionMapper INSTANCE = Mappers.getMapper(ReturnPositionMapper.class);

    OMSReturnPosition fromApiReturnPosition(ReturnPosition modelReturnPosition);

    @InheritInverseConfiguration
    public abstract ReturnPosition toApiReturnPosition(OMSReturnPosition omsReturnPosition);
}