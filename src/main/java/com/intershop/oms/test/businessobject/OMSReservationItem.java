package com.intershop.oms.test.businessobject;

import com.intershop.oms.rest.reservation.v2_0.model.ReservationState;

import lombok.Data;

@Data
public class OMSReservationItem
{
    int qty;

    String id;

    ReservationState state;

    public OMSReservationItem() {}

    public OMSReservationItem(String articleRef, int quantity)
    {
        id = articleRef;
        qty = quantity;
    }
}