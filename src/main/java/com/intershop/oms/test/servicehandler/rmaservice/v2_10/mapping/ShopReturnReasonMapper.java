package com.intershop.oms.test.servicehandler.rmaservice.v2_10.mapping;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.rma.v2_10.model.ShopReturnReason;
import com.intershop.oms.test.businessobject.rma.OMSShopReturnReason;

@Mapper
public interface ShopReturnReasonMapper
{
    ShopReturnReasonMapper INSTANCE = Mappers.getMapper(ShopReturnReasonMapper.class);

    OMSShopReturnReason fromApiShopReturnReason(ShopReturnReason shopReturnReason);

    @InheritInverseConfiguration
    public abstract ShopReturnReason toApiShopReturnReason(OMSShopReturnReason omsShopReturnReason);

    @AfterMapping
    public default void fromApiShopReturnReasonList(final List<ShopReturnReason> shopReturnReasons, @MappingTarget final List<OMSShopReturnReason> omsShopReturnReasons)
    {
        shopReturnReasons.stream().forEach(shopReturnReason -> omsShopReturnReasons.add(fromApiShopReturnReason(shopReturnReason)));
    }
}