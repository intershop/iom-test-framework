package com.intershop.oms.test.businessobject.address;

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
public class OMSAddressShipping extends OMSBusinessObject
{
    private OMSAddressLocation location;

    private OMSContact contact;

    private OMSAddressReceiver receiver;
}
