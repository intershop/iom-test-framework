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
//tried "fluent=true" but then the String setters will prevent the creation of the Lombok setters
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSPrice extends OMSBusinessObject
{
    private BigDecimal amount = null;

    private BigDecimal amountDiscounted = null;

    public OMSPrice amount(String amount)
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
    
    public OMSPrice amountDiscounted(String amount)
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
    
    public OMSPrice amount(BigDecimal amount)
    {
        return setAmount(amount);
    }

    public OMSPrice amountDiscounted(BigDecimal amountDiscounted)
    {
        return setAmountDiscounted(amountDiscounted);
    }
}
