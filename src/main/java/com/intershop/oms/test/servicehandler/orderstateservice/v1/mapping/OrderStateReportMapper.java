package com.intershop.oms.test.servicehandler.orderstateservice.v1.mapping;

import org.mapstruct.Mapper;

@Mapper(uses = { OrderStateSumPriceMapper.class})
public interface OrderStateReportMapper
{
//    OrderStateReportMapper INSTANCE = Mappers.getMapper(OrderStateReportMapper.class);
//
//    OMSOrderStateCollectionContainer fromApiOrderStateReport(OrderStateReport orderStateReport);
//
//    @InheritInverseConfiguration
//    public abstract OrderStateReport toApiOrderStateReport(OMSOrderStateCollectionContainer omsOrderStateReport);
}


//java.util.Map<java.lang.String,java.lang.String> map(com.intershop.oms.test.businessobject.OMSCollectionMetaData value)