package com.intershop.oms.test.businessobject.gdpr;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Represents an address of a customer.
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSAddress extends OMSBusinessObject
{
    private String firstName;
    private String lastName;
    private String title;
    private String companyName;
    private String street;
    private String houseNumber;
    private String addressAddition1;
    private String addressAddition2;
    private String addressAddition3;
    private String addressAddition4;
    private String addressType;
    private String postCode;
    private String city;
    private String district;
    private String country;
    private String email;
    private String fax;
    private String mobilePhone;
    private String phone1;
    private String phone2;
    private Boolean isContainerFreightStation;
    private Boolean isDefault;
    private String gender;
    private String salutation;
    private String poBox;
    private Boolean nightShipping;
}

