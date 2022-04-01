package com.intershop.oms.test.servicehandler.supplierservice.v2_10.mapping;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.communication.v2_10.model.ModelReturn;
import com.intershop.oms.test.businessobject.communication.OMSReturn;
import com.intershop.oms.test.servicehandler.ServiceHandlerFactory;

@Mapper(uses = { PropertyGroupMapper.class })
public interface ReturnMapper
{
    ReturnMapper INSTANCE = Mappers.getMapper(ReturnMapper.class);

    @Mapping(target = "supplierName", source = "supplier.name")
    @Mapping(target = "supplierShopName", ignore = true)
    ModelReturn toApiReturn(OMSReturn omsReturn);

    @AfterMapping
    default void setShopSupplier(OMSReturn omsReturn, @MappingTarget ModelReturn modelReturn)
    {
        String supplierShopName = ServiceHandlerFactory.getDbHandler().getSupplierShopName(omsReturn.getShop(),
                        omsReturn.getSupplier());
        modelReturn.setSupplierShopName(supplierShopName);
    }
}