package com.intershop.oms.test.businessobject.order;

import java.time.OffsetDateTime;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
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

    public OMSChangeRequest changeRequest(OMSOrder changeRequest)
    {
        this.changeRequest = changeRequest;
        return this;
    }

    public OMSChangeRequest changeRequestId(String changeRequestId)
    {
        this.changeRequestId = changeRequestId;
        return this;
    }

    public OMSChangeRequest status(String status)
    {
        this.status = status;
        return this;
    }

    public OMSChangeRequest updateDate(OffsetDateTime updateDate)
    {
        this.updateDate = updateDate;
        return this;
    }

    public OMSChangeRequest creationDate(OffsetDateTime creationDate)
    {
        this.creationDate = creationDate;
        return this;
    }

    public OMSChangeRequest creator(String creator)
    {
        this.creator = creator;
        return this;
    }

    public OMSChangeRequest id(Long id)
    {
        this.id = id;
        return this;
    }

}
