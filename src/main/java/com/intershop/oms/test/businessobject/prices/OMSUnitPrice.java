package com.intershop.oms.test.businessobject.prices;

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
public class OMSUnitPrice extends OMSBusinessObject
{
    private OMSListPrice net;

    private OMSListPrice gross;

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSUnitPrice net(OMSListPrice net)
    {
        return setNet(net);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSUnitPrice gross(OMSListPrice gross)
    {
        return setGross(gross);
    }
}