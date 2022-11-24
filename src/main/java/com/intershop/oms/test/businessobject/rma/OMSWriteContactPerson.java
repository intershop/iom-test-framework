package com.intershop.oms.test.businessobject.rma;

import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSWriteContactPerson extends OMSBusinessObject
{
    private String company;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String emailAddress;

    private String language;

    public OMSWriteContactPerson()
    {
        setCompany("Intershop Communication AG");
        setFirstName("John");
        setLastName("Doe");
        setPhoneNumber("0123456789");
        setEmailAddress("test@intershop.com");
        setLanguage("en");
    }

    public OMSWriteContactPerson company(String company)
    {
        this.company = company;
        return this;
    }

    public OMSWriteContactPerson firstName(String firstName)
    {
        this.firstName = firstName;
        return this;
    }

    public OMSWriteContactPerson lastName(String lastName)
    {
        this.lastName = lastName;
        return this;
    }

    public OMSWriteContactPerson phoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public OMSWriteContactPerson emailAddress(String emailAddress)
    {
        this.emailAddress = emailAddress;
        return this;
    }

    /**
     * @param language, ISO-639-1
     * @return
     */
    public OMSWriteContactPerson language(String language)
    {
        this.language = language;
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
        OMSWriteContactPerson writePickupAddress = (OMSWriteContactPerson) o;
        return Objects.equals(this.company, writePickupAddress.company) &&
                        Objects.equals(this.firstName, writePickupAddress.firstName) &&
                        Objects.equals(this.lastName, writePickupAddress.lastName) &&
                        Objects.equals(this.phoneNumber, writePickupAddress.phoneNumber) &&
                        Objects.equals(this.emailAddress, writePickupAddress.emailAddress) &&
                        Objects.equals(this.language, writePickupAddress.language);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(company, firstName, lastName, phoneNumber, emailAddress, language);
    }


    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OMSWriteContactPerson {\n");
        sb.append("    company: ").append(toIndentedString(company)).append("\n");
        sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
        sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
        sb.append("    phoneNumber: ").append(toIndentedString(phoneNumber)).append("\n");
        sb.append("    emailAddress: ").append(toIndentedString(emailAddress)).append("\n");
        sb.append("    language: ").append(toIndentedString(language)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}