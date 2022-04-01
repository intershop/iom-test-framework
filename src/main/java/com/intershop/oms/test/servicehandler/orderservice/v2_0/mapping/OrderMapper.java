package com.intershop.oms.test.servicehandler.orderservice.v2_0.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.order.v2_0.model.Order;
import com.intershop.oms.test.businessobject.order.OMSOrder;

@Mapper(uses= {AddressLocationMapper.class, OrderPositionMapper.class, TaxMapper.class})
public interface OrderMapper
{
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "testCaseId", ignore = true)
    @Mapping(target = "shop", ignore = true)
    @Mapping(source = "additionalAttributes", target = "propertyGroups")
    OMSOrder fromApiOrder(Order order);

    @InheritInverseConfiguration
    Order toApiOrder(OMSOrder omsOrder);
}