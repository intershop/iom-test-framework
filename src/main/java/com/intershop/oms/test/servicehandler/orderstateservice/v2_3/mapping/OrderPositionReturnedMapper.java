package com.intershop.oms.test.servicehandler.orderstateservice.v2_3.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.order.v2_3.model.OrderPositionReturned;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderPositionReturned;

@Mapper(uses = TaxMapper.class)
public interface OrderPositionReturnedMapper
{
    OrderPositionReturnedMapper INSTANCE = Mappers.getMapper(OrderPositionReturnedMapper.class);

    @Mapping(target = "status", ignore = true)
    @Mapping(target = "supplierEntryDate", ignore = true)
    OMSOrderPositionReturned fromApiOrderPositionReturned(OrderPositionReturned orderPositionReturned);

}