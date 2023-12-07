package com.intershop.oms.test.businessobject.order;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSPayment extends OMSBusinessObject
{
    private String paymentMethod;

    private String paymentProviderOrderNo;

    private String paymentProviderRefNo;

    private String paymentProviderMerchantAccount;
}