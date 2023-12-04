package com.intershop.oms.test.servicehandler.orderservice.v2_4.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.order.v2_4.model.LatestOrderStateCollectionContainer;
import com.intershop.oms.test.businessobject.orderstate.OMSLatestOrderStateCollectionContainer;

@Mapper(uses = {OrderStatePositionMapper.class,
                TaxMapper.class,
                AddressLocationMapper.class,
                OrderPositionReturnedQuantitiesMapper.class})
public interface LatestOrderStateCollectionContainerMapper
{
    LatestOrderStateCollectionContainerMapper INSTANCE = Mappers.getMapper(LatestOrderStateCollectionContainerMapper.class);

    OMSLatestOrderStateCollectionContainer fromApiLatestOrderStateCollectionContainer(LatestOrderStateCollectionContainer latestOrderStateCollectionContainer);
}