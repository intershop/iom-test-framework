package com.intershop.oms.test.businessobject.order;

import java.time.OffsetDateTime;

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
public class OMSDeliveryDate extends OMSBusinessObject
{
    public enum OMSDeliveryDateTypeEnum
    {
        STANDARD("STANDARD"),
        EXPRESS("EXPRESS"),
        EARLIEST("EARLIEST"),
        FIXED("FIXED");

        private String value;

        OMSDeliveryDateTypeEnum(String value)
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

        public static OMSDeliveryDateTypeEnum fromValue(String value)
        {
            for (OMSDeliveryDateTypeEnum b : OMSDeliveryDateTypeEnum.values())
            {
                if (b.value.equals(value))
                {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    private OMSDeliveryDateTypeEnum deliveryDateType;

    private String name;

    private OffsetDateTime desiredDeliveryDate;

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSDeliveryDate deliveryDateType(OMSDeliveryDateTypeEnum deliveryDateType)
    {
        return setDeliveryDateType(deliveryDateType);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSDeliveryDate name(String name)
    {
        return setName(name);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSDeliveryDate desiredDeliveryDate(OffsetDateTime desiredDeliveryDate)
    {
        return setDesiredDeliveryDate(desiredDeliveryDate);
    }
}