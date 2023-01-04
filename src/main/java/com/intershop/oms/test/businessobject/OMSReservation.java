package com.intershop.oms.test.businessobject;

import java.util.Collection;

import lombok.Data;

@Data
public class OMSReservation
{
    static public enum RESERVATION_TYPE { COMPLETE, PARTLY };

    static public enum RESERVATION_STATE { reserved, expired };

    long id;

    long lifetime;

    Collection<OMSReservationItem> items;

    public Collection<OMSReservationItem> getItems()
    {
        return items;
    }

    public long getLifetime()
    {
        return lifetime;
    }

    public String getReservationType()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
