package com.intershop.oms.test.servicehandler.supplierservice.v2_10.mapping;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.communication.v2_10.model.OrderResponse;
import com.intershop.oms.test.businessobject.communication.OMSOrderResponse;
import com.intershop.oms.test.servicehandler.ServiceHandlerFactory;

@Mapper(uses={OrderResponsePositionMapper.class, PropertyGroupMapper.class})
public interface OrderResponseMapper
{
    OrderResponseMapper INSTANCE = Mappers.getMapper(OrderResponseMapper.class);

    @Mapping(target = "supplierName", source = "supplier.name")
    @Mapping(target = "supplierShopName", ignore = true)
    OrderResponse toApiOrderResponse(OMSOrderResponse omsOrderResponse);

    @AfterMapping
    default void setShopSupplier(OMSOrderResponse omsOrderResponse, @MappingTarget OrderResponse orderResponse)
    {
        String supplierShopName = ServiceHandlerFactory.getDbHandler().getSupplierShopName(omsOrderResponse.getShop(),
                        omsOrderResponse.getSupplier());
        orderResponse.setSupplierShopName(supplierShopName);
    }
}