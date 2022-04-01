package com.intershop.oms.test.businessobject.rma;

import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSShopReturnReason extends OMSBusinessObject
{
    private String name;

    private String description;

    private String type;

    public OMSShopReturnReason name(String name)
    {
        this.name = name;
        return this;
    }

    public OMSShopReturnReason description(String description)
    {
        this.description = description;
        return this;
    }

    public OMSShopReturnReason type(String type)
    {
        this.type = type;
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
        OMSShopReturnReason shopReturnReason = (OMSShopReturnReason) o;
        return Objects.equals(this.name, shopReturnReason.name) &&
                        Objects.equals(this.description, shopReturnReason.description) &&
                        Objects.equals(this.type, shopReturnReason.type);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, description, type);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OMSShopReturnReason {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
