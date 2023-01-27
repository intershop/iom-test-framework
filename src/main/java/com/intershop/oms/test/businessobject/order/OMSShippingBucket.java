package com.intershop.oms.test.businessobject.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.address.OMSAddressShipping;
import com.intershop.oms.test.businessobject.prices.OMSCharge;
import com.intershop.oms.test.businessobject.prices.OMSTax;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSShippingBucket extends OMSBusinessObject
{
    private String number;

    private OMSAddressShipping shippingAddress;

    private String shippingMethod;

    private List<OMSCharge> charges = null;

    private Map<String, Map<String, String>> additionalAttributes = null;

    private List<OMSOrderPosition> positions = new ArrayList<>();

    public OMSShippingBucket number(String number)
    {
        this.number = number;
        return this;
    }

    public OMSShippingBucket shippingAddress(OMSAddressShipping shippingAddress)
    {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public OMSShippingBucket shippingMethod(String shippingMethod)
    {
        this.shippingMethod = shippingMethod;
        return this;
    }
    public OMSShippingBucket charges(List<OMSCharge> charges)
    {
        this.charges = charges;
        return this;
    }

    public OMSShippingBucket addChargesItem(OMSCharge chargesItem)
    {
        if (charges == null)
        {
            charges = new ArrayList<>();
        }
        charges.add(chargesItem);
        return this;
    }

    public OMSShippingBucket additionalAttributes(Map<String, Map<String, String>> additionalAttributes)
    {
        this.additionalAttributes = additionalAttributes;
        return this;
    }

    public OMSShippingBucket putAdditionalAttributesItem(String key, Map<String, String> additionalAttributesItem)
    {
        if (additionalAttributes == null)
        {
            additionalAttributes = new HashMap<>();
        }
        additionalAttributes.put(key, additionalAttributesItem);
        return this;
    }

    public OMSShippingBucket positions(List<OMSOrderPosition> positions)
    {
        this.positions = positions;
        return this;
    }

    public OMSShippingBucket addPositionsItem(OMSOrderPosition positionsItem)
    {
        positions.add(positionsItem);
        return this;
    }

    /**
     * @return a list of all the used taxes within these sales
     */
    public List<OMSTax> getUsedTaxes()
    {
        List<OMSTax> taxes = new ArrayList<>();
        if (positions != null)
        {
            positions.forEach(p -> taxes.addAll(p.getSum().getTaxes()));
        }
        if (charges != null)
        {
            charges.forEach(ch -> taxes.addAll(ch.getTaxes()));
        }
        return taxes;
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
        OMSShippingBucket shippingBucket = (OMSShippingBucket) o;
        return Objects.equals(number, shippingBucket.number) &&
                        Objects.equals(shippingAddress, shippingBucket.shippingAddress) &&
                        Objects.equals(shippingMethod, shippingBucket.shippingMethod) &&
                        Objects.equals(charges, shippingBucket.charges) &&
                        Objects.equals(additionalAttributes, shippingBucket.additionalAttributes) &&
                        Objects.equals(positions, shippingBucket.positions);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(number, shippingAddress, shippingMethod, charges, additionalAttributes, positions);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class ShippingBucket {\n");
        sb.append("    number: ").append(toIndentedString(number)).append("\n");
        sb.append("    shippingAddress: ").append(toIndentedString(shippingAddress)).append("\n");
        sb.append("    shippingMethod: ").append(toIndentedString(shippingMethod)).append("\n");
        sb.append("    charges: ").append(toIndentedString(charges)).append("\n");
        sb.append("    additionalAttributes: ").append(toIndentedString(additionalAttributes)).append("\n");
        sb.append("    positions: ").append(toIndentedString(positions)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
