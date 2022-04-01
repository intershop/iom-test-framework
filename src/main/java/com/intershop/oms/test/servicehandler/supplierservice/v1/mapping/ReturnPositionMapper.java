package com.intershop.oms.test.servicehandler.supplierservice.v1.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.communication.v2.model.ReturnPosition;
import com.intershop.oms.test.businessobject.communication.OMSReturnPosition;

@Mapper(uses={ProductMapper.class, PropertyGroupMapper.class})
public interface ReturnPositionMapper
{
    ReturnPositionMapper INSTANCE = Mappers.getMapper(ReturnPositionMapper.class);

    OMSReturnPosition fromApiReturnPosition(ReturnPosition modelReturnPosition);

    @InheritInverseConfiguration
    public abstract ReturnPosition toApiReturnPosition(OMSReturnPosition omsReturnPosition);
}