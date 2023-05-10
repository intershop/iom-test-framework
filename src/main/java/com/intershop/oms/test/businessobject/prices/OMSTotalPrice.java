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
public class OMSTotalPrice extends OMSBusinessObject
{
    private OMSPrice net;

    private OMSPrice gross;

    private List<OMSTax> taxes = null;

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSTotalPrice net(OMSPrice net)
    {
        return setNet(net);
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSTotalPrice gross(OMSPrice gross)
    {
        return setGross(gross);
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSTotalPrice taxes(List<OMSTax> taxes)
    {
        return setTaxes(taxes);
    }

    public OMSTotalPrice addTaxesItem(OMSTax taxesItem)
    {
        if (taxes == null)
        {
            taxes = new ArrayList<>();
        }
        else
        {
            taxes = new ArrayList<>(taxes);
        }
        taxes.add(taxesItem);
        return this;
    }
}
