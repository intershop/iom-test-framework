package com.intershop.oms.test.businessobject.order;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class OMSProduct extends OMSBusinessObject
{
    private String name;
    private String number;
    private String isbn;
    private Long ean;
    private Long productId;

    public OMSProduct name(String name)
    {
        this.name = name;
        return this;
    }

    public OMSProduct number(String number)
    {
        this.number = number;
        return this;
    }

    public OMSProduct isbn(String isbn)
    {
        this.isbn = isbn;
        return this;
    }

    public OMSProduct ean(Long ean)
    {
        this.ean = ean;
        return this;
    }

    public OMSProduct productId(Long productId)
    {
        this.productId = productId;
        return this;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class Product {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    number: ").append(toIndentedString(number)).append("\n");
        sb.append("    isbn: ").append(toIndentedString(isbn)).append("\n");
        sb.append("    ean: ").append(toIndentedString(ean)).append("\n");
        sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
