package com.intershop.oms.test.servicehandler.orderstateservice.v2_0.mapping;

import com.intershop.oms.rest.order.v2_0.model.OrderStatePosition;
import com.intershop.oms.test.businessobject.order.OMSOrderPosition;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {TaxMapper.class, OrderPositionReturnedMapper.class})
public interface OrderStatePositionMapper
{
    OrderStatePositionMapper INSTANCE = Mappers.getMapper(OrderStatePositionMapper.class);

    @Mapping(target = "quantity", ignore = true)
    @Mapping(target = "sum", ignore = true)
    @Mapping(target = "unitPrice", ignore = true)
    @Mapping(target = "shipping", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product.productId", ignore = true)
    OMSOrderPosition fromApiOrderStatePosition(OrderStatePosition orderStatePosition);
}