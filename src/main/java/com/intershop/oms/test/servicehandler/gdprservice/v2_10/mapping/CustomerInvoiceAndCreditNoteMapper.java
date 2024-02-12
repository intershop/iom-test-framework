package com.intershop.oms.test.servicehandler.gdprservice.v2_10.mapping;

import com.intershop.oms.test.businessobject.gdpr.OMSCustomerInvoiceAndCreditNote;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.gdpr.v2_10.model.CustomerInvoiceAndCreditNote;

@Mapper(uses = {CustomerInvoicePositionMapper.class})
public interface CustomerInvoiceAndCreditNoteMapper
{
    CustomerInvoiceAndCreditNoteMapper INSTANCE = Mappers.getMapper(CustomerInvoiceAndCreditNoteMapper.class);

    OMSCustomerInvoiceAndCreditNote fromApiCustomerInvoiceAndCreditNote(CustomerInvoiceAndCreditNote customerInvoiceAndCreditNote);

    CustomerInvoiceAndCreditNote toApiCustomerInvoiceAndCreditNote(OMSCustomerInvoiceAndCreditNote omsCustomerInvoiceAndCreditNote);
}

