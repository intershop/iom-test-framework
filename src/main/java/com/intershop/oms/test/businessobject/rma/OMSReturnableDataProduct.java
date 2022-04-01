package com.intershop.oms.test.businessobject.rma;

import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSReturnableDataProduct extends OMSBusinessObject
{
    private String number;

    private String name;

    public OMSReturnableDataProduct number(String number)
    {
        this.number = number;
        return this;
    }

    public OMSReturnableDataProduct name(String name)
    {
        this.name = name;
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
        OMSReturnableDataProduct returnableDataProduct = (OMSReturnableDataProduct) o;
        return Objects.equals(this.number, returnableDataProduct.number) && Objects.equals(this.name, returnableDataProduct.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(number, name);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OMSReturnableDataProduct {\n");
        sb.append("    number: ").append(toIndentedString(number)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
