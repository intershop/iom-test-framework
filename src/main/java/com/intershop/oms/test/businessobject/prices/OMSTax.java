package com.intershop.oms.test.businessobject.prices;

import java.math.BigDecimal;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
//tried "fluent=true" but then the String setters will prevent the creation of the Lombok setters
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSTax extends OMSBusinessObject
{
    private String type;
    private BigDecimal amount = null;
    private BigDecimal rate = null;
    private String location = null;

    public OMSTax type(String type)
    {
        return setType(type);
    }

    public OMSTax amount(BigDecimal amount)
    {
        return setAmount(amount);
    }

    public OMSTax amount(String amount)
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
    
    public OMSTax rate(BigDecimal rate)
    {
        return setRate(rate);
    }

    public OMSTax location(String location)
    {
        return setLocation(location);
    }
}