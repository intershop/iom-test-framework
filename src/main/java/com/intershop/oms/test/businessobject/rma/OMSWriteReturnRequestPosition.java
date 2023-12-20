package com.intershop.oms.test.businessobject.rma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.communication.OMSDispatchPosition;

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
public class OMSWriteReturnRequestPosition extends OMSBusinessObject
{
    private Integer positionNumber;
    private String productNumber;
    private String reason;
    private Integer quantity;

    private List<OMSWriteReturnRequestItem> items = null;

    private Map<String, String> customAttributes = new HashMap<>();
    
    public OMSWriteReturnRequestPosition() {}

    public OMSWriteReturnRequestPosition(OMSDispatchPosition dispatchPosition)
    {
        setPositionNumber(dispatchPosition.getOrderPositionNumber());
        setQuantity(dispatchPosition.getDispatchedQuantity());
        setProductNumber(dispatchPosition.getArticleNo());
        setReason("RET010");
    }

    public OMSWriteReturnRequestPosition addItemsItem(OMSWriteReturnRequestItem itemsItem)
    {
        if (items == null)
        {
            items = new ArrayList<>();
        }
        items.add(itemsItem);
        return this;
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSWriteReturnRequestPosition positionNumber(Integer positionNumber) {

        return setPositionNumber(positionNumber);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSWriteReturnRequestPosition productNumber(String productNumber)
    {
        return setProductNumber(productNumber);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSWriteReturnRequestPosition reason(String reason)
    {
        return setReason(reason);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSWriteReturnRequestPosition quantity(Integer quantity)
    {
        return setQuantity(quantity);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSWriteReturnRequestPosition items(List<OMSWriteReturnRequestItem> items)
    {
        return setItems(items);
    }
}
