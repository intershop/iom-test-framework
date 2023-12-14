package com.intershop.oms.test.businessobject.orderstate;

import java.time.OffsetDateTime;

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
public class OMSOrderPositionConfirmedCancelled extends OMSBusinessObject
{
    private Integer quantity;
    private OffsetDateTime cancelDate;
    private OMSOrderPositionConfirmedCancelledReason reason;

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionConfirmedCancelled quantity(Integer quantity)
    {
        return setQuantity(quantity);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionConfirmedCancelled cancelDate(OffsetDateTime cancelDate)
    {
        return setCancelDate(cancelDate);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionConfirmedCancelled reason(OMSOrderPositionConfirmedCancelledReason reason)
    {
        return setReason(reason);
    }
}
