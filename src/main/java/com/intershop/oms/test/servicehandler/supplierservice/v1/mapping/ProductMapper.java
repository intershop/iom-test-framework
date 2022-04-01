package com.intershop.oms.test.servicehandler.supplierservice.v1.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.communication.v2.model.Product;
import com.intershop.oms.test.businessobject.communication.OMSProduct;

@Mapper
public interface ProductMapper
{
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "shopProductNumber", ignore = true)
    @Mapping(target = "supplierProductNumber", ignore = true)
    OMSProduct fromApiProduct(Product product);

    @InheritInverseConfiguration
    public abstract Product toApiProduct(OMSProduct omsProduct);
}
