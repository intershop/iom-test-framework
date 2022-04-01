package com.intershop.oms.test.businessobject.orderstate;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.intershop.oms.rest.order.v2_0.model.DeliveryDate;
import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSOrderStateShipping extends OMSBusinessObject
{
    private String freightClass;

    private String selectedSupplier;

    private List<String> deliveryOptions = null;

    private DeliveryDate deliveryDate;

    private Integer expectedDeliveryDays;

    private Integer supplierExpectedDeliveryDays;

    private OffsetDateTime confirmedDeliveryDate;

    public OMSOrderStateShipping freightClass(String freightClass)
    {
        this.freightClass = freightClass;
        return this;
    }

    public OMSOrderStateShipping selectedSupplier(String selectedSupplier)
    {
        this.selectedSupplier = selectedSupplier;
        return this;
    }

    public OMSOrderStateShipping deliveryOptions(List<String> deliveryOptions)
    {
        this.deliveryOptions = deliveryOptions;
        return this;
    }

    public OMSOrderStateShipping addDeliveryOptionsItem(String deliveryOptionsItem)
    {
        if (this.deliveryOptions == null)
        {
            this.deliveryOptions = new ArrayList<>();
        }
        this.deliveryOptions.add(deliveryOptionsItem);
        return this;
    }

    public OMSOrderStateShipping deliveryDate(DeliveryDate deliveryDate)
    {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public OMSOrderStateShipping expectedDeliveryDays(Integer expectedDeliveryDays)
    {
        this.expectedDeliveryDays = expectedDeliveryDays;
        return this;
    }

    public OMSOrderStateShipping supplierExpectedDeliveryDays(Integer supplierExpectedDeliveryDays)
    {
        this.supplierExpectedDeliveryDays = supplierExpectedDeliveryDays;
        return this;
    }

    public OMSOrderStateShipping confirmedDeliveryDate(OffsetDateTime confirmedDeliveryDate)
    {
        this.confirmedDeliveryDate = confirmedDeliveryDate;
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
        OMSOrderStateShipping orderStateShipping = (OMSOrderStateShipping) o;
        return Objects.equals(this.freightClass, orderStateShipping.freightClass) &&
                        Objects.equals(this.selectedSupplier, orderStateShipping.selectedSupplier) &&
                        Objects.equals(this.deliveryOptions, orderStateShipping.deliveryOptions) &&
                        Objects.equals(this.deliveryDate, orderStateShipping.deliveryDate) &&
                        Objects.equals(this.expectedDeliveryDays, orderStateShipping.expectedDeliveryDays) &&
                        Objects.equals(this.supplierExpectedDeliveryDays, orderStateShipping.supplierExpectedDeliveryDays) &&
                        Objects.equals(this.confirmedDeliveryDate, orderStateShipping.confirmedDeliveryDate);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(freightClass, selectedSupplier, deliveryOptions, deliveryDate, expectedDeliveryDays, supplierExpectedDeliveryDays, confirmedDeliveryDate);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrderStateShipping {\n");
        sb.append("    freightClass: ").append(toIndentedString(freightClass)).append("\n");
        sb.append("    selectedSupplier: ").append(toIndentedString(selectedSupplier)).append("\n");
        sb.append("    deliveryOptions: ").append(toIndentedString(deliveryOptions)).append("\n");
        sb.append("    deliveryDate: ").append(toIndentedString(deliveryDate)).append("\n");
        sb.append("    expectedDeliveryDays: ").append(toIndentedString(expectedDeliveryDays)).append("\n");
        sb.append("    supplierExpectedDeliveryDays: ").append(toIndentedString(supplierExpectedDeliveryDays)).append("\n");
        sb.append("    confirmedDeliveryDate: ").append(toIndentedString(confirmedDeliveryDate)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}