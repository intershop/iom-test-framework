package com.intershop.oms.test.businessobject.address;

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
public class OMSContact extends OMSBusinessObject
{
    private String email;

    private String phone;

    private String mobile;

    private String fax;
}
