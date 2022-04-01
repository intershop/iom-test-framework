package com.intershop.oms.test.businessobject.rma;

import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSReturnableDataItem extends OMSBusinessObject
{
    private String productSerialNumber;

    public OMSReturnableDataItem productSerialNumber(String productSerialNumber)
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
        OMSReturnableDataItem returnableDataItem = (OMSReturnableDataItem) o;
        return Objects.equals(this.productSerialNumber, returnableDataItem.productSerialNumber);
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
        sb.append("class OMSReturnableDataItem {\n");
        sb.append("    productSerialNumber: ").append(toIndentedString(productSerialNumber)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
