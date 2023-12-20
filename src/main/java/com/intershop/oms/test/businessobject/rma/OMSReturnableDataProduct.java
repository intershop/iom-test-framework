package com.intershop.oms.test.businessobject.rma;

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
public class OMSReturnableDataProduct extends OMSBusinessObject
{
    private String number;

    private String name;

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSReturnableDataProduct number(String number)
    {
        return setName(number);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSReturnableDataProduct name(String name)
    {
        return setName(name);
    }
}
