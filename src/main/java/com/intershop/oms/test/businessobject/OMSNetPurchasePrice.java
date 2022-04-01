package com.intershop.oms.test.businessobject;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSNetPurchasePrice extends OMSBusinessObject
{
    private Double amount;

    private String currency;

    public OMSNetPurchasePrice amount(Double amount)
    {
        this.amount = amount;
        return this;
    }

    public OMSNetPurchasePrice currency(String currency)
    {
        this.currency = currency;
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
        OMSNetPurchasePrice netPurchasePrice = (OMSNetPurchasePrice) o;
        return Objects.equals(this.amount, netPurchasePrice.amount) && Objects.equals(this.currency, netPurchasePrice.currency);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(amount, currency);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OMSNetPurchasePrice {\n");
        sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
        sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}