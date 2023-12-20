package com.intershop.oms.test.businessobject.rma;

import java.util.Objects;

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

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSWritePickupAddress company(String company)
    {
        return setCompany(company);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSWritePickupAddress firstName(String firstName)
    {
        return setFirstName(firstName);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSWritePickupAddress lastName(String lastName)
    {
        return setLastName(lastName);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSWritePickupAddress streetName(String streetName)
    {
        return setStreetName(streetName);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSWritePickupAddress houseNumber(String houseNumber)
    {
        return setHouseNumber(houseNumber);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSWritePickupAddress postCode(String postCode)
    {
        return setPostCode(postCode);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSWritePickupAddress city(String city)
    {
        return setCity(city);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSWritePickupAddress countryCode(String countryCode)
    {
        return setCountryCode(countryCode);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSWritePickupAddress district(String district)
    {
        return setDistrict(district);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSWritePickupAddress additionFirstLine(String additionFirstLine)
    {
        return setAdditionFirstLine(additionFirstLine);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSWritePickupAddress additionSecondLine(String additionSecondLine)
    {
        return setAdditionSecondLine(additionSecondLine);
    }
}