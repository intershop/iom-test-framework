package com.intershop.oms.test.businessobject.communication;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSNetPurchasePrice;
import com.intershop.oms.test.businessobject.OMSPropertyGroupOwner;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class OMSOrderResponsePosition extends OMSBusinessObject implements OMSPropertyGroupOwner
{
    private Integer orderPositionNumber;

    private OMSProduct product;

    private Integer quantityCanceled;

    private OMSConfirmedDelivery confirmedDelivery;

    private OMSBackorderedDelivery backorderedDelivery;

    private OMSNetPurchasePrice netPurchasePrice;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Map<String, Map<String, String>> propertyGroups = new HashMap<>();

    public OMSOrderResponsePosition(OMSOrderResponsePosition orderResponsePosition)
    {
        setProduct(orderResponsePosition.getProduct());
        setQuantityCanceled(orderResponsePosition.getQuantityCanceled());
        setConfirmedDelivery(orderResponsePosition.getConfirmedDelivery());
        setNetPurchasePrice(orderResponsePosition.getNetPurchasePrice());
        this.propertyGroups = orderResponsePosition.getPropertyGroups();
        setOrderPositionNumber(orderResponsePosition.getOrderPositionNumber());
    }

    public OMSOrderResponsePosition()
    {
        // FIXME should be empty constructor
        setConfirmedDelivery(new OMSConfirmedDelivery());
        setNetPurchasePrice(new OMSNetPurchasePrice());
    }

    @Override
    public Map<String, Map<String, String>> getPropertyGroups()
    {
        return propertyGroups;
    }
}