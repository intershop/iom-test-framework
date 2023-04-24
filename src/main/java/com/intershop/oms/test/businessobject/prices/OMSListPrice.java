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
public class OMSListPrice extends OMSBusinessObject
{
    private BigDecimal amount = null;

    private BigDecimal amountDiscounted = null;

    private BigDecimal listPrice = null;

    @Tolerate
    public OMSListPrice setAmount(String amount)
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
    public OMSListPrice setAmountDiscounted(String amount)
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
    
    @Tolerate
    public OMSListPrice setListPrice(String amount)
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

    @Deprecated(since = "4.5.0", forRemoval = true)
    public OMSListPrice amount(BigDecimal amount)
    {
        return setAmount(amount);
    }
    
    @Deprecated(since = "4.5.0", forRemoval = true)
    public OMSListPrice amountDiscounted(BigDecimal amount)
    {
        return setAmountDiscounted(amount);
    }
    
    @Deprecated(since = "4.5.0", forRemoval = true)
    public OMSListPrice listPrice(BigDecimal amount)
    {
        return setListPrice(amount);
    }
    
}