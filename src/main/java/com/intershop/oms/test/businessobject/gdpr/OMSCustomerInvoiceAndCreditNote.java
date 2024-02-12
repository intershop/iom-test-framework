package com.intershop.oms.test.businessobject.gdpr;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Represents an invoice of a customer.
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSCustomerInvoiceAndCreditNote extends OMSBusinessObject {
    private String type;
    private List<String> relatedShopOrderNos = null;
    private List<String> relatedShopOrderNumbers = null;
    private String invoiceNo;
    private String invoiceNumber;
    private String referenceInvoiceNo;
    private String referenceInvoiceNumber;
    private OffsetDateTime invoicingDate;
    private String currency;
    private String paymentMethod;
    private OffsetDateTime paymentDueDate;

    private List<OMSCustomerInvoicePosition> invoicePositions = null;
}

