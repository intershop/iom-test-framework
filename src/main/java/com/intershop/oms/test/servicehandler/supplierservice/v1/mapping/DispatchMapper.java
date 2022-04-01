package com.intershop.oms.test.servicehandler.supplierservice.v1.mapping;

import com.intershop.oms.rest.communication.v2.model.Dispatch;
import com.intershop.oms.test.businessobject.communication.OMSDispatch;
import com.intershop.oms.test.servicehandler.ServiceHandlerFactory;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = { DispatchPositionMapper.class, PropertyGroupMapper.class })
public interface DispatchMapper
{
    DispatchMapper INSTANCE = Mappers.getMapper(DispatchMapper.class);

    @Mapping(target = "supplierName", source = "supplier.name")
    @Mapping(target = "supplierShopName", ignore = true)
    Dispatch toApiDispatch(OMSDispatch omsDispatch);

    @AfterMapping
    default void setShopSupplier(OMSDispatch omsDispatch, @MappingTarget Dispatch dispatch)
    {
        String supplierShopName = ServiceHandlerFactory.getDbHandler()
                        .getSupplierShopName(omsDispatch.getShop(), omsDispatch.getSupplier());
        dispatch.setSupplierShopName(supplierShopName);
    }

}