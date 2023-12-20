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
public class OMSReturnableDataItem extends OMSBusinessObject
{
    private String productSerialNumber;

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSReturnableDataItem productSerialNumber(String productSerialNumber)
    {
        return setProductSerialNumber(productSerialNumber);
    }
}
