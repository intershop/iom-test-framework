package com.intershop.oms.test.servicehandler.gdprservice.v2_10.mapping;

import com.intershop.oms.test.businessobject.gdpr.OMSCustomerInvoicePosition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.gdpr.v2_10.model.CustomerInvoicePosition;

@Mapper
public interface CustomerInvoicePositionMapper
{
    CustomerInvoicePositionMapper INSTANCE = Mappers.getMapper(CustomerInvoicePositionMapper.class);

    OMSCustomerInvoicePosition fromApiCustomerInvoicePosition(CustomerInvoicePosition customerInvoicePosition);

    CustomerInvoicePosition toApiCustomerInvoicePosition(OMSCustomerInvoicePosition omsCustomerInvoicePosition);
}
