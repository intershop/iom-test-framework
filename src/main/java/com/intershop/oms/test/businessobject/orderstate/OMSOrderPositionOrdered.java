package com.intershop.oms.test.businessobject.orderstate;

import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.prices.OMSSumPrice;
import com.intershop.oms.test.businessobject.prices.OMSUnitPrice;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSOrderPositionOrdered extends OMSBusinessObject
{
    private OMSOrderStateShipping shipping;

    private Integer quantity;

    private OMSSumPrice sum;

    private OMSUnitPrice unitPrice;

    public OMSOrderPositionOrdered shipping(OMSOrderStateShipping shipping)
    {
        this.shipping = shipping;
        return this;
    }

    public OMSOrderPositionOrdered quantity(Integer quantity)
    {
        this.quantity = quantity;
        return this;
    }

    public OMSOrderPositionOrdered sum(OMSSumPrice sum)
    {
        this.sum = sum;
        return this;
    }

    public OMSOrderPositionOrdered unitPrice(OMSUnitPrice unitPrice)
    {
        this.unitPrice = unitPrice;
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
        OMSOrderPositionOrdered orderPositionOrdered = (OMSOrderPositionOrdered) o;
        return Objects.equals(this.shipping, orderPositionOrdered.shipping) &&
                        Objects.equals(this.quantity, orderPositionOrdered.quantity) &&
                        Objects.equals(this.sum, orderPositionOrdered.sum) &&
                        Objects.equals(this.unitPrice, orderPositionOrdered.unitPrice);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(shipping, quantity, sum, unitPrice);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrderPositionOrdered {\n");
        sb.append("    shipping: ").append(toIndentedString(shipping)).append("\n");
        sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
        sb.append("    sum: ").append(toIndentedString(sum)).append("\n");
        sb.append("    unitPrice: ").append(toIndentedString(unitPrice)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
