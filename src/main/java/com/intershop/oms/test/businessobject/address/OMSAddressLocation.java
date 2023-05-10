package com.intershop.oms.test.businessobject.address;

import java.util.ArrayList;
import java.util.List;

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
        type = getClass().getSimpleName();
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSAddressLocation city(String city)
    {
        return setCity(city);
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSAddressLocation postCode(String postCode)
    {
        return setPostCode(postCode);
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSAddressLocation district(String district)
    {
        return setDistrict(district);
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSAddressLocation countryCode(String countryCode)
    {
        return setCountryCode(countryCode);
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSAddressLocation type(String type)
    {
        return setType(type);
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSAddressLocation additions(List<String> additions)
    {
        return setAdditions(additions);
    }

    public OMSAddressLocation addAdditionsItem(String additionsItem)
    {
        if (additions == null)
        {
            additions = new ArrayList<>();
        }
        additions.add(additionsItem);
        return this;
    }
}
