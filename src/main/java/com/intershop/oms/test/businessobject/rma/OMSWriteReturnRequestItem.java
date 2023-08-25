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
public class OMSWriteReturnRequestItem extends OMSBusinessObject
{
    private String productSerialNumber;

    /**
     * @deprecated use setProductSerialNumber as produced by LOMBOK
     */
    @Deprecated(forRemoval = true, since = "6.0.0")
    public OMSWriteReturnRequestItem productSerialNumber(String productSerialNumber)
    {
        this.productSerialNumber = productSerialNumber;
        return this;
    }
}
