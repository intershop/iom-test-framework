package com.intershop.oms.test.businessobject.orderstate;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSOrderPositionConfirmed extends OMSBusinessObject
{
    private String type;

    private OffsetDateTime confirmationDate;

    private List<OMSOrderPositionConfirmedQuantity> quantities = null;

    private List<OMSOrderPositionConfirmedCancelled> cancelled = null;

    public OMSOrderPositionConfirmed type(String type)
    {
        this.type = type;
        return this;
    }

    public OMSOrderPositionConfirmed confirmationDate(OffsetDateTime confirmationDate)
    {
        this.confirmationDate = confirmationDate;
        return this;
    }

    public OMSOrderPositionConfirmed quantities(List<OMSOrderPositionConfirmedQuantity> quantities)
    {
        this.quantities = quantities;
        return this;
    }

    public OMSOrderPositionConfirmed addQuantitiesItem(OMSOrderPositionConfirmedQuantity quantitiesItem)
    {
        if (this.quantities == null)
        {
            this.quantities = new ArrayList<>();
        }
        this.quantities.add(quantitiesItem);
        return this;
    }

    public OMSOrderPositionConfirmed cancelled(List<OMSOrderPositionConfirmedCancelled> cancelled)
    {
        this.cancelled = cancelled;
        return this;
    }

    public OMSOrderPositionConfirmed addCancelledItem(OMSOrderPositionConfirmedCancelled cancelledItem)
    {
        if (this.cancelled == null)
        {
            this.cancelled = new ArrayList<>();
        }
        this.cancelled.add(cancelledItem);
        return this;
    }

    @Override
    public boolean equals(java.lang.Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        OMSOrderPositionConfirmed orderPositionConfirmed = (OMSOrderPositionConfirmed) o;
        return Objects.equals(this.type, orderPositionConfirmed.type) &&
                        Objects.equals(this.confirmationDate, orderPositionConfirmed.confirmationDate) &&
                        Objects.equals(this.quantities, orderPositionConfirmed.quantities) &&
                        Objects.equals(this.cancelled, orderPositionConfirmed.cancelled);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(type, confirmationDate, quantities, cancelled);
    }


    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrderPositionConfirmed {\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    confirmationDate: ").append(toIndentedString(confirmationDate)).append("\n");
        sb.append("    quantities: ").append(toIndentedString(quantities)).append("\n");
        sb.append("    cancelled: ").append(toIndentedString(cancelled)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
