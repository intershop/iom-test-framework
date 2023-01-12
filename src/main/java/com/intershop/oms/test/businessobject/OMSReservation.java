package com.intershop.oms.test.businessobject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import lombok.Data;

@Data
public class OMSReservation
{
    public enum RESERVATION_TYPE { COMPLETE, PARTLY }

    public enum RESERVATION_STATE { RESERVED, EXPIRED }

    /**
     * @deprecated use resvId
     */
    @Deprecated(forRemoval = true, since = "4.4.0")
    int id;

    // request parameters
    RESERVATION_TYPE type = RESERVATION_TYPE.COMPLETE;
    long lifetime;

    // response values
    long resvId;
    Date validUntil;

    Collection<OMSReservationItem> items = new ArrayList<>();

    public OMSReservation() {}

    public OMSReservation(long lifetime, RESERVATION_TYPE reservationType)
    {
        this.lifetime = lifetime;
        type = reservationType;
    }
}
