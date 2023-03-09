package com.intershop.oms.test.businessobject.order;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderPositionConfirmed;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderPositionDispatched;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderPositionOrdered;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderPositionReturned;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderPositionSupplier;
import com.intershop.oms.test.businessobject.prices.OMSSumPrice;
import com.intershop.oms.test.businessobject.prices.OMSTax;
import com.intershop.oms.test.businessobject.prices.OMSUnitPrice;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
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

    public OMSOrderPosition number(Integer number)
    {
        this.number = number;
        return this;
    }

    public OMSOrderPosition productUnit(String productUnit)
    {
        this.productUnit = productUnit;
        return this;
    }

    public OMSOrderPosition product(OMSProduct product)
    {
        this.product = product;
        return this;
    }

    public OMSOrderPosition costCenter(String costCenter)
    {
        this.costCenter = costCenter;
        return this;
    }

    public OMSOrderPosition project(String project)
    {
        this.project = project;
        return this;
    }

    public OMSOrderPosition additionalAttributes(Map<String, Map<String, String>> additionalAttributes)
    {
        this.additionalAttributes = additionalAttributes;
        return this;
    }

    public OMSOrderPosition putAdditionalAttributesItem(String key, Map<String, String> additionalAttributesItem)
    {
        if (this.additionalAttributes == null)
        {
            this.additionalAttributes = new HashMap<>();
        }
        this.additionalAttributes.put(key, additionalAttributesItem);
        return this;
    }

    public OMSOrderPosition quantity(Integer quantity)
    {
        this.quantity = quantity;
        return this;
    }

    public OMSOrderPosition sum(OMSSumPrice sum)
    {
        this.sum = sum;
        return this;
    }

    public OMSSumPrice getSum()
    {
        return sum;
    }

    public void setSum(OMSSumPrice sum)
    {
        this.sum = sum;
    }

    public OMSOrderPosition unitPrice(OMSUnitPrice unitPrice)
    {
        this.unitPrice = unitPrice;
        return this;
    }

    public OMSUnitPrice getUnitPrice()
    {
        return unitPrice;
    }

    public void setUnitPrice(OMSUnitPrice unitPrice)
    {
        this.unitPrice = unitPrice;
    }

    public OMSOrderPosition shipping(OMSShipping shipping)
    {
        this.shipping = shipping;
        return this;
    }

    public OMSShipping getShipping()
    {
        return shipping;
    }

    public void setShipping(OMSShipping shipping)
    {
        this.shipping = shipping;
    }

    public OMSOrderPosition status(String status)
    {

        this.status = status;
        return this;
    }

    public OMSOrderPosition ordered(OMSOrderPositionOrdered ordered)
    {
        this.ordered = ordered;
        return this;
    }

    public OMSOrderPosition commissioned(OMSOrderPositionSupplier commissioned)
    {
        this.commissioned = commissioned;
        return this;
    }

    public OMSOrderPosition confirmed(OMSOrderPositionConfirmed confirmed)
    {
        this.confirmed = confirmed;
        return this;
    }

    public OMSOrderPosition dispatched(List<OMSOrderPositionDispatched> dispatched)
    {
        this.dispatched = dispatched;
        return this;
    }

    public OMSOrderPosition addDispatchedItem(OMSOrderPositionDispatched dispatchedItem)
    {
        if (this.dispatched == null)
        {
            this.dispatched = new ArrayList<>();
        }
        this.dispatched.add(dispatchedItem);
        return this;
    }

    public OMSOrderPosition returned(List<OMSOrderPositionReturned> returned)
    {
        this.returned = returned;
        return this;
    }

    public OMSOrderPosition addReturnedItem(OMSOrderPositionReturned returnedItem)
    {
        if (this.returned == null)
        {
            this.returned = new ArrayList<>();
        }
        this.returned.add(returnedItem);
        return this;
    }

    public OMSOrderPosition id(Long id)
    {
        this.id = id;
        return this;
    }

    public Collection<? extends OMSTax> getUsedTaxes()
    {
        // TODO Auto-generated method stub
        return null;
    }

}
