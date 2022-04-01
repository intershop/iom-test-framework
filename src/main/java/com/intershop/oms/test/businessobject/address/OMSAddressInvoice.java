package com.intershop.oms.test.businessobject.address;

import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSAddressInvoice extends OMSBusinessObject
{
    private OMSAddressLocation location;

    private OMSContact contact;

    private OMSAddressReceiver receiver;

    public OMSAddressInvoice location(OMSAddressLocation location)
    {
        this.location = location;
        return this;
    }

    public OMSAddressInvoice contact(OMSContact contact)
    {
        this.contact = contact;
        return this;
    }

    public OMSAddressInvoice receiver(OMSAddressReceiver receiver)
    {
        this.receiver = receiver;
        return this;
    }

    @Override
    public boolean equals(java.lang.Object o)
    {
        if (o == null)
        {
            return false;
        }
        if (this == o)
        {
            return true;
        }
        if (!o.getClass().isAssignableFrom(getClass()) && !getClass().isAssignableFrom(o.getClass()))
        {
            return false;
        }
        OMSAddressInvoice addressInvoice = (OMSAddressInvoice) o;
        return location.equals(addressInvoice.location) && contact.equals(addressInvoice.contact) && receiver.equals(addressInvoice.receiver);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(location, contact, receiver);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class AddressInvoice {\n");
        sb.append("    location: ").append(toIndentedString(location)).append("\n");
        sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
        sb.append("    receiver: ").append(toIndentedString(receiver)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
