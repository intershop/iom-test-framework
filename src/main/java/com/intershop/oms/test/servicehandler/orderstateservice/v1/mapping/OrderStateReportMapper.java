package com.intershop.oms.test.servicehandler.orderstateservice.v1.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.orderstate.v1.model.OrderStateReport;
import com.intershop.oms.rest.shared.ApiResponse;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderStateCollectionContainer;

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