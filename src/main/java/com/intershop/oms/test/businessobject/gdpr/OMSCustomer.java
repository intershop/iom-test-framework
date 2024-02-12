package com.intershop.oms.test.businessobject.gdpr;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Represents a customer.
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSCustomer extends OMSBusinessObject {
    private OffsetDateTime creationDate;
    private String customerType;
    private LocalDate dayOfBirth;
    private String firstName;
    private Boolean hasNewsletter;
    private Boolean isActive;
    private Boolean isNewCustomer;
    private String lastName;
    private String gender;
    private String shopCustomerNo;
    private String shopCustomerNumber;
    private String title;
    private String email;
    private String fax;
    private String mobilePhone;
    private String phone1;
    private String phone2;
    private String companyName;

    private List<OMSCustomerBankAccount> bankAccounts = null;
    private List<OMSAddress> addresses = null;
    private List<OMSCustomerOrder> orders = null;
    private List<OMSCustomerInvoiceAndCreditNote> invoiceAndCreditNotes = null;
    private List<OMSCustomerBalance> customerBalances = null;
}

