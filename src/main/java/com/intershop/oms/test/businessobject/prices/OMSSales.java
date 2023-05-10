package com.intershop.oms.test.businessobject.prices;

import java.util.ArrayList;
import java.util.List;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSSales extends OMSBusinessObject
{
    private String currencyCode;

    private OMSSumPrice subTotal;

    private List<OMSCharge> charges = new ArrayList<>();

    private OMSTotalPrice total;

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSSales currencyCode(String currencyCode)
    {
        return setCurrencyCode(currencyCode);
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSSales total(OMSTotalPrice total)
    {
        return setTotal(total);
    }
    
    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSSales subTotal(OMSSumPrice subTotal)
    {
        return setSubTotal(subTotal);
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSSales charges(List<OMSCharge> charges)
    {
        return setCharges(charges);
    }

    public OMSSales addChargesItem(OMSCharge chargesItem)
    {
        charges.add(chargesItem);
        return this;
    }
}
