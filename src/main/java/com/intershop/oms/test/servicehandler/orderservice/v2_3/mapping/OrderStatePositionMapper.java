package com.intershop.oms.test.servicehandler.orderservice.v2_3.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.mapstruct.ReportingPolicy;

import com.intershop.oms.rest.order.v2_3.model.OrderStatePosition;
import com.intershop.oms.test.businessobject.order.OMSOrderPosition;

@Mapper(uses = {TaxMapper.class },unmappedTargetPolicy = ReportingPolicy.WARN)
public interface OrderStatePositionMapper
{
    OrderStatePositionMapper INSTANCE = Mappers.getMapper(OrderStatePositionMapper.class);

    @Mapping(target = "sum", ignore = true)
    @Mapping(target = "unitPrice", ignore = true)
    @Mapping(target = "shipping", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product.productId", ignore = true)
    @Mapping(source = "product.number", target = "product.number")
    OMSOrderPosition fromApiOrderStatePosition(OrderStatePosition orderStatePosition);
}