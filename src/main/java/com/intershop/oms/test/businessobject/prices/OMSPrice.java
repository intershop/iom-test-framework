package com.intershop.oms.test.businessobject.prices;

import java.math.BigDecimal;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSPrice extends OMSBusinessObject
{
    private BigDecimal amount = null;

    private BigDecimal amountDiscounted = null;

    @Tolerate
    public OMSPrice setAmount(String amount)
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
    
    @Tolerate
    public OMSPrice setAmountDiscounted(String amount)
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
}
