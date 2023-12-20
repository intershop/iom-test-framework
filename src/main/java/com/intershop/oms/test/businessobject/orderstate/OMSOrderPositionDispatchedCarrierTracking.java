package com.intershop.oms.test.businessobject.orderstate;

import java.net.URI;

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
public class OMSOrderPositionDispatchedCarrierTracking extends OMSBusinessObject
{
    private String number;
    private URI url;

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionDispatchedCarrierTracking number(String number)
    {
      return setNumber(number);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionDispatchedCarrierTracking url(URI url)
    {
      return setUrl(url);
    }
}
