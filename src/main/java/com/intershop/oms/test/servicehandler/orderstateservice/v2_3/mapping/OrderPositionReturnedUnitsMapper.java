package com.intershop.oms.test.servicehandler.orderstateservice.v2_3.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.order.v2_3.model.OrderPositionReturned;
import com.intershop.oms.rest.order.v2_3.model.OrderPositionReturnedQuantities;
import com.intershop.oms.rest.order.v2_3.model.OrderPositionReturnedUnits;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderPositionReturnedQuantities;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderPositionReturnedUnits;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderPositionReturned;

@Mapper(uses = TaxMapper.class)
public interface OrderPositionReturnedUnitsMapper
{
    
    OrderPositionReturnedUnitsMapper INSTANCE = Mappers.getMapper(OrderPositionReturnedUnitsMapper.class);
    

    @Mapping(target = "OMSOrderPositionReturnedUnitsUnits", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "supplierEntryDate", ignore = true)
    OMSOrderPositionReturnedUnits fromApiOrderPositionReturnedUnits(OrderPositionReturnedUnits position);

}