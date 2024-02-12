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
 * Represents an order of a customer.
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSCustomerOrder extends OMSBusinessObject {
    private Integer id;
    private String shopOrderNo;
    private String shopOrderNumber;
    private String shopCustomerOrderNo;
    private String shopCustomerOrderNumber;
    private String paymentProviderOrderNo;
    private String paymentProviderOrderNumber;
    private OffsetDateTime shopOrderCreationDate;
    private String paymentMethod;
    private Double shopSubTotalGross;
    private Double shopSubTotalNet;
    private Double shopTotalGross;
    private Double shopTotalNet;
    private String carrier;
    private Double shopSubTotalGrossDiscounted;
    private Double shopSubTotalNetDiscounted;
    private Double recalculatedShopSubTotalGrossDiscounted;
    private Double recalculatedShopSubTotalNetDiscounted;
    private String currency;

    private List<OMSCustomerOrderPosition> orderPositions = null;
}

