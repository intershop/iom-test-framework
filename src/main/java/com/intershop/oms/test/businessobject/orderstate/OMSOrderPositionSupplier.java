package com.intershop.oms.test.businessobject.orderstate;

import java.time.OffsetDateTime;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSOrderPositionSupplier extends OMSBusinessObject
{
    private String name;

    private OffsetDateTime commissionDate;

    public OMSOrderPositionSupplier name(String name)
    {
        this.name = name;
        return this;
    }

    public OMSOrderPositionSupplier commissionDate(OffsetDateTime commissionDate)
    {
        this.commissionDate = commissionDate;
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
        OMSOrderPositionSupplier orderPositionSupplier = (OMSOrderPositionSupplier) o;
        return Objects.equals(this.name, orderPositionSupplier.name) && Objects.equals(this.commissionDate, orderPositionSupplier.commissionDate);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, commissionDate);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrderPositionSupplier {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    commissionDate: ").append(toIndentedString(commissionDate)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
