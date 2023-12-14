package com.intershop.oms.test.businessobject.orderstate;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.intershop.oms.rest.order.v2_0.model.DeliveryDate;
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
public class OMSOrderStateShipping extends OMSBusinessObject
{
    private String freightClass;
    private String selectedSupplier;
    private List<String> deliveryOptions = null;
    private DeliveryDate deliveryDate;
    private Integer expectedDeliveryDays;
    private Integer supplierExpectedDeliveryDays;
    private OffsetDateTime confirmedDeliveryDate;

    public OMSOrderStateShipping addDeliveryOptionsItem(String deliveryOptionsItem)
    {
        if (deliveryOptions == null)
        {
            deliveryOptions = new ArrayList<>();
        }
        deliveryOptions.add(deliveryOptionsItem);
        return this;
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderStateShipping freightClass(String freightClass)
    {
        return setFreightClass(freightClass);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderStateShipping selectedSupplier(String selectedSupplier)
    {
        return setSelectedSupplier(selectedSupplier);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderStateShipping deliveryOptions(List<String> deliveryOptions)
    {
        return setDeliveryOptions(deliveryOptions);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderStateShipping deliveryDate(DeliveryDate deliveryDate)
    {
        return setDeliveryDate(deliveryDate);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderStateShipping expectedDeliveryDays(Integer expectedDeliveryDays)
    {
        return setExpectedDeliveryDays(expectedDeliveryDays);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderStateShipping supplierExpectedDeliveryDays(Integer supplierExpectedDeliveryDays)
    {
        return setSupplierExpectedDeliveryDays(supplierExpectedDeliveryDays);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderStateShipping confirmedDeliveryDate(OffsetDateTime confirmedDeliveryDate)
    {
        return setConfirmedDeliveryDate(confirmedDeliveryDate);
    }
}