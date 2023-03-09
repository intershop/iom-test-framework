package com.intershop.oms.test.businessobject.prices;

import java.math.BigDecimal;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@ToString
@Accessors(chain = true)
public class OMSTax extends OMSBusinessObject
{
    private String type;
    private BigDecimal amount;
    private BigDecimal rate;
    private String location;

    public OMSTax() {}

    public OMSTax(String type, BigDecimal amount, BigDecimal rate, String location)
    {
        this.type     = type;
        this.amount   = amount;
        this.rate     = rate;
        this.location = location;
    }

    /**
     * to be able to use fix tax rates and still set different amounts in test orders this returns a new object!
     *
     * @param amount
     * @return a new tax object with the same location, rate, type, but the new amount
     */
    public OMSTax amount(BigDecimal amount)
    {
        return new OMSTax(type, amount, rate, location);
    }

    public OMSTax type(String type)
    {
        this.type = type;
        return this;
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