package com.intershop.oms.test.businessobject.orderstate;

import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSOrderPositionDispatchedUnit extends OMSBusinessObject
{
    private String serialNumber;

    public OMSOrderPositionDispatchedUnit serialNumber(String serialNumber)
    {
        this.serialNumber = serialNumber;
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
        OMSOrderPositionDispatchedUnit orderPositionDispatchedUnit = (OMSOrderPositionDispatchedUnit) o;
        return Objects.equals(this.serialNumber, orderPositionDispatchedUnit.serialNumber);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(serialNumber);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrderPositionDispatchedUnit {\n");
        sb.append("    serialNumber: ").append(toIndentedString(serialNumber)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
