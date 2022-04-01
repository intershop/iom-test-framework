package com.intershop.oms.test.businessobject.address;

import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSAddressShipping extends OMSBusinessObject
{
    private OMSAddressLocation location;

    private OMSContact contact;

    private OMSAddressReceiver receiver;

    public OMSAddressShipping location(OMSAddressLocation location)
    {
        this.location = location;
        return this;
    }

    public OMSAddressShipping contact(OMSContact contact)
    {
        this.contact = contact;
        return this;
    }

    public OMSAddressShipping receiver(OMSAddressReceiver receiver)
    {
        this.receiver = receiver;
        return this;
    }

    @Override
    public boolean equals(java.lang.Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null)
        {
            return false;
        }
        if (!o.getClass().isAssignableFrom(getClass()) && !getClass().isAssignableFrom(o.getClass()))
        {
            return false;
        }
        OMSAddressShipping addressShipping = (OMSAddressShipping) o;
        return Objects.equals(this.location, addressShipping.location) &&
                        Objects.equals(this.contact, addressShipping.contact) &&
                        Objects.equals(this.receiver, addressShipping.receiver);
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
        sb.append("class AddressShipping {\n");
        sb.append("    location: ").append(toIndentedString(location)).append("\n");
        sb.append("    contact: ").append(toIndentedString(contact)).append("\n");
        sb.append("    receiver: ").append(toIndentedString(receiver)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
