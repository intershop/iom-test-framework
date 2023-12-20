package com.intershop.oms.test.businessobject.rma;

import java.util.ArrayList;
import java.util.List;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSReturnableDataPosition extends OMSBusinessObject
{
    private Integer positionNumber;

    private Integer quantity;

    private List<OMSReturnableDataItem> items = null;

    private OMSReturnableDataProduct product;

    public OMSReturnableDataPosition addItemsItem(OMSReturnableDataItem itemsItem)
    {
        if (this.items == null)
        {
            this.items = new ArrayList<>();
        }
        this.items.add(itemsItem);
        return this;
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSReturnableDataPosition positionNumber(Integer positionNumber)
    {
        return setPositionNumber(positionNumber);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSReturnableDataPosition quantity(Integer quantity)
    {
        return setQuantity(quantity);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSReturnableDataPosition items(List<OMSReturnableDataItem> items)
    {
        return setItems(items);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSReturnableDataPosition product(OMSReturnableDataProduct product)
    {
        return setProduct(product);
    }
}
