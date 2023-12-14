package com.intershop.oms.test.businessobject.communication;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSCarrier packages(Integer packages)
    {
        return setPackages(packages);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSCarrier trackingNumber(String trackingNumber)
    {
        return setTrackingNumber(trackingNumber);
    }
}
