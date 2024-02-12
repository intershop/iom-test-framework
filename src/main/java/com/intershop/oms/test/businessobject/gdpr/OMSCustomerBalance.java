package com.intershop.oms.test.businessobject.gdpr;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

/**
 * Represents a customer balance.
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSCustomerBalance extends OMSBusinessObject {
    private String currency;
    private Double openInvoiceAmount;
    private OffsetDateTime openInvoiceAmountCalculationDate;
    private Double bookedCreditSum;
    private Double bookedDebitSum;
    private Double chargedCreditSum;
    private Double chargedDebitSum;
    private Double chargedOpenSum;
    private Double invoicingCreditSum;
    private Double invoicingDebitSum;
    private Double negativeBookedOpenSum;
    private Double positiveBookedOpenSum;
}

