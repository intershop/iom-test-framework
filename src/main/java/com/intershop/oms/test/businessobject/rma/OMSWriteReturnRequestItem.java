package com.intershop.oms.test.businessobject.rma;

import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSWriteReturnRequestItem extends OMSBusinessObject
{
    private String productSerialNumber;

    public OMSWriteReturnRequestItem productSerialNumber(String productSerialNumber)
    {
        this.productSerialNumber = productSerialNumber;
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
        OMSWriteReturnRequestItem writeReturnRequestItem = (OMSWriteReturnRequestItem) o;
        return Objects.equals(this.productSerialNumber, writeReturnRequestItem.productSerialNumber);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(productSerialNumber);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OMSWriteReturnRequestItem {\n");
        sb.append("    productSerialNumber: ").append(toIndentedString(productSerialNumber)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
