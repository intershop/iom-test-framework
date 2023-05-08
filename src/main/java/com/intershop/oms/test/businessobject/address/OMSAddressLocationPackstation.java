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
public class OMSAddressLocationPackstation extends OMSAddressLocation
{
    private String userId;

    private String stationNumber;

    public OMSAddressLocationPackstation()
    {
        type = "AddressLocationPackstation";
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSAddressLocationPackstation userId(String userId)
    {
        return setUserId(userId);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSAddressLocationPackstation stationNumber(String stationNumber)
    {
        return setStationNumber(stationNumber);
    }
}
