package com.intershop.oms.test.businessobject.orderstate;

import java.time.OffsetDateTime;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSPropertyGroupOwner;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSOrderPositionReturnedUnitsUnit   extends OMSBusinessObject
{

    private String serialNumber;
    
    /*TODO:
    returnReason   
    payment
    */
                    

    public OMSOrderPositionReturnedUnitsUnit serialNumber(String serialNumber)
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
        OMSOrderPositionReturnedUnitsUnit orderPositionReturned = (OMSOrderPositionReturnedUnitsUnit) o;
        return Objects.equals(serialNumber, orderPositionReturned.serialNumber);
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
        sb.append("class OMSOrderPositionReturnedUnitsUnit {\n");
        sb.append("    serialNumber: ").append(toIndentedString(serialNumber)).append("\n");
         sb.append("}");
        return sb.toString();
    }
}
