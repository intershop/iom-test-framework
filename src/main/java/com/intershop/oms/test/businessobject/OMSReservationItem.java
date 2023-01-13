package com.intershop.oms.test.businessobject;

import lombok.Data;

@Data
public class OMSReservationItem
{
    int qty;

    String id;

    OMSReservationState state;

    public OMSReservationItem() {}

    public OMSReservationItem(String articleRef, int quantity)
    {
        id = articleRef;
        qty = quantity;
    }
}