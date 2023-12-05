package com.intershop.oms.test.businessobject.communication;

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
public class OMSProduct extends OMSBusinessObject
{
    private String name;

    private String number;

    private String shopProductNumber;

    private String supplierProductNumber;

    @Deprecated(since = "6.0.0", forRemoval = true)
    public OMSProduct name(String name)
    {
        return setName(name);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSProduct number(String number)
    {
        return setNumber(number);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSProduct shopProductNumber(String shopProductNumber)
    {
        return setShopProductNumber(shopProductNumber);
      }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSProduct supplierProductNumber(String supplierProductNumber)
    {
        return setSupplierProductNumber(supplierProductNumber);
    }
}
