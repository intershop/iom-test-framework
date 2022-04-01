package com.intershop.oms.test.businessobject.address;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSAddressLocationPOBox extends OMSAddressLocation
{
    private String postBox;

    public OMSAddressLocationPOBox()
    {
        type = "AddressLocationPOBox";
    }

    public OMSAddressLocationPOBox postBox(String postBox)
    {
        this.postBox = postBox;
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
        OMSAddressLocationPOBox addressLocationPOBox = (OMSAddressLocationPOBox) o;
        return Objects.equals(this.postBox, addressLocationPOBox.postBox) && super.equals(o);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(postBox, super.hashCode());
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class AddressLocationPOBox {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    postBox: ").append(toIndentedString(postBox)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
