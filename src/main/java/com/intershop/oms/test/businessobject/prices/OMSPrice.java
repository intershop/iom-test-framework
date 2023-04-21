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
@Accessors(chain = true, fluent = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSPrice extends OMSBusinessObject
{
    private BigDecimal amount = null;

    private BigDecimal amountDiscounted = null;

    public OMSPrice setAmount(String amount)
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
    
    public OMSPrice setAmountDiscounted(String amount)
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
   
}
