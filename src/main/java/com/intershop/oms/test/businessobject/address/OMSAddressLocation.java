package com.intershop.oms.test.businessobject.address;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSAddressLocation extends OMSBusinessObject
{
    private String city;

    private String postCode;

    private String district;

    private String countryCode;

    private List<String> additions = null;

    protected String type;

    public OMSAddressLocation()
    {
        this.type = this.getClass().getSimpleName();
    }

    public OMSAddressLocation city(String city)
    {
        this.city = city;
        return this;
    }

    public OMSAddressLocation postCode(String postCode)
    {
        this.postCode = postCode;
        return this;
    }

    public OMSAddressLocation district(String district)
    {
        this.district = district;
        return this;
    }

    public OMSAddressLocation countryCode(String countryCode)
    {
        this.countryCode = countryCode;
        return this;
    }

    public OMSAddressLocation additions(List<String> additions)
    {
        this.additions = additions;
        return this;
    }

    public OMSAddressLocation addAdditionsItem(String additionsItem)
    {
        if (this.additions == null)
        {
            this.additions = new ArrayList<>();
        }
        this.additions.add(additionsItem);
        return this;
    }

    public OMSAddressLocation type(String type)
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
        OMSAddressLocation addressLocation = (OMSAddressLocation) o;
        return Objects.equals(this.city, addressLocation.city) &&
                        Objects.equals(this.postCode, addressLocation.postCode) &&
                        Objects.equals(this.district, addressLocation.district) &&
                        Objects.equals(this.countryCode, addressLocation.countryCode) &&
                        Objects.equals(this.additions, addressLocation.additions) &&
                        Objects.equals(this.type, addressLocation.type);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(city, postCode, district, countryCode, additions, type);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class AddressLocation {\n");
        sb.append("    city: ").append(toIndentedString(city)).append("\n");
        sb.append("    postCode: ").append(toIndentedString(postCode)).append("\n");
        sb.append("    district: ").append(toIndentedString(district)).append("\n");
        sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
        sb.append("    additions: ").append(toIndentedString(additions)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
