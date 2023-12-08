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
