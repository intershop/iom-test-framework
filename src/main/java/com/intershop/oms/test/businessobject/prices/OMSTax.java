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
public class OMSTax extends OMSBusinessObject
{
    private String type;
    private BigDecimal amount = null;
    private BigDecimal rate = null;
    private String location = null;

    @Tolerate
    public OMSTax setAmount(String amount)
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
}