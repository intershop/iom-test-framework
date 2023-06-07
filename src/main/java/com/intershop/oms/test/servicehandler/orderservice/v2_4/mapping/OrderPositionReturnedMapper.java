package com.intershop.oms.test.servicehandler.orderservice.v2_4.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.order.v2_4.model.OrderPositionReturned;
import com.intershop.oms.rest.order.v2_4.model.OrderPositionReturnedQuantities;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderPositionReturned;

@Mapper(uses = TaxMapper.class)
public interface OrderPositionReturnedMapper
{
    OrderPositionReturnedMapper INSTANCE = Mappers.getMapper(OrderPositionReturnedMapper.class);
    
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "supplierEntryDate", ignore = true)
    @Mapping(source = "orderPositionReturnedQuantities.quantity", target = "quantity")
    @Mapping(source = "orderPositionReturnedUnits.units", target = "units")
    OMSOrderPositionReturned fromApiOrderPositionReturned(OrderPositionReturned orderPositionReturned);


}