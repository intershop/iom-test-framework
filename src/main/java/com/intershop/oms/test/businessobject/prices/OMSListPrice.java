package com.intershop.oms.test.businessobject.prices;

import java.math.BigDecimal;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
// tried "fluent=true" but then the String setters will prevent the creation of the Lombok setters
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSListPrice extends OMSBusinessObject
{
    private BigDecimal amount = null;

    private BigDecimal amountDiscounted = null;

    private BigDecimal listPrice = null;

    public OMSListPrice amount(BigDecimal amount)
    {
        return setAmount(amount);
    }

    public OMSListPrice amountDiscounted(BigDecimal amountDiscounted)
    {
        return setAmountDiscounted(amountDiscounted);
    }

    public OMSListPrice listPrice(BigDecimal listPrice)
    {
        return setListPrice(listPrice);
    }

    public OMSListPrice amount(String amount)
    {
        if (amount != null)
        {
            return setAmount(new BigDecimal(amount));
        }
        else
        {
            return setAmount((BigDecimal)null);
        }
    }
    
    public OMSListPrice amountDiscounted(String amount)
    {
        if (amount != null)
        {
            return setAmountDiscounted(new BigDecimal(amount));
        }
        else
        {
            return setAmountDiscounted((BigDecimal)null);
        }
    }
    
    public OMSListPrice listPrice(String amount)
    {
        if (amount != null)
        {
            return setListPrice(new BigDecimal(amount));
        }
        else
        {
            return setListPrice((BigDecimal)null);
        }
    }
}