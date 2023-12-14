package com.intershop.oms.test.businessobject.communication;

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
public class OMSConfirmedDelivery extends OMSBusinessObject
{
    private Integer quantity;
    private LocalDate plannedDeliveryDate;

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSConfirmedDelivery quantity(Integer quantity)
    {
        return setQuantity(quantity);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSConfirmedDelivery plannedDeliveryDate(LocalDate plannedDeliveryDate)
    {
        return setPlannedDeliveryDate(plannedDeliveryDate);
    }
}
