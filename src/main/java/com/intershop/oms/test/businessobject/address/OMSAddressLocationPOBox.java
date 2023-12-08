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
public class OMSAddressLocationPOBox extends OMSAddressLocation
{
    private String postBox;

    public OMSAddressLocationPOBox()
    {
        type = "AddressLocationPOBox";
    }
}
