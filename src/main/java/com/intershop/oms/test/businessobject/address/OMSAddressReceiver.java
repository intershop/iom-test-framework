package com.intershop.oms.test.businessobject.address;

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
public class OMSAddressReceiver extends OMSBusinessObject
{
    public enum OMSAddressReceiverTypeEnum
    {
        PERSON("PERSON"),
        COMPANY("COMPANY");

        private String value;

        OMSAddressReceiverTypeEnum(String value)
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

        public static OMSAddressReceiverTypeEnum fromValue(String value)
        {
            for (OMSAddressReceiverTypeEnum b : OMSAddressReceiverTypeEnum.values())
            {
                if (b.value.equals(value))
                {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }

        public static class Adapter extends TypeAdapter<OMSAddressReceiverTypeEnum>
        {
            @Override
            public void write(final JsonWriter jsonWriter, final OMSAddressReceiverTypeEnum enumeration) throws IOException
            {
                jsonWriter.value(enumeration.getValue());
            }

            @Override
            public OMSAddressReceiverTypeEnum read(final JsonReader jsonReader) throws IOException
            {
                String value =  jsonReader.nextString();
                return OMSAddressReceiverTypeEnum.fromValue(value);
            }
        }
    }

    private OMSAddressReceiverTypeEnum addressReceiverType;

    private OMSPerson person;

    private String companyName;

    public OMSAddressReceiver addressReceiverType(OMSAddressReceiverTypeEnum addressReceiverType)
    {
        this.addressReceiverType = addressReceiverType;
        return this;
    }

    public OMSAddressReceiver person(OMSPerson person)
    {
        this.person = person;
        return this;
    }

    public OMSAddressReceiver companyName(String companyName)
    {
        this.companyName = companyName;
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
        OMSAddressReceiver addressReceiver = (OMSAddressReceiver) o;
        return Objects.equals(this.addressReceiverType, addressReceiver.addressReceiverType) &&
                        Objects.equals(this.person, addressReceiver.person) &&
                        Objects.equals(this.companyName, addressReceiver.companyName);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(addressReceiverType, person, companyName);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class AddressReceiver {\n");
        sb.append("    addressReceiverType: ").append(toIndentedString(addressReceiverType)).append("\n");
        sb.append("    person: ").append(toIndentedString(person)).append("\n");
        sb.append("    companyName: ").append(toIndentedString(companyName)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}