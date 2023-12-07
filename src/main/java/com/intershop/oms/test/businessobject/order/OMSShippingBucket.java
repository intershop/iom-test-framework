package com.intershop.oms.test.businessobject.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.address.OMSAddressShipping;
import com.intershop.oms.test.businessobject.prices.OMSCharge;

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
public class OMSShippingBucket extends OMSBusinessObject
{
    private String number;

    private OMSAddressShipping shippingAddress;

    private String shippingMethod;

    private List<OMSCharge> charges = null;

    private Map<String, Map<String, String>> additionalAttributes = null;

    private List<OMSOrderPosition> positions = new ArrayList<>();

    public OMSShippingBucket addChargesItem(OMSCharge chargesItem)
    {
        if (charges == null)
        {
            charges = new ArrayList<>();
        }
        charges.add(chargesItem);
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

    public OMSShippingBucket addPositionsItem(OMSOrderPosition positionsItem)
    {
        positions.add(positionsItem);
        return this;
    }
}