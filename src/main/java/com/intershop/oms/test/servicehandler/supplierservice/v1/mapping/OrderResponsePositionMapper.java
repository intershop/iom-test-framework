package com.intershop.oms.test.servicehandler.supplierservice.v1.mapping;

import com.intershop.oms.rest.communication.v2.model.OrderResponsePosition;
import com.intershop.oms.test.businessobject.communication.OMSOrderResponsePosition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { NetPurchasePriceMapper.class, ProductMapper.class, PropertyGroupMapper.class })
public interface OrderResponsePositionMapper
{
    OrderResponsePositionMapper INSTANCE = Mappers.getMapper(OrderResponsePositionMapper.class);

    OrderResponsePosition toApiOrderResponsePosition(OMSOrderResponsePosition omsOrderResponsePosition);
}