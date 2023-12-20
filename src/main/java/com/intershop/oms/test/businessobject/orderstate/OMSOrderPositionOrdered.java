package com.intershop.oms.test.businessobject.orderstate;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
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
public class OMSOrderPositionOrdered extends OMSBusinessObject
{
    private OMSOrderStateShipping shipping;
    private Integer quantity;
    private OMSSumPrice sum;
    private OMSUnitPrice unitPrice;

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionOrdered shipping(OMSOrderStateShipping shipping)
    {
        return setShipping(shipping);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionOrdered quantity(Integer quantity)
    {
        return setQuantity(quantity);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionOrdered sum(OMSSumPrice sum)
    {
        return setSum(sum);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionOrdered unitPrice(OMSUnitPrice unitPrice)
    {
        return setUnitPrice(unitPrice);
    }
}
