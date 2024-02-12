package com.intershop.oms.test.businessobject.gdpr;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Represents an invoice position of an invoice of a customer.
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSCustomerInvoicePosition extends OMSBusinessObject {
    private String shopOrderNo;
    private String shopOrderNumber;
    private String shopArticleNo;
    private String shopArticleNumber;
    private Integer quantity;
    private Double salesItemNet;
    private Double salesItemGross;
    private Double chargeNet;
    private Double chargeGross;
}

