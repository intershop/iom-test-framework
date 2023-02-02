package com.intershop.oms.test.businessobject.orderstate;

import java.time.OffsetDateTime;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSOrderPositionReturnedQuantities extends OMSOrderPositionReturned
{

    private Integer quantity;

    
    @Override
    public OMSOrderPositionReturnedQuantities status(String status)
    {
        this.status = status;
        return this;
    }

    @Override
    public OMSOrderPositionReturnedQuantities supplierEntryDate(OffsetDateTime supplierEntryDate)
    {
        this.supplierEntryDate = supplierEntryDate;
        return this;
    }
    public OMSOrderPositionReturnedQuantities quantity(Integer quantity)
    {
        this.quantity = quantity;
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
        OMSOrderPositionReturnedQuantities orderPositionReturned = (OMSOrderPositionReturnedQuantities) o;
        return Objects.equals(status, orderPositionReturned.status) 
                        && Objects.equals(quantity, orderPositionReturned.quantity)
                        && Objects.equals(supplierEntryDate, orderPositionReturned.supplierEntryDate);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(status, supplierEntryDate,quantity);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OMSOrderPositionReturnedQuantities {\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    supplierEntryDate: ").append(toIndentedString(supplierEntryDate)).append("\n");
        sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
