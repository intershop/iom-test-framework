package com.intershop.oms.test.businessobject.orderstate;

import java.time.LocalDate;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSOrderPositionConfirmedQuantity extends OMSBusinessObject
{
    private Integer value;

    private LocalDate plannedDeliveryDate;

    public OMSOrderPositionConfirmedQuantity value(Integer value)
    {
        this.value = value;
        return this;
    }

    public OMSOrderPositionConfirmedQuantity plannedDeliveryDate(LocalDate plannedDeliveryDate)
    {
        this.plannedDeliveryDate = plannedDeliveryDate;
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
        OMSOrderPositionConfirmedQuantity orderPositionConfirmedQuantity = (OMSOrderPositionConfirmedQuantity) o;
        return Objects.equals(this.value, orderPositionConfirmedQuantity.value) && Objects.equals(this.plannedDeliveryDate, orderPositionConfirmedQuantity.plannedDeliveryDate);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(value, plannedDeliveryDate);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrderPositionConfirmedQuantity {\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
        sb.append("    plannedDeliveryDate: ").append(toIndentedString(plannedDeliveryDate)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
