package com.intershop.oms.test.businessobject.order;

import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public OMSCompanyData companyName(String companyName)
    {
        this.companyName = companyName;
        return this;
    }

    public OMSCompanyData department(String department)
    {
        this.department = department;
        return this;
    }

    public OMSCompanyData lineOfBusiness(String lineOfBusiness)
    {
        this.lineOfBusiness = lineOfBusiness;
        return this;
    }

    public OMSCompanyData costCenterNumber(String costCenterNumber)
    {
        this.costCenterNumber = costCenterNumber;
        return this;
    }

    public OMSCompanyData commercialRegisterNumber(String commercialRegisterNumber)
    {
        this.commercialRegisterNumber = commercialRegisterNumber;
        return this;
    }

    public OMSCompanyData commercialRegisterLocation(String commercialRegisterLocation)
    {
        this.commercialRegisterLocation = commercialRegisterLocation;
        return this;
    }

    public OMSCompanyData companyType(String companyType)
    {
        this.companyType = companyType;
        return this;
    }

    public OMSCompanyData vatNumber(String vatNumber)
    {
        this.vatNumber = vatNumber;
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
        OMSCompanyData companyData = (OMSCompanyData) o;
        return Objects.equals(this.companyName, companyData.companyName) &&
                        Objects.equals(this.department, companyData.department) &&
                        Objects.equals(this.lineOfBusiness, companyData.lineOfBusiness) &&
                        Objects.equals(this.costCenterNumber, companyData.costCenterNumber) &&
                        Objects.equals(this.commercialRegisterNumber, companyData.commercialRegisterNumber) &&
                        Objects.equals(this.commercialRegisterLocation, companyData.commercialRegisterLocation) &&
                        Objects.equals(this.companyType, companyData.companyType) &&
                        Objects.equals(this.vatNumber, companyData.vatNumber);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(companyName, department, lineOfBusiness, costCenterNumber, commercialRegisterNumber, commercialRegisterLocation, companyType, vatNumber);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class CompanyData {\n");
        sb.append("    companyName: ").append(toIndentedString(companyName)).append("\n");
        sb.append("    department: ").append(toIndentedString(department)).append("\n");
        sb.append("    lineOfBusiness: ").append(toIndentedString(lineOfBusiness)).append("\n");
        sb.append("    costCenterNumber: ").append(toIndentedString(costCenterNumber)).append("\n");
        sb.append("    commercialRegisterNumber: ").append(toIndentedString(commercialRegisterNumber)).append("\n");
        sb.append("    commercialRegisterLocation: ").append(toIndentedString(commercialRegisterLocation)).append("\n");
        sb.append("    companyType: ").append(toIndentedString(companyType)).append("\n");
        sb.append("    vatNumber: ").append(toIndentedString(vatNumber)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
