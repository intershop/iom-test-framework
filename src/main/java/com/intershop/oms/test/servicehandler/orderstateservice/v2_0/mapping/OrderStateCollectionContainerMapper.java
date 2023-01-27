package com.intershop.oms.test.servicehandler.orderstateservice.v2_0.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.order.v2_0.model.OrderStateCollectionContainer;
import com.intershop.oms.rest.order.v2_0.model.Sales;
import com.intershop.oms.rest.order.v2_0.model.ShippingBucketOrderState;
import com.intershop.oms.test.businessobject.order.OMSShippingBucket;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderStateCollectionContainer;
import com.intershop.oms.test.businessobject.prices.OMSSales;

@Mapper(uses = { OrderStatePositionMapper.class, TaxMapper.class })
public interface OrderStateCollectionContainerMapper
{
    OrderStateCollectionContainerMapper INSTANCE = Mappers.getMapper(OrderStateCollectionContainerMapper.class);

    OMSOrderStateCollectionContainer fromApiOrderStateCollectionContainer(OrderStateCollectionContainer orderStateCollectionContainer);

    @Mapping(target = "usedTaxes", ignore = true)
    OMSSales fromApiOrderState(Sales sales);

    @Mapping(target = "usedTaxes", ignore = true)
    OMSShippingBucket fromApiShippingBucket(ShippingBucketOrderState shippingBucket);
}