package com.intershop.oms.test.servicehandler.orderservice.v2_2.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.order.v2_2.model.OrderPosition;
import com.intershop.oms.test.businessobject.order.OMSOrderPosition;

@Mapper
public interface OrderPositionMapper
{
    OrderPositionMapper INSTANCE = Mappers.getMapper(OrderPositionMapper.class);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "ordered", ignore = true)
    @Mapping(target = "commissioned", ignore = true)
    @Mapping(target = "confirmed", ignore = true)
    @Mapping(target = "dispatched", ignore = true)
    @Mapping(target = "returned", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usedTaxes", ignore = true)
    @Mapping(target = "product.productId", ignore = true)
    OMSOrderPosition fromApiOrderPosition(OrderPosition orderPosition);

    @InheritInverseConfiguration
    OrderPosition toApiOrderPosition(OMSOrderPosition omsOrderPosition);
}