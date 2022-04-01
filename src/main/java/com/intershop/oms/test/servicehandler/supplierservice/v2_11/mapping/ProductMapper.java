package com.intershop.oms.test.servicehandler.supplierservice.v2_11.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.communication.v2_11.model.Product;
import com.intershop.oms.test.businessobject.communication.OMSProduct;

@Mapper
public interface ProductMapper
{
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    OMSProduct fromApiProduct(Product product);

    @InheritInverseConfiguration
    public abstract Product toApiProduct(OMSProduct omsProduct);
}
