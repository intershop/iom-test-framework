package com.intershop.oms.test.businessobject.rma;

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
public class OMSContactPerson extends OMSBusinessObject
{
    private String company;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;
    private String language;
}
