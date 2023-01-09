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
     * @return the newly created reservation extended by the orderId
     * @throws ApiException
     */
    OMSReservation createReservation(Long shopId, OMSReservation omsReservation) throws ApiException;

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
