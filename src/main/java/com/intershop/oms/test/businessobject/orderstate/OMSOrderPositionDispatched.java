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
public class OMSOrderPositionDispatched extends OMSBusinessObject
{
    private Integer quantity;

    private String deliveryNoteNumber;

    private OffsetDateTime dispatchDate;

    private OMSOrderStateOrderPositionDispatchedCarrier carrier;

    private List<OMSOrderPositionDispatchedUnit> units = null;

    public OMSOrderPositionDispatched quantity(Integer quantity)
    {
        this.quantity = quantity;
        return this;
    }

    public OMSOrderPositionDispatched deliveryNoteNumber(String deliveryNoteNumber)
    {
        this.deliveryNoteNumber = deliveryNoteNumber;
        return this;
    }

    public OMSOrderPositionDispatched dispatchDate(OffsetDateTime dispatchDate)
    {
        this.dispatchDate = dispatchDate;
        return this;
    }

    public OMSOrderPositionDispatched carrier(OMSOrderStateOrderPositionDispatchedCarrier carrier)
    {
        this.carrier = carrier;
        return this;
    }

    public OMSOrderPositionDispatched units(List<OMSOrderPositionDispatchedUnit> units)
    {
        this.units = units;
        return this;
    }

    public OMSOrderPositionDispatched addUnitsItem(OMSOrderPositionDispatchedUnit unitsItem)
    {
        if (this.units == null)
        {
            this.units = new ArrayList<>();
        }
        this.units.add(unitsItem);
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
        OMSOrderPositionDispatched orderPositionDispatched = (OMSOrderPositionDispatched) o;
        return Objects.equals(this.quantity, orderPositionDispatched.quantity) &&
                        Objects.equals(this.deliveryNoteNumber, orderPositionDispatched.deliveryNoteNumber) &&
                        Objects.equals(this.dispatchDate, orderPositionDispatched.dispatchDate) &&
                        Objects.equals(this.carrier, orderPositionDispatched.carrier) &&
                        Objects.equals(this.units, orderPositionDispatched.units);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(quantity, deliveryNoteNumber, dispatchDate, carrier, units);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrderPositionDispatched {\n");
        sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
        sb.append("    deliveryNoteNumber: ").append(toIndentedString(deliveryNoteNumber)).append("\n");
        sb.append("    dispatchDate: ").append(toIndentedString(dispatchDate)).append("\n");
        sb.append("    carrier: ").append(toIndentedString(carrier)).append("\n");
        sb.append("    units: ").append(toIndentedString(units)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
