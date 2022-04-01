package com.intershop.oms.test.businessobject.orderstate;

import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSOrderStateOrderPositionDispatchedCarrier extends OMSBusinessObject
{
    private String name;

    private Integer packages;

    private OMSOrderPositionDispatchedCarrierTracking shipmentTracking;

    public OMSOrderStateOrderPositionDispatchedCarrier name(String name)
    {
        this.name = name;
        return this;
    }

    public OMSOrderStateOrderPositionDispatchedCarrier packages(Integer packages)
    {
        this.packages = packages;
        return this;
    }

    public OMSOrderStateOrderPositionDispatchedCarrier shipmentTracking(OMSOrderPositionDispatchedCarrierTracking shipmentTracking)
    {
        this.shipmentTracking = shipmentTracking;
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
        OMSOrderStateOrderPositionDispatchedCarrier orderStateOrderPositionDispatchedCarrier = (OMSOrderStateOrderPositionDispatchedCarrier) o;
        return Objects.equals(this.name, orderStateOrderPositionDispatchedCarrier.name) &&
                        Objects.equals(this.packages, orderStateOrderPositionDispatchedCarrier.packages) &&
                        Objects.equals(this.shipmentTracking, orderStateOrderPositionDispatchedCarrier.shipmentTracking);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, packages, shipmentTracking);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrderStateOrderPositionDispatchedCarrier {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    packages: ").append(toIndentedString(packages)).append("\n");
        sb.append("    shipmentTracking: ").append(toIndentedString(shipmentTracking)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}