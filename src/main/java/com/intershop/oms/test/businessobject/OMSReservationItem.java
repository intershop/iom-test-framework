package com.intershop.oms.test.businessobject;

import lombok.Data;

@Data
public class OMSReservationItem
{
    int qty;

    String id;

    OMSReservation.RESERVATION_STATE state;

    public OMSReservationItem() {}

    public OMSReservationItem(String articleRef, int quantity)
    {
        id = articleRef;
        qty = quantity;
    }
}