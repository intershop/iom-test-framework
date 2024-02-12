package com.intershop.oms.test.servicehandler.gdprservice.v2_10.mapping;

import com.intershop.oms.test.businessobject.gdpr.OMSCustomer;
import com.intershop.oms.rest.gdpr.v2_10.model.Customer;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CustomerBankAccountMapper.class, AddressMapper.class, CustomerOrderMapper.class, CustomerInvoiceAndCreditNoteMapper.class, CustomerBalanceMapper.class})
public interface CustomerMapper
{
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    OMSCustomer fromApiCustomer(Customer customer);

    Customer toApiCustomer(OMSCustomer omsCustomer);
}

