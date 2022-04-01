package com.intershop.oms.test.servicehandler.orderstateservice.v1.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.orderstate.v1.model.OrderStateReport;
import com.intershop.oms.rest.shared.ApiResponse;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderStateCollectionContainer;

@Mapper(uses = { OrderStateReportMapper.class})
public interface OrderStateApiResponseMapper
{
//    OrderStateApiResponseMapper INSTANCE = Mappers.getMapper(OrderStateApiResponseMapper.class);
//
//    ApiResponse<OMSOrderStateCollectionContainer> fromApiResponse(ApiResponse<OrderStateReport> apiResponse);
}


//java.util.Map<java.lang.String,java.lang.String> map(com.intershop.oms.test.businessobject.OMSCollectionMetaData value)