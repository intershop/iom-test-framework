package com.intershop.oms.test.businessobject.order;

import java.io.IOException;
import java.util.Objects;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

        public static class Adapter extends TypeAdapter<OMSCustomerDataTypeEnum>
        {
            @Override
            public void write(final JsonWriter jsonWriter, final OMSCustomerDataTypeEnum enumeration) throws IOException
            {
                jsonWriter.value(enumeration.getValue());
            }

            @Override
            public OMSCustomerDataTypeEnum read(final JsonReader jsonReader) throws IOException
            {
                String value =  jsonReader.nextString();
                return OMSCustomerDataTypeEnum.fromValue(value);
            }
        }
    }

    private OMSCustomerDataTypeEnum customerDataType;

    private String orderNumber;

    private String shopCustomerNumber;

    private OMSCompanyData companyData;

    public OMSCustomerData customerDataType(OMSCustomerDataTypeEnum customerDataType)
    {
        this.customerDataType = customerDataType;
        return this;
    }

    public OMSCustomerData orderNumber(String orderNumber)
    {
        this.orderNumber = orderNumber;
        return this;
    }

    public OMSCustomerData shopCustomerNumber(String shopCustomerNumber)
    {
        this.shopCustomerNumber = shopCustomerNumber;
        return this;
    }

    public OMSCustomerData companyData(OMSCompanyData companyData)
    {
        this.companyData = companyData;
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
        OMSCustomerData customerData = (OMSCustomerData) o;
        return Objects.equals(this.customerDataType, customerData.customerDataType) &&
                        Objects.equals(this.orderNumber, customerData.orderNumber) &&
                        Objects.equals(this.shopCustomerNumber, customerData.shopCustomerNumber) &&
                        Objects.equals(this.companyData, customerData.companyData);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(customerDataType, orderNumber, shopCustomerNumber, companyData);
    }


    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class CustomerData {\n");
        sb.append("    customerDataType: ").append(toIndentedString(customerDataType)).append("\n");
        sb.append("    orderNumber: ").append(toIndentedString(orderNumber)).append("\n");
        sb.append("    shopCustomerNumber: ").append(toIndentedString(shopCustomerNumber)).append("\n");
        sb.append("    companyData: ").append(toIndentedString(companyData)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
