package com.intershop.oms.test.servicehandler.supplierservice.v2_10.mapping;

import com.intershop.oms.rest.communication.v2_10.model.OrderResponsePosition;
import com.intershop.oms.test.businessobject.communication.OMSOrderResponsePosition;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { NetPurchasePriceMapper.class, ProductMapper.class, PropertyGroupMapper.class })
public interface OrderResponsePositionMapper
{
    OrderResponsePositionMapper INSTANCE = Mappers.getMapper(OrderResponsePositionMapper.class);

    OMSOrderResponsePosition fromApiOrderResponsePosition(OrderResponsePosition orderResponsePosition);

    @InheritInverseConfiguration
    public abstract OrderResponsePosition toApiOrderResponsePosition(OMSOrderResponsePosition omsOrderResponsePosition);
}