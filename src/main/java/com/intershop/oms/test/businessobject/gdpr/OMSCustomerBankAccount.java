package com.intershop.oms.test.businessobject.gdpr;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Represents a bank account of a customer.
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSCustomerBankAccount extends OMSBusinessObject {
    private String accountHolder;
    private String accountNumber;
    private String bankCode;
    private String bankName;
}

