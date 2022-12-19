package com.intershop.oms.test.businessobject.rma;

import java.util.ArrayList;
import java.util.List;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSLink;

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
public class OMSPickupAddress extends OMSBusinessObject
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
    private Long id;
    private List<OMSLink> links = new ArrayList<>();
}
