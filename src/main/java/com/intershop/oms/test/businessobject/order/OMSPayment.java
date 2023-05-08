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

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSPayment paymentMethod(String paymentMethod)
    {
        return setPaymentMethod(paymentMethod);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSPayment paymentProviderOrderNo(String paymentProviderOrderNo)
    {
        return setPaymentProviderOrderNo(paymentProviderOrderNo);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSPayment paymentProviderRefNo(String paymentProviderRefNo)
    {
        return setPaymentProviderRefNo(paymentProviderRefNo);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSPayment paymentProviderMerchantAccount(String paymentProviderMerchantAccount)
    {
        return setPaymentProviderMerchantAccount(paymentProviderMerchantAccount);
    }
}