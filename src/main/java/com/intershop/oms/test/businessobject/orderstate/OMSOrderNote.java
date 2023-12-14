package com.intershop.oms.test.businessobject.orderstate;

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
public class OMSOrderNote extends OMSBusinessObject
{
    private String message;
    private String type;
    private OffsetDateTime creationDate;

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderNote message(String message)
    {
        return setMessage(message);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderNote type(String type)
    {
        return setType(type);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderNote creationDate(OffsetDateTime creationDate)
    {
        return setCreationDate(creationDate);
    }
}
