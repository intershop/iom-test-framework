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
public class OMSChangeRequest extends OMSBusinessObject
{
    private OMSOrder changeRequest;

    private String changeRequestId;

    private String status;

    private OffsetDateTime updateDate;

    private OffsetDateTime creationDate;

    private String creator;

    private Long id;

    public enum StatusEnum
    {
        OPEN("OPEN"),
        ACCEPTED("ACCEPTED"),
        APPLIED("APPLIED"),
        FAILED_TO_APPLY("FAILED_TO_APPLY");

        private String value;

        StatusEnum(String value)
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

        public static StatusEnum fromValue(String value)
        {
            for (StatusEnum b : StatusEnum.values())
            {
                if (b.value.equals(value))
                {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }
}
