package com.intershop.oms.test.businessobject.address;

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
public class OMSAddressLocationStreet extends OMSAddressLocation
{
    private String street;

    private String streetNumber;

    public OMSAddressLocationStreet()
    {
        type = "AddressLocationStreet";
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSAddressLocationStreet street(String street)
    {
        return setStreet(street);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSAddressLocationStreet streetNumber(String streetNumber)
    {
        return setStreetNumber(streetNumber);
    }
}
