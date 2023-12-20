package com.intershop.oms.test.businessobject.orderstate;

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
public class OMSOrderPositionDispatchedUnit extends OMSBusinessObject
{
    private String serialNumber;

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionDispatchedUnit serialNumber(String serialNumber)
    {
        return setSerialNumber(serialNumber);
    }
}