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
}