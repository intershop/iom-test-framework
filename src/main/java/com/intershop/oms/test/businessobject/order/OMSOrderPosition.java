package com.intershop.oms.test.businessobject.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderPositionConfirmed;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderPositionDispatched;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderPositionOrdered;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderPositionReturned;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderPositionSupplier;
import com.intershop.oms.test.businessobject.prices.OMSSumPrice;
import com.intershop.oms.test.businessobject.prices.OMSUnitPrice;

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
public class OMSOrderPosition extends OMSBusinessObject
{
    private Integer number;

    private String productUnit;

    private OMSProduct product;

    private String costCenter;

    private String project;

    private Map<String, Map<String, String>> additionalAttributes = null;

    private Integer quantity;

    private OMSSumPrice sum;

    private OMSUnitPrice unitPrice;

    private OMSShipping shipping;

    private String status;

    private OMSOrderPositionOrdered ordered;

    private OMSOrderPositionSupplier commissioned;

    private OMSOrderPositionConfirmed confirmed;

    private List<OMSOrderPositionDispatched> dispatched = null;

    private List<OMSOrderPositionReturned> returned = null;

    private Long id;

    public OMSOrderPosition putAdditionalAttributesItem(String key, Map<String, String> additionalAttributesItem)
    {
        if (additionalAttributes == null)
        {
            additionalAttributes = new HashMap<>();
        }
        additionalAttributes.put(key, additionalAttributesItem);
        return this;
    }

    public OMSOrderPosition addDispatchedItem(OMSOrderPositionDispatched dispatchedItem)
    {
        if (dispatched == null)
        {
            dispatched = new ArrayList<>();
        }
        dispatched.add(dispatchedItem);
        return this;
    }

   public OMSOrderPosition addReturnedItem(OMSOrderPositionReturned returnedItem)
    {
        if (returned == null)
        {
            returned = new ArrayList<>();
        }
        returned.add(returnedItem);
        return this;
    }
}