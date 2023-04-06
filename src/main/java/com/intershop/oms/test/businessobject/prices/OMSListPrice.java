package com.intershop.oms.test.businessobject.prices;

import java.math.BigDecimal;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSListPrice extends OMSBusinessObject
{
    private BigDecimal amount = null;

    private BigDecimal amountDiscounted = null;

    private BigDecimal listPrice = null;

    public OMSListPrice amount(BigDecimal amount)
    {
        if (null != amount)
        {
            this.amount = amount;
        }
        return this;
    }

    public OMSListPrice amountDiscounted(BigDecimal amountDiscounted)
    {
        if (null != amountDiscounted)
        {
            this.amountDiscounted = amountDiscounted;
        }
        return this;
    }

    public OMSListPrice listPrice(BigDecimal listPrice)
    {
        if (null != listPrice)
        {
            this.listPrice = listPrice;
        }
        return this;
    }

    public OMSListPrice amount(String amount)
    {
        return amount(new BigDecimal(amount));
    }
    
    public OMSListPrice amountDiscounted(String amount)
    {
        return amountDiscounted(new BigDecimal(amount));
    }
    
    public OMSListPrice listPrice(String amount)
    {
        return listPrice(new BigDecimal(amount));
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
        OMSListPrice listPrice = (OMSListPrice) o;
        return Objects.equals(this.amount, listPrice.amount) &&
                        Objects.equals(this.amountDiscounted, listPrice.amountDiscounted) &&
                        Objects.equals(this.listPrice, listPrice.listPrice);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(amount, amountDiscounted, listPrice);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class ListPrice {\n");
        sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
        sb.append("    amountDiscounted: ").append(toIndentedString(amountDiscounted)).append("\n");
        sb.append("    listPrice: ").append(toIndentedString(listPrice)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}