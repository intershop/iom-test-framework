package com.intershop.oms.test.businessobject.orderstate;

import java.time.OffsetDateTime;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSOrderPositionReturned extends OMSBusinessObject
{
    private String status;

    private OffsetDateTime supplierEntryDate;

    public OMSOrderPositionReturned status(String status)
    {
        this.status = status;
        return this;
    }

    public OMSOrderPositionReturned supplierEntryDate(OffsetDateTime supplierEntryDate)
    {
        this.supplierEntryDate = supplierEntryDate;
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
        OMSOrderPositionReturned orderPositionReturned = (OMSOrderPositionReturned) o;
        return Objects.equals(this.status, orderPositionReturned.status) && Objects.equals(this.supplierEntryDate, orderPositionReturned.supplierEntryDate);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(status, supplierEntryDate);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrderPositionReturned {\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    supplierEntryDate: ").append(toIndentedString(supplierEntryDate)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
