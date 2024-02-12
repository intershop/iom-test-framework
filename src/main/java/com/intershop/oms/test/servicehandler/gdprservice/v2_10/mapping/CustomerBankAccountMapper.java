package com.intershop.oms.test.servicehandler.gdprservice.v2_10.mapping;

import com.intershop.oms.test.businessobject.gdpr.OMSCustomerBankAccount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.gdpr.v2_10.model.CustomerBankAccount;

@Mapper
public interface CustomerBankAccountMapper
{
    CustomerBankAccountMapper INSTANCE = Mappers.getMapper(CustomerBankAccountMapper.class);

    OMSCustomerBankAccount fromApiCustomerBankAccount(CustomerBankAccount customerBankAccount);

    CustomerBankAccount toApiCustomerBankAccount(OMSCustomerBankAccount omsCustomerBankAccount);
}

