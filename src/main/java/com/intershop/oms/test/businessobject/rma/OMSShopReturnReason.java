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
public class OMSShopReturnReason extends OMSBusinessObject
{
    private String name;

    private String description;

    private String type;

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSShopReturnReason name(String name)
    {
        return setName(name);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSShopReturnReason description(String description)
    {
        return setDescription(description);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSShopReturnReason type(String type)
    {
        return setType(type);
    }
}
