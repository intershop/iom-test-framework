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
public class OMSListPrice extends OMSBusinessObject
{
    private BigDecimal amount = null;

    private BigDecimal amountDiscounted = null;

    private BigDecimal listPrice = null;

    public OMSListPrice setAmount(String amount)
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
    
    public OMSListPrice setAmountDiscounted(String amount)
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
    
    public OMSListPrice setListPrice(String amount)
    {
        if (amount != null)
        {
            return listPrice(new BigDecimal(amount));
        }
        else
        {
            return listPrice((BigDecimal)null);
        }
    }
}