package com.intershop.oms.test.businessobject.prices;

import java.math.BigDecimal;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class OMSTax extends OMSBusinessObject
{
    private String type;
    private BigDecimal amount = null;
    private BigDecimal rate = null;
    private String location = null;

    public OMSTax type(String type)
    {
        this.type = type;
        return this;
    }

    public OMSTax amount(BigDecimal amount)
    {
        this.amount = amount;
        return this;
    }

    public OMSTax amount(String amount)
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
    
    public OMSTax rate(BigDecimal rate)
    {
        if (null != rate)
        {
            this.rate = rate;
        }
        return this;
    }

    public OMSTax location(String location)
    {
        this.location = location;
        return this;
    }
}