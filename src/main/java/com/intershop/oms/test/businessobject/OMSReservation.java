package com.intershop.oms.test.businessobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.intershop.oms.rest.reservation.v2_0.model.ReservationType;

import lombok.Data;

@Data
public class OMSReservation
{
    /**
     * @deprecated use generated model.ReservationType
     */
    @Deprecated(forRemoval = true, since = "4.4.0")
    enum RESERVATION_TYPE { COMPLETE, PARTLY };

    /**
     * @deprecated use generated model.ReservationState
     */
    @Deprecated(forRemoval = true, since = "4.4.0")
    enum RESERVATION_STATE { reserved, expired };

    /**
     * @deprecated use resvId
     */
    @Deprecated(forRemoval = true, since = "4.4.0")
    int id;

    // request parameters
    ReservationType type = ReservationType.COMPLETE;
    long lifetime;

    // response values
    long resvId;
    Date validUntil;

    Collection<OMSReservationItem> items = new ArrayList<>();

    public OMSReservation() {}

    public OMSReservation(long lifetime, ReservationType reservationType)
    {
        this.lifetime = lifetime;
        type = reservationType;
    }
}
