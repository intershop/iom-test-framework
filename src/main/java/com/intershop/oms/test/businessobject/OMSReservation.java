package com.intershop.oms.test.businessobject;

import java.util.Collection;

public class OMSReservation
{
    static enum RESERVATION_TYPE { COMPLETE, PARTLY };

    static enum RESERVATION_STATE { reserved, expired };

    int lifetime;
//    String type // filled via metaclass as type is optional
    Collection<OMSReservationItem> items;
    
    public Collection<OMSReservationItem> getItems()
    {
        return items;
    }

    public int getLifetime()
    {
        return lifetime;
    }
}
