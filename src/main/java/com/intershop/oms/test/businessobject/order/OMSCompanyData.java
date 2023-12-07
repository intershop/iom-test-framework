package com.intershop.oms.test.businessobject.order;

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
public class OMSCompanyData extends OMSBusinessObject
{
    private String companyName;

    private String department;

    private String lineOfBusiness;

    private String costCenterNumber;

    private String commercialRegisterNumber;

    private String commercialRegisterLocation;

    private String companyType;

    private String vatNumber;
}