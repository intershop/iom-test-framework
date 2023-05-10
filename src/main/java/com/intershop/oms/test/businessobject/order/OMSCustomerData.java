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
public class OMSCustomerData extends OMSBusinessObject
{
    public enum OMSCustomerDataTypeEnum
    {
        PERSON("PERSON"),
        COMPANY("COMPANY");

        private String value;

        OMSCustomerDataTypeEnum(String value)
        {
            this.value = value;
        }

        public String getValue()
        {
            return value;
        }

        @Override
        public String toString()
        {
            return String.valueOf(value);
        }

        public static OMSCustomerDataTypeEnum fromValue(String value)
        {
            for (OMSCustomerDataTypeEnum b : OMSCustomerDataTypeEnum.values())
            {
                if (b.value.equals(value))
                {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    private OMSCustomerDataTypeEnum customerDataType;

    private String orderNumber;

    private String shopCustomerNumber;

    private OMSCompanyData companyData;

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSCustomerData customerDataType(OMSCustomerDataTypeEnum customerDataType)
    {
        return setCustomerDataType(customerDataType);
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSCustomerData orderNumber(String orderNumber)
    {
        return setOrderNumber(orderNumber);
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSCustomerData shopCustomerNumber(String shopCustomerNumber)
    {
        return setShopCustomerNumber(shopCustomerNumber);
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSCustomerData companyData(OMSCompanyData companyData)
    {
        return setCompanyData(companyData);
    }
}