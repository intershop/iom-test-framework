package com.intershop.oms.test.servicehandler.rmaservice.v2_11.mapping;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.rma.v2_11.model.ShopReturnReason;
import com.intershop.oms.test.businessobject.rma.OMSShopReturnReason;

@Mapper
public interface ShopReturnReasonMapper
{
    ShopReturnReasonMapper INSTANCE = Mappers.getMapper(ShopReturnReasonMapper.class);

    OMSShopReturnReason fromApiShopReturnReason(ShopReturnReason shopReturnReason);

    @InheritInverseConfiguration
    ShopReturnReason toApiShopReturnReason(OMSShopReturnReason omsShopReturnReason);

    @AfterMapping
    default void fromApiShopReturnReasonList(final List<ShopReturnReason> shopReturnReasons, @MappingTarget final List<OMSShopReturnReason> omsShopReturnReasons)
    {
        shopReturnReasons.stream().forEach(shopReturnReason -> omsShopReturnReasons.add(fromApiShopReturnReason(shopReturnReason)));
    }
}