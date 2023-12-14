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
public class OMSOrderPositionSupplier extends OMSBusinessObject
{
    private String name;
    private OffsetDateTime commissionDate;

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionSupplier name(String name)
    {
        return setName(name);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderPositionSupplier commissionDate(OffsetDateTime commissionDate)
    {
        return setCommissionDate(commissionDate);
    }
}