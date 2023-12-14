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
public class OMSOrderPositionConfirmed extends OMSBusinessObject
{
    private String type;
    private OffsetDateTime confirmationDate;
    private List<OMSOrderPositionConfirmedQuantity> quantities = null;
    private List<OMSOrderPositionConfirmedCancelled> cancelled = null;

    public OMSOrderPositionConfirmed addQuantitiesItem(OMSOrderPositionConfirmedQuantity quantitiesItem)
    {
        if (quantities == null)
        {
            quantities = new ArrayList<>();
        }
        quantities.add(quantitiesItem);
        return this;
    }

    public OMSOrderPositionConfirmed addCancelledItem(OMSOrderPositionConfirmedCancelled cancelledItem)
    {
        if (cancelled == null)
        {
            cancelled = new ArrayList<>();
        }
        cancelled.add(cancelledItem);
        return this;
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionConfirmed type(String type)
    {
        return setType(type);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionConfirmed confirmationDate(OffsetDateTime confirmationDate)
    {
        return setConfirmationDate(confirmationDate);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionConfirmed quantities(List<OMSOrderPositionConfirmedQuantity> quantities)
    {
        return setQuantities(quantities);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionConfirmed cancelled(List<OMSOrderPositionConfirmedCancelled> cancelled)
    {
        return setCancelled(cancelled);
    }
}