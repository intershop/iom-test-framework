package com.intershop.oms.test.businessobject.communication;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSCarrier extends OMSBusinessObject
{
    private String name;

    private Integer packages;

    private String trackingNumber;

    public OMSCarrier()
    {
        setName("DHL");
        setPackages(1);
        setTrackingNumber("TestTrackingNumber" +'_' + new SimpleDateFormat("yy-MM-dd-HH:mm:ss.SSS").format(Calendar.getInstance().getTime()));
    }

    public OMSCarrier(String name)
    {
        this.name = name;
    }

    public OMSCarrier packages(Integer packages)
    {
        this.packages = packages;
        return this;
    }

    public OMSCarrier trackingNumber(String trackingNumber)
    {
        this.trackingNumber = trackingNumber;
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
        OMSCarrier carrier = (OMSCarrier) o;
        return Objects.equals(this.name, carrier.name) &&
                        Objects.equals(this.packages, carrier.packages) &&
                        Objects.equals(this.trackingNumber, carrier.trackingNumber);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, packages, trackingNumber);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OMSCarrier {\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    packages: ").append(toIndentedString(packages)).append("\n");
        sb.append("    trackingNumber: ").append(toIndentedString(trackingNumber)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
