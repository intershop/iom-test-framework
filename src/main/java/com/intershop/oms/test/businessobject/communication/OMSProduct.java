package com.intershop.oms.test.businessobject.communication;

import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSProduct extends OMSBusinessObject
{
    private String name;

    private String number;

    private String shopProductNumber;

    private String supplierProductNumber;

    public OMSProduct name(String name)
    {
        this.name = name;
        return this;
    }

    public OMSProduct number(String number)
    {
        this.number = number;
        return this;
    }

    /**
     * supported for &gt;= 2.11
     * @param shopProductNumber
     * @return
     */
    public OMSProduct shopProductNumber(String shopProductNumber)
    {
        this.shopProductNumber = shopProductNumber;
        return this;
      }

    /**
     * supported for &gt;= 2.11
     * @param supplierProductNumber
     * @return
     */
    public OMSProduct supplierProductNumber(String supplierProductNumber)
    {
        this.supplierProductNumber = supplierProductNumber;
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
        OMSProduct product = (OMSProduct) o;
        return Objects.equals(this.name, product.name) && Objects.equals(this.number, product.number) && Objects.equals(this.shopProductNumber, product.shopProductNumber) && Objects.equals(this.supplierProductNumber, product.supplierProductNumber);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, number);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OMSProduct {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    number: ").append(toIndentedString(number)).append("\n");
        sb.append("    shopProductNumber: ").append(toIndentedString(shopProductNumber)).append("\n");
        sb.append("    supplierProductNumber: ").append(toIndentedString(supplierProductNumber)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
