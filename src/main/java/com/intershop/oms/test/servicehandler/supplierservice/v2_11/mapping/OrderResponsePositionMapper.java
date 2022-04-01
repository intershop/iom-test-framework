package com.intershop.oms.test.servicehandler.supplierservice.v2_11.mapping;

import com.intershop.oms.rest.communication.v2_11.model.OrderResponsePosition;
import com.intershop.oms.test.businessobject.communication.OMSOrderResponsePosition;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { NetPurchasePriceMapper.class, PropertyGroupMapper.class })
public interface OrderResponsePositionMapper
{
    OrderResponsePositionMapper INSTANCE = Mappers.getMapper(OrderResponsePositionMapper.class);

    OMSOrderResponsePosition fromApiOrderResponsePosition(OrderResponsePosition orderResponsePosition);

    @InheritInverseConfiguration
    public abstract OrderResponsePosition toApiOrderResponsePosition(OMSOrderResponsePosition omsOrderResponsePosition);
}