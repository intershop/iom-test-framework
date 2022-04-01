package com.intershop.oms.test.businessobject.rma;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSReturnableDataPosition extends OMSBusinessObject
{
    private Integer positionNumber;

    private Integer quantity;

    private List<OMSReturnableDataItem> items = null;

    private OMSReturnableDataProduct product;

    public OMSReturnableDataPosition positionNumber(Integer positionNumber)
    {
        this.positionNumber = positionNumber;
        return this;
    }

    public OMSReturnableDataPosition quantity(Integer quantity)
    {
        this.quantity = quantity;
        return this;
    }

    public OMSReturnableDataPosition items(List<OMSReturnableDataItem> items)
    {
        this.items = items;
        return this;
    }

    public OMSReturnableDataPosition addItemsItem(OMSReturnableDataItem itemsItem)
    {
        if (this.items == null)
        {
            this.items = new ArrayList<>();
        }
        this.items.add(itemsItem);
        return this;
    }

    public OMSReturnableDataPosition product(OMSReturnableDataProduct product)
    {
        this.product = product;
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
        OMSReturnableDataPosition returnableDataPosition = (OMSReturnableDataPosition) o;
        return Objects.equals(this.positionNumber, returnableDataPosition.positionNumber) &&
                        Objects.equals(this.quantity, returnableDataPosition.quantity) &&
                        Objects.equals(this.items, returnableDataPosition.items) &&
                        Objects.equals(this.product, returnableDataPosition.product);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(positionNumber, quantity, items, product);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OMSReturnableDataPosition {\n");
        sb.append("    positionNumber: ").append(toIndentedString(positionNumber)).append("\n");
        sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
        sb.append("    items: ").append(toIndentedString(items)).append("\n");
        sb.append("    product: ").append(toIndentedString(product)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
