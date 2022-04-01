package com.intershop.oms.test.servicehandler.supplierservice.v2_11.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.communication.v2_11.model.NetPurchasePrice;
import com.intershop.oms.test.businessobject.OMSNetPurchasePrice;

@Mapper
public interface NetPurchasePriceMapper
{
    NetPurchasePriceMapper INSTANCE = Mappers.getMapper(NetPurchasePriceMapper.class);

    OMSNetPurchasePrice fromApiNetPurchasePrice(NetPurchasePrice netPurchasePrice);

    @InheritInverseConfiguration
    public abstract NetPurchasePrice toApiNetPurchasePrice(OMSNetPurchasePrice omsNetPurchasePrice);
}
