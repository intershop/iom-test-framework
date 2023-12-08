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

    public OMSSales addChargesItem(OMSCharge chargesItem)
    {
        charges.add(chargesItem);
        return this;
    }
}
