package com.intershop.oms.test.businessobject.gdpr;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

/**
 * Represents an order position of an order of a customer.
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSCustomerOrderPosition extends OMSBusinessObject {
    private String shopArticleNo;
    private String shopArticleNumber;
    private String shopArticleName;
    private Integer orderPosNo;
    private Integer orderPosNumber;
    private Integer quantityCanceled;
    private Integer quantityOrdered;
    private Integer quantityReturned;
    private OffsetDateTime returnDate;
    private Double shopItemGross;
    private Double shopItemNet;
    private Double shopItemTax;
    private Double shopPosGross;
    private Double shopPosNet;
    private Double shopPosTax;
    private Double shopPosNetDiscounted;
    private Double shopPosGrossDiscounted;
    private String shopUnit;
    private Double shopItemNetDiscounted;
    private Double shopItemGrossDiscounted;
    private Integer quantityRefunded;

    private OMSAddress shippingAddress;
}

