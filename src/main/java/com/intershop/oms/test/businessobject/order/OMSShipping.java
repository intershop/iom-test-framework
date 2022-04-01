package com.intershop.oms.test.businessobject.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSShipping extends OMSBusinessObject
{
    private String freightClass;

    private String selectedSupplier;

    private List<String> deliveryOptions = null;

    private OMSDeliveryDate deliveryDate;

    private Integer expectedDeliveryDays;

    public OMSShipping freightClass(String freightClass)
    {
        this.freightClass = freightClass;
        return this;
    }

    public OMSShipping selectedSupplier(String selectedSupplier)
    {
        this.selectedSupplier = selectedSupplier;
        return this;
    }

    public OMSShipping deliveryOptions(List<String> deliveryOptions)
    {
        this.deliveryOptions = deliveryOptions;
        return this;
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

    public OMSShipping deliveryDate(OMSDeliveryDate deliveryDate)
    {
        this.deliveryDate = deliveryDate;
        return this;
    }

    public OMSShipping expectedDeliveryDays(Integer expectedDeliveryDays)
    {
        this.expectedDeliveryDays = expectedDeliveryDays;
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
        OMSShipping shipping = (OMSShipping) o;
        return Objects.equals(this.freightClass, shipping.freightClass) &&
                        Objects.equals(this.selectedSupplier, shipping.selectedSupplier) &&
                        Objects.equals(this.deliveryOptions, shipping.deliveryOptions) &&
                        Objects.equals(this.deliveryDate, shipping.deliveryDate) &&
                        Objects.equals(this.expectedDeliveryDays, shipping.expectedDeliveryDays);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(freightClass, selectedSupplier, deliveryOptions, deliveryDate, expectedDeliveryDays);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class Shipping {\n");
        sb.append("    freightClass: ").append(toIndentedString(freightClass)).append("\n");
        sb.append("    selectedSupplier: ").append(toIndentedString(selectedSupplier)).append("\n");
        sb.append("    deliveryOptions: ").append(toIndentedString(deliveryOptions)).append("\n");
        sb.append("    deliveryDate: ").append(toIndentedString(deliveryDate)).append("\n");
        sb.append("    expectedDeliveryDays: ").append(toIndentedString(expectedDeliveryDays)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
