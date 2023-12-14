package com.intershop.oms.test.businessobject.orderstate;

import java.time.LocalDate;

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
public class OMSOrderPositionConfirmedQuantity extends OMSBusinessObject
{
    private Integer value;
    private LocalDate plannedDeliveryDate;

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionConfirmedQuantity value(Integer value)
    {
        return setValue(value);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionConfirmedQuantity plannedDeliveryDate(LocalDate plannedDeliveryDate)
    {
        return setPlannedDeliveryDate(plannedDeliveryDate);
    }
}
