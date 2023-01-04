package com.intershop.oms.test.servicehandler.reservationservice;

import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.test.businessobject.OMSReservation;
import com.intershop.oms.test.servicehandler.OMSServiceHandler;

public interface OMSReservationServiceHandler extends OMSServiceHandler
{
    /**
     * creates a reservation
     *
     * @return the newly created reservation extended by the orderId
     * @throws ApiException
     */
    OMSReservation createReservation(Long shopId, OMSReservation omsReservation) throws ApiException;
}
