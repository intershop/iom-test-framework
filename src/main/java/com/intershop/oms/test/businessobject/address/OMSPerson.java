package com.intershop.oms.test.businessobject.address;

import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSCustomer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public OMSPerson salutation(String salutation)
    {
        this.salutation = salutation;
        return this;
    }

    public OMSPerson title(String title)
    {
        this.title = title;
        return this;
    }

    public OMSPerson firstName(String firstName)
    {
        this.firstName = firstName;
        return this;
    }

    public OMSPerson lastName(String lastName)
    {
        this.lastName = lastName;
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
        OMSPerson person = (OMSPerson) o;
        return Objects.equals(this.salutation, person.salutation) &&
                        Objects.equals(this.title, person.title) &&
                        Objects.equals(this.firstName, person.firstName) &&
                        Objects.equals(this.lastName, person.lastName);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(salutation, title, firstName, lastName);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class Person {\n");
        sb.append("    salutation: ").append(toIndentedString(salutation)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
        sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * @TODO: move to proper xml-handler
     *
     * @param prefix
     * @return
     */
    public String getSOAPRequestString(String prefix)
    {
        StringBuffer soapContent = new StringBuffer();
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