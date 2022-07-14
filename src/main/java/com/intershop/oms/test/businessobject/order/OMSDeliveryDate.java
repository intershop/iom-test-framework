package com.intershop.oms.test.businessobject.order;

import java.time.OffsetDateTime;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public OMSDeliveryDate deliveryDateType(OMSDeliveryDateTypeEnum deliveryDateType)
    {
        this.deliveryDateType = deliveryDateType;
        return this;
    }

    public OMSDeliveryDate name(String name)
    {
        this.name = name;
        return this;
    }

    public OMSDeliveryDate desiredDeliveryDate(OffsetDateTime desiredDeliveryDate)
    {
        this.desiredDeliveryDate = desiredDeliveryDate;
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
        OMSDeliveryDate deliveryDate = (OMSDeliveryDate) o;
        return Objects.equals(this.deliveryDateType, deliveryDate.deliveryDateType) &&
                        Objects.equals(this.name, deliveryDate.name) &&
                        Objects.equals(this.desiredDeliveryDate, deliveryDate.desiredDeliveryDate);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(deliveryDateType, name, desiredDeliveryDate);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class DeliveryDate {\n");
        sb.append("    deliveryDateType: ").append(toIndentedString(deliveryDateType)).append("\n");
        sb.append("    name: ").append(toIndentedString(name)).append("\n");
        sb.append("    desiredDeliveryDate: ").append(toIndentedString(desiredDeliveryDate)).append("\n");
        sb.append("}");
        return sb.toString();
    }

}