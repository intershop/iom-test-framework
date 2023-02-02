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
public class OMSOrderPositionReturnedUnits  extends OMSOrderPositionReturned
{

    private List<OMSOrderPositionReturnedUnitsUnit> oMSOrderPositionReturnedUnitsUnits = new ArrayList<>();

    @Override
    public OMSOrderPositionReturnedUnits status(String status)
    {
        this.status = status;
        return this;
    }

    @Override
    public OMSOrderPositionReturnedUnits supplierEntryDate(OffsetDateTime supplierEntryDate)
    {
        this.supplierEntryDate = supplierEntryDate;
        return this;
    }
    
    public OMSOrderPositionReturnedUnits status(List<OMSOrderPositionReturnedUnitsUnit> oMSOrderPositionReturnedUnitsUnits)
    {
        this.oMSOrderPositionReturnedUnitsUnits = oMSOrderPositionReturnedUnitsUnits;
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
        return Objects.equals(status, orderPositionReturned.status) && Objects.equals(supplierEntryDate, orderPositionReturned.supplierEntryDate);
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
        sb.append("class OMSOrderPositionReturnedUnits {\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    supplierEntryDate: ").append(toIndentedString(supplierEntryDate)).append("\n");
        sb.append("    oMSOrderPositionReturnedUnitsUnits: ").append(toIndentedString(oMSOrderPositionReturnedUnitsUnits)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
