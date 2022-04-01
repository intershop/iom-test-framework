package com.intershop.oms.test.businessobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Accessors(chain = true)
public class OMSSupplier
{
    private String name;
    private long id;

    public OMSSupplier()
    {
    }

    public OMSSupplier(long anId, String aSupplierName)
    {
        id = anId;
        name = aSupplierName;
    }

}
