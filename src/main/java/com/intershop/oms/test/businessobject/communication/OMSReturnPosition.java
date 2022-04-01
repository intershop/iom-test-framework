package com.intershop.oms.test.businessobject.communication;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSPropertyGroupOwner;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Accessors(chain = true)
public class OMSReturnPosition extends OMSBusinessObject implements OMSPropertyGroupOwner
{
    private Integer orderPositionNumber;

    private Integer returnedQuantity;

    private OMSProduct product;

    private List<OMSReturnItem> items = new ArrayList<>();

    private Map<String, Map<String, String>> propertyGroups = new HashMap<>();

    /**
     * CAUTION: This is NO DEEP COPY! Lists and the product are only referenced!
     * Only to be used to change returned quantity...
     */
    public OMSReturnPosition(OMSReturnPosition returnPosition)
    {
        setProduct(returnPosition.getProduct());
        setReturnedQuantity(returnPosition.getReturnedQuantity());
        setItems(returnPosition.getItems());
        setPropertyGroups(returnPosition.getPropertyGroups());
        setOrderPositionNumber(returnPosition.getOrderPositionNumber());
    }

    public OMSReturnPosition orderPositionNumber(Integer orderPositionNumber)
    {
        this.orderPositionNumber = orderPositionNumber;
        return this;
    }

    public OMSReturnPosition() {}

    @Override
    public Map<String, Map<String, String>> getPropertyGroups()
    {
        return propertyGroups;
    }
}