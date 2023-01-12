package com.intershop.oms.test.servicehandler.inventoryservice;

import java.util.List;

import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.test.businessobject.OMSInventory;
import com.intershop.oms.test.businessobject.OMSReservation;
import com.intershop.oms.test.servicehandler.OMSServiceHandler;

public interface OMSInventoryServiceHandler extends OMSServiceHandler
{
    /**
     * creates a reservation
     *
     * @return the newly created reservation extended by the reservationId
     * @throws ApiException
     */
    OMSReservation createReservation(Long shopId, OMSReservation omsReservation) throws ApiException;

    /**
     * retrieve information for a given reservation
     *
     * @param reservationId
     * @return a reservation with updated information about lifetime and items a.s. for the requested reservation
     * @throws ApiException
     */
    OMSReservation getReservation(Long reservationId) throws ApiException;

    /**
     * send an update for a given reservation (i.e. longer reservation)
     *
     * @param omsReservation
     * @return the updated reservation
     * @throws ApiException
     */
    OMSReservation updateReservation(OMSReservation omsReservation) throws ApiException;

    /**
     * release the reservation
     *
     * currently doesn't work (see #44) since the empty response 204 ends in an NPE
     *
     * @param reservationId
     * @throws ApiException
     */
    void deleteReservation(Long reservationId) throws ApiException;

    /**
     * retrieve information about the availability of products
     *
     * @return information about the availability of the given products
     * @throws ApiException
     */
    default List<OMSInventory> getInventories(Long shopId, List<String> productIds) throws ApiException
    {
        return getInventories(shopId, productIds, null);
    }

    /**
     * retrieve information about the availability of products
     *
     * @param shopId
     * @param productIds - for the given shop
     * @param reservationId - optional
     *
     * @return information about the availability of the given products
     * @throws ApiException
     */
    List<OMSInventory> getInventories(Long shopId, List<String> productIds, Long reservationId) throws ApiException;

}
