package com.intershop.oms.test.businessobject.address;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSCustomer;

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
public class OMSPerson extends OMSBusinessObject
{
    private String salutation;

    private String title;

    private String firstName;

    private String lastName;

    public OMSPerson() {}

    public OMSPerson(OMSCustomer omsCustomer)
    {
        salutation = omsCustomer.getSalutation();
        title = omsCustomer.getSalutation();
        firstName = omsCustomer.getFirstName();
        lastName = omsCustomer.getLastName();
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSPerson salutation(String salutation)
    {
        return setSalutation(salutation);
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSPerson title(String title)
    {
        return setTitle(title);
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSPerson firstName(String firstName)
    {
        return setFirstName(firstName);
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSPerson lastName(String lastName)
    {
        return setLastName(lastName);
    }

    /**
     * @TODO: move to proper xml-handler
     *
     * @param prefix
     * @return
     */
    public String getSOAPRequestString(String prefix)
    {
        StringBuilder soapContent = new StringBuilder();
        soapContent.append("\n    <" +prefix+":Person ");
        if (firstName != null)
        {
            soapContent.append("firstName=\""+firstName+"\" ");
        }
        if (lastName != null)
        {
            soapContent.append("lastName=\""+lastName+"\" ");
        }
        if (salutation != null)
        {
            soapContent.append("salutation=\""+salutation+"\" ");
        }
        soapContent.append("/>");
        return soapContent.toString();
    }
}