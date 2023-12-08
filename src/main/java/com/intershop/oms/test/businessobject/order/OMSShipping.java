package com.intershop.oms.test.businessobject.order;

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
public class OMSShipping extends OMSBusinessObject
{
    private String freightClass;

    private String selectedSupplier;

    private List<String> deliveryOptions = null;

    private OMSDeliveryDate deliveryDate;

    private Integer expectedDeliveryDays;

    public OMSShipping addDeliveryOptionsItem(String deliveryOptionsItem)
    {
        if (deliveryOptions == null)
        {
            deliveryOptions = new ArrayList<>();
        }
        deliveryOptions.add(deliveryOptionsItem);
        return this;
    }
}