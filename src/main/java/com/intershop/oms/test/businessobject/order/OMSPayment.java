package com.intershop.oms.test.businessobject.order;

import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSPayment extends OMSBusinessObject
{
    private String paymentMethod;

    private String paymentProviderOrderNo;

    private String paymentProviderRefNo;

    private String paymentProviderMerchantAccount;

    public OMSPayment paymentMethod(String paymentMethod)
    {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public OMSPayment paymentProviderOrderNo(String paymentProviderOrderNo)
    {
        this.paymentProviderOrderNo = paymentProviderOrderNo;
        return this;
    }

    public OMSPayment paymentProviderRefNo(String paymentProviderRefNo)
    {
        this.paymentProviderRefNo = paymentProviderRefNo;
        return this;
    }

    public OMSPayment paymentProviderMerchantAccount(String paymentProviderMerchantAccount)
    {
        this.paymentProviderMerchantAccount = paymentProviderMerchantAccount;
        return this;
    }

    @Override
    public boolean equals(java.lang.Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        OMSPayment payment = (OMSPayment) o;
        return Objects.equals(this.paymentMethod, payment.paymentMethod) &&
                        Objects.equals(this.paymentProviderOrderNo, payment.paymentProviderOrderNo) &&
                        Objects.equals(this.paymentProviderRefNo, payment.paymentProviderRefNo) &&
                        Objects.equals(this.paymentProviderMerchantAccount, payment.paymentProviderMerchantAccount);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(paymentMethod, paymentProviderOrderNo, paymentProviderRefNo, paymentProviderMerchantAccount);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class Payment {\n");
        sb.append("    paymentMethod: ").append(toIndentedString(paymentMethod)).append("\n");
        sb.append("    paymentProviderOrderNo: ").append(toIndentedString(paymentProviderOrderNo)).append("\n");
        sb.append("    paymentProviderRefNo: ").append(toIndentedString(paymentProviderRefNo)).append("\n");
        sb.append("    paymentProviderMerchantAccount: ").append(toIndentedString(paymentProviderMerchantAccount)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
