
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
public class OMSOrderPositionConfirmedCancelledReason extends OMSBusinessObject
{
    private String id;
    private String value;

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionConfirmedCancelledReason id(String id)
    {
        return setId(id);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionConfirmedCancelledReason value(String value)
    {
        return setValue(value);
    }
}
