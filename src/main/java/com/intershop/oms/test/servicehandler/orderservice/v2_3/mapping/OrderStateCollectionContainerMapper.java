package com.intershop.oms.test.servicehandler.orderservice.v2_3.mapping;

import com.intershop.oms.rest.order.v2_3.model.OrderStateCollectionContainer;

import com.intershop.oms.test.businessobject.orderstate.OMSOrderStateCollectionContainer;
import com.intershop.oms.test.servicehandler.orderservice.v2_3.mapping.OrderPositionReturnedQuantitiesMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { OrderStatePositionMapper.class, 
                TaxMapper.class, 
                AddressInvoiceLocationMapper.class, 
                AddressShippingLocationMapper.class,
                OrderPositionReturnedQuantitiesMapper.class})
public interface OrderStateCollectionContainerMapper
{
    OrderStateCollectionContainerMapper INSTANCE = Mappers.getMapper(OrderStateCollectionContainerMapper.class);

    OMSOrderStateCollectionContainer fromApiOrderStateCollectionContainer(
                    OrderStateCollectionContainer orderStateCollectionContainer);

}