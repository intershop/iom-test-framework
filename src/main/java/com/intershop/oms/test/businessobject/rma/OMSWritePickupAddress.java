package com.intershop.oms.test.businessobject.rma;

import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSWritePickupAddress extends OMSBusinessObject
{
    private String company;

    private String firstName;

    private String lastName;

    private String streetName;

    private String houseNumber;

    private String postCode;

    private String city;

    private String countryCode;

    private String district;

    private String additionFirstLine;

    private String additionSecondLine;

    public OMSWritePickupAddress()
    {
        setCompany("Intershop Communication AG");
        setFirstName("John");
        setLastName("Doe");
        setStreetName("Musterstrasse");
        setHouseNumber("24");
        setPostCode("12054");
        setCity("Berlin");
        setCountryCode("DEU");
        setDistrict("PickupDistrict");
        setAdditionFirstLine("Finanz");
        setAdditionSecondLine("3. Floor");
    }

    public OMSWritePickupAddress company(String company)
    {
        this.company = company;
        return this;
    }

    public OMSWritePickupAddress firstName(String firstName)
    {
        this.firstName = firstName;
        return this;
    }

    public OMSWritePickupAddress lastName(String lastName)
    {
        this.lastName = lastName;
        return this;
    }

    public OMSWritePickupAddress streetName(String streetName)
    {
        this.streetName = streetName;
        return this;
    }

    public OMSWritePickupAddress houseNumber(String houseNumber)
    {
        this.houseNumber = houseNumber;
        return this;
    }

    public OMSWritePickupAddress postCode(String postCode)
    {
        this.postCode = postCode;
        return this;
    }

    public OMSWritePickupAddress city(String city)
    {
        this.city = city;
        return this;
    }

    public OMSWritePickupAddress countryCode(String countryCode)
    {
        this.countryCode = countryCode;
        return this;
    }

    public OMSWritePickupAddress district(String district)
    {
        this.district = district;
        return this;
    }

    public OMSWritePickupAddress additionFirstLine(String additionFirstLine)
    {
        this.additionFirstLine = additionFirstLine;
        return this;
    }

    public OMSWritePickupAddress additionSecondLine(String additionSecondLine)
    {
        this.additionSecondLine = additionSecondLine;
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
        OMSWritePickupAddress writePickupAddress = (OMSWritePickupAddress) o;
        return Objects.equals(this.company, writePickupAddress.company) &&
                        Objects.equals(this.firstName, writePickupAddress.firstName) &&
                        Objects.equals(this.lastName, writePickupAddress.lastName) &&
                        Objects.equals(this.streetName, writePickupAddress.streetName) &&
                        Objects.equals(this.houseNumber, writePickupAddress.houseNumber) &&
                        Objects.equals(this.postCode, writePickupAddress.postCode) &&
                        Objects.equals(this.city, writePickupAddress.city) &&
                        Objects.equals(this.countryCode, writePickupAddress.countryCode) &&
                        Objects.equals(this.district, writePickupAddress.district) &&
                        Objects.equals(this.additionFirstLine, writePickupAddress.additionFirstLine) &&
                        Objects.equals(this.additionSecondLine, writePickupAddress.additionSecondLine);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(company, firstName, lastName, streetName, houseNumber, postCode, city, countryCode, district, additionFirstLine, additionSecondLine);
    }


    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OMSWritePickupAddress {\n");
        sb.append("    company: ").append(toIndentedString(company)).append("\n");
        sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
        sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
        sb.append("    streetName: ").append(toIndentedString(streetName)).append("\n");
        sb.append("    houseNumber: ").append(toIndentedString(houseNumber)).append("\n");
        sb.append("    postCode: ").append(toIndentedString(postCode)).append("\n");
        sb.append("    city: ").append(toIndentedString(city)).append("\n");
        sb.append("    countryCode: ").append(toIndentedString(countryCode)).append("\n");
        sb.append("    district: ").append(toIndentedString(district)).append("\n");
        sb.append("    additionFirstLine: ").append(toIndentedString(additionFirstLine)).append("\n");
        sb.append("    additionSecondLine: ").append(toIndentedString(additionSecondLine)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}