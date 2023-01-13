package com.intershop.oms.test.businessobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Accessors(chain = true)
public class OMSReservation
{
    private OMSShop shop;

    // request parameters
    private OMSReservationType type = OMSReservationType.COMPLETE;
    private long lifetime;

    // response values
    private long resvId;
    private Date validUntil;

    private Collection<OMSReservationItem> items = new ArrayList<>();
}
