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

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSShipping freightClass(String freightClass)
    {
        return setFreightClass(freightClass);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSShipping selectedSupplier(String selectedSupplier)
    {
        return setSelectedSupplier(selectedSupplier);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSShipping deliveryOptions(List<String> deliveryOptions)
    {
        return setDeliveryOptions(deliveryOptions);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSShipping deliveryDate(OMSDeliveryDate deliveryDate)
    {
        return setDeliveryDate(deliveryDate);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSShipping expectedDeliveryDays(Integer expectedDeliveryDays)
    {
        return setExpectedDeliveryDays(expectedDeliveryDays);
    }

    public OMSShipping addDeliveryOptionsItem(String deliveryOptionsItem)
    {
        if (this.deliveryOptions == null)
        {
            this.deliveryOptions = new ArrayList<>();
        }
        this.deliveryOptions.add(deliveryOptionsItem);
        return this;
    }
}