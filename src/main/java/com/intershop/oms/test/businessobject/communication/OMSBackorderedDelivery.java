package com.intershop.oms.test.businessobject.communication;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
public class OMSBackorderedDelivery extends OMSBusinessObject
{
    private Integer quantity;

    private LocalDate plannedDeliveryDate;

    public OMSBackorderedDelivery quantity(Integer quantity)
    {
        this.quantity = quantity;
        return this;
    }

    public OMSBackorderedDelivery plannedDeliveryDate(LocalDate plannedDeliveryDate)
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
        OMSBackorderedDelivery backorderedDelivery = (OMSBackorderedDelivery)o;
        return Objects.equals(this.quantity, backorderedDelivery.quantity) && Objects.equals(this.plannedDeliveryDate,
                        backorderedDelivery.plannedDeliveryDate);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(quantity, plannedDeliveryDate);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class BackorderedDelivery {\n");
        sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
        sb.append("    plannedDeliveryDate: ").append(toIndentedString(plannedDeliveryDate)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}