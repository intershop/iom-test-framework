package com.intershop.oms.test.businessobject.prices;

import java.math.BigDecimal;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSPrice extends OMSBusinessObject
{
    private BigDecimal amount = null;

    private BigDecimal amountDiscounted = null;

    public OMSPrice amount(String amount)
    {
        if (amount != null)
        {
            return amount(new BigDecimal(amount));
        }
        else
        {
            return amount((BigDecimal)null);
        }
    }
    
    public OMSPrice amountDiscounted(String amount)
    {
        if (amount != null)
        {
            return amountDiscounted(new BigDecimal(amount));
        }
        else
        {
            return amountDiscounted((BigDecimal)null);
        }
    }
    
    public OMSPrice amount(BigDecimal amount)
    {
        if (null != amount)
        {
            this.amount = amount;
        }
        return this;
    }

    public OMSPrice amountDiscounted(BigDecimal amountDiscounted)
    {
        if (null != amountDiscounted)
        {
            this.amountDiscounted = amountDiscounted;
        }
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
        OMSPrice price = (OMSPrice) o;
        return Objects.equals(this.amount, price.amount) && Objects.equals(this.amountDiscounted, price.amountDiscounted);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(amount, amountDiscounted);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class Price {\n");
        sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
        sb.append("    amountDiscounted: ").append(toIndentedString(amountDiscounted)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
