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
    }

    private OMSAddressReceiverTypeEnum addressReceiverType;

    private OMSPerson person;

    private String companyName;

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSAddressReceiver addressReceiverType(OMSAddressReceiverTypeEnum addressReceiverType)
    {
        return setAddressReceiverType(addressReceiverType);    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSAddressReceiver person(OMSPerson person)
    {
        return setPerson(person);
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSAddressReceiver companyName(String companyName)
    {
        return setCompanyName(companyName);
    }
}