package com.intershop.oms.test.businessobject.orderstate;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

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
public class OMSOrderPositionDispatched extends OMSBusinessObject
{
    private Integer quantity;
    private String deliveryNoteNumber;
    private OffsetDateTime dispatchDate;
    private OMSOrderStateOrderPositionDispatchedCarrier carrier;
    private List<OMSOrderPositionDispatchedUnit> units = null;

    public OMSOrderPositionDispatched addUnitsItem(OMSOrderPositionDispatchedUnit unitsItem)
    {
        if (units == null)
        {
            units = new ArrayList<>();
        }
        units.add(unitsItem);
        return this;
    }
    
    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionDispatched quantity(Integer quantity)
    {
        return setQuantity(quantity);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionDispatched deliveryNoteNumber(String deliveryNoteNumber)
    {
        return setDeliveryNoteNumber(deliveryNoteNumber);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionDispatched dispatchDate(OffsetDateTime dispatchDate)
    {
        return setDispatchDate(dispatchDate);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionDispatched carrier(OMSOrderStateOrderPositionDispatchedCarrier carrier)
    {
        return setCarrier(carrier);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionDispatched units(List<OMSOrderPositionDispatchedUnit> units)
    {
        return setUnits(units);
    }
}
