package com.intershop.oms.test.businessobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import lombok.Data;

@Data
public class OMSReservation
{
    /**
     * @deprecated use generated OMSReservationType
     */
    @Deprecated(forRemoval = true, since = "4.5.0")
    enum RESERVATION_TYPE { COMPLETE, PARTLY };

    /**
     * @deprecated use generated OMSReservationState
     */
    @Deprecated(forRemoval = true, since = "4.5.0")
    enum RESERVATION_STATE { reserved, expired };

    /**
     * @deprecated use resvId
     */
    @Deprecated(forRemoval = true, since = "4.5.0")
    int id;

    // request parameters
    OMSReservationType type = OMSReservationType.COMPLETE;
    long lifetime;

    // response values
    long resvId;
    Date validUntil;

    Collection<OMSReservationItem> items = new ArrayList<>();

    public OMSReservation() {}

    public OMSReservation(long lifetime, OMSReservationType reservationType)
    {
        this.lifetime = lifetime;
        type = reservationType;
    }
}
