package com.intershop.oms.test.businessobject.prices;

import java.math.BigDecimal;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true, fluent = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSTax extends OMSBusinessObject
{
    private String type;
    private BigDecimal amount = null;
    private BigDecimal rate = null;
    private String location = null;

    public OMSTax setAmount(String amount)
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
}