package com.intershop.oms.test.businessobject.order;

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
    private String isbn;
    private Long ean;
    private Long productId;

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSProduct name(String name)
    {
        return setName(name);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSProduct number(String number)
    {
        return setNumber(number);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSProduct isbn(String isbn)
    {
        return setIsbn(isbn);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSProduct ean(Long ean)
    {
        return setEan(ean);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSProduct productId(Long productId)
    {
        return setProductId(productId);
    }
}