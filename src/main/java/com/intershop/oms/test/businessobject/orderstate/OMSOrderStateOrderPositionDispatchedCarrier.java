package com.intershop.oms.test.businessobject.orderstate;

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
public class OMSOrderStateOrderPositionDispatchedCarrier extends OMSBusinessObject
{
    private String name;
    private Integer packages;
    private OMSOrderPositionDispatchedCarrierTracking shipmentTracking;

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderStateOrderPositionDispatchedCarrier name(String name)
    {
        return setName(name);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderStateOrderPositionDispatchedCarrier packages(Integer packages)
    {
        return setPackages(packages);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderStateOrderPositionDispatchedCarrier shipmentTracking(OMSOrderPositionDispatchedCarrierTracking shipmentTracking)
    {
        return setShipmentTracking(shipmentTracking);
    }
}