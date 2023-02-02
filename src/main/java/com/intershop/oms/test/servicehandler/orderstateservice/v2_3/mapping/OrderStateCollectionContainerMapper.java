package com.intershop.oms.test.servicehandler.orderstateservice.v2_3.mapping;

import com.intershop.oms.rest.order.v2_3.model.OrderStateCollectionContainer;

import com.intershop.oms.test.businessobject.orderstate.OMSOrderStateCollectionContainer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { OrderStatePositionMapper.class, 
                TaxMapper.class, 
                com.intershop.oms.test.servicehandler.orderservice.v2_3.mapping.AddressInvoiceLocationMapper.class, 
                com.intershop.oms.test.servicehandler.orderservice.v2_3.mapping.AddressShippingLocationMapper.class })
public interface OrderStateCollectionContainerMapper
{
    OrderStateCollectionContainerMapper INSTANCE = Mappers.getMapper(OrderStateCollectionContainerMapper.class);

    OMSOrderStateCollectionContainer fromApiOrderStateCollectionContainer(
                    OrderStateCollectionContainer orderStateCollectionContainer);

}