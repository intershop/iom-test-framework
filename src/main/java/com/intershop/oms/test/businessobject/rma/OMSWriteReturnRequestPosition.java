package com.intershop.oms.test.businessobject.rma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.communication.OMSDispatchPosition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSWriteReturnRequestPosition extends OMSBusinessObject
{
    private Integer positionNumber;

    private String productNumber;

    private String reason;

    private Integer quantity;

    private List<OMSWriteReturnRequestItem> items = null;

    private Map<String, String> customAttributes = new HashMap<>();
    
    public OMSWriteReturnRequestPosition(OMSDispatchPosition dispatchPosition)
    {
        setPositionNumber(dispatchPosition.getOrderPositionNumber());
        setQuantity(dispatchPosition.getDispatchedQuantity());
        setProductNumber(dispatchPosition.getArticleNo());
        setReason("RET010");
    }

    public OMSWriteReturnRequestPosition()
    {
    }

    public OMSWriteReturnRequestPosition positionNumber(Integer positionNumber) {

        this.positionNumber = positionNumber;
        return this;
    }

    public OMSWriteReturnRequestPosition productNumber(String productNumber)
    {
        this.productNumber = productNumber;
        return this;
    }

    public OMSWriteReturnRequestPosition reason(String reason)
    {
        this.reason = reason;
        return this;
    }

    public OMSWriteReturnRequestPosition quantity(Integer quantity)
    {
       this.quantity = quantity;
        return this;
    }

    public OMSWriteReturnRequestPosition items(List<OMSWriteReturnRequestItem> items)
    {
        this.items = items;
        return this;
    }

    public OMSWriteReturnRequestPosition addItemsItem(OMSWriteReturnRequestItem itemsItem)
    {
        if (this.items == null)
        {
            this.items = new ArrayList<>();
        }
        this.items.add(itemsItem);
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
        OMSWriteReturnRequestPosition writeReturnRequestPosition = (OMSWriteReturnRequestPosition) o;
        return Objects.equals(this.positionNumber, writeReturnRequestPosition.positionNumber) &&
                        Objects.equals(this.productNumber, writeReturnRequestPosition.productNumber) &&
                        Objects.equals(this.reason, writeReturnRequestPosition.reason) &&
                        Objects.equals(this.quantity, writeReturnRequestPosition.quantity) &&
                        Objects.equals(this.items, writeReturnRequestPosition.items);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(positionNumber, productNumber, reason, quantity, items);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OMSWriteReturnRequestPosition {\n");
        sb.append("    positionNumber: ").append(toIndentedString(positionNumber)).append("\n");
        sb.append("    productNumber: ").append(toIndentedString(productNumber)).append("\n");
        sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
        sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
        sb.append("    items: ").append(toIndentedString(items)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
