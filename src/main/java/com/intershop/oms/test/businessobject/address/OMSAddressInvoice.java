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
public class OMSAddressInvoice extends OMSBusinessObject
{
    private OMSAddressLocation location;

    private OMSContact contact;

    private OMSAddressReceiver receiver;

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSAddressInvoice location(OMSAddressLocation location)
    {
        return setLocation(location);
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSAddressInvoice contact(OMSContact contact)
    {
        return setContact(contact);
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSAddressInvoice receiver(OMSAddressReceiver receiver)
    {
        return setReceiver(receiver);
    }
}
