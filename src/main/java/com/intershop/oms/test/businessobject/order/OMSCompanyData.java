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

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSCompanyData companyName(String companyName)
    {
        return setCompanyName(companyName);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSCompanyData department(String department)
    {
        return setDepartment(department);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSCompanyData lineOfBusiness(String lineOfBusiness)
    {
        return setLineOfBusiness(lineOfBusiness);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSCompanyData costCenterNumber(String costCenterNumber)
    {
        return setCostCenterNumber(costCenterNumber);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSCompanyData commercialRegisterNumber(String commercialRegisterNumber)
    {
        return setCommercialRegisterNumber(commercialRegisterNumber);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSCompanyData commercialRegisterLocation(String commercialRegisterLocation)
    {
        return setCommercialRegisterLocation(commercialRegisterLocation);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSCompanyData companyType(String companyType)
    {
        return setCompanyType(companyType);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSCompanyData vatNumber(String vatNumber)
    {
        return setVatNumber(vatNumber);
    }
}