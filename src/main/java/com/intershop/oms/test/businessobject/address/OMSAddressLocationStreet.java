package com.intershop.oms.test.businessobject.address;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSAddressLocationStreet extends OMSAddressLocation
{
    private String street;

    private String streetNumber;

    public OMSAddressLocationStreet()
    {
        type = "AddressLocationStreet";
    }

    public OMSAddressLocationStreet street(String street)
    {
        this.street = street;
        return this;
    }

    public OMSAddressLocationStreet streetNumber(String streetNumber)
    {
        this.streetNumber = streetNumber;
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
        OMSAddressLocationStreet addressLocationStreet = (OMSAddressLocationStreet) o;
        return Objects.equals(this.street, addressLocationStreet.street) &&
                        Objects.equals(this.streetNumber, addressLocationStreet.streetNumber) &&
                        super.equals(o);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(street, streetNumber, super.hashCode());
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class AddressLocationStreet {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    street: ").append(toIndentedString(street)).append("\n");
        sb.append("    streetNumber: ").append(toIndentedString(streetNumber)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
