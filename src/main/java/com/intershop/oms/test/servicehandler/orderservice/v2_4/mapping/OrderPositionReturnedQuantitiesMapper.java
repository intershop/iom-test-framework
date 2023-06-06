package com.intershop.oms.test.servicehandler.orderservice.v2_4.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.order.v2_4.model.OrderPositionReturned;
import com.intershop.oms.rest.order.v2_4.model.OrderPositionReturnedQuantities;
import com.intershop.oms.rest.order.v2_4.model.OrderPositionReturnedUnits;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderPositionReturned;

@Mapper(uses = TaxMapper.class)
public interface OrderPositionReturnedQuantitiesMapper
{
    
    OrderPositionReturnedQuantitiesMapper INSTANCE = Mappers.getMapper(OrderPositionReturnedQuantitiesMapper.class);
    
    @Mapping(source = "quantity", target = "quantity")
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "units", ignore = true)
    OMSOrderPositionReturned fromApiOrderPositionReturnedQuantities(OrderPositionReturnedQuantities position);

    //@Mapping(target = "supplierEntryDate", ignore = true)
    
    //OMSOrderPositionReturnedQuantities fromApiOrderPositionReturnedQuantities(OrderPositionReturnedQuantities position);
}