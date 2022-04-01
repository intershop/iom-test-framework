package com.intershop.oms.test.servicehandler.transmissionservice;

import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.test.businessobject.OMSMultiStatusCollectionContainer;
import com.intershop.oms.test.businessobject.OMSShop;
import com.intershop.oms.test.businessobject.transmission.OMSTransmission;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionCollectionContainer;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionType;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionUpdate;
import com.intershop.oms.test.servicehandler.OMSServiceHandler;
import com.intershop.oms.test.util.Experimental;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Experimental("Full rework of the handler is still pending")
public interface OMSTransmissionServiceHandler extends OMSServiceHandler
{
    public OMSTransmissionCollectionContainer getSampleTransmissions(OMSShop shop, OMSTransmissionType transmissionType)
                    throws ApiException;

    public Map<OMSTransmissionType, OMSTransmissionCollectionContainer> getSampleTransmissions(OMSShop shop)
                    throws ApiException;

    //since 1_1 only
    public OMSTransmission getTransmission(String transmissionId) throws ApiException;

    //since 1_1 only
    public OMSMultiStatusCollectionContainer updateTransmissions(List<OMSTransmissionUpdate> transmissionUpdates)
                    throws ApiException;

    //since 1_1 only, just return the code, mapping doesn't work in specs, so this way to check the HttpStatusCode
    public int updateTransmissionsGetStatusCode(List<OMSTransmissionUpdate> omsTransmissionUpdates) throws ApiException;

    /**
     * filters the given list of transmission as to your filter specification --- currently only shopOrderNo can be filtered
     *
     * @param resultListPerTransmissionType
     * @param filter
     * @param shopOrderNo
     * @return
     */
    public default Map<OMSTransmissionType, List<OMSTransmission>> filterTransmissions(
                    Map<OMSTransmissionType, OMSTransmissionCollectionContainer> resultListPerTransmissionType,
                    TRANSMISSION_FILTERS filter, String shopOrderNo)
    {
        Logger log = LoggerFactory.getLogger(OMSTransmissionServiceHandler.class);

        Map<OMSTransmissionType, List<OMSTransmission>> resultListPerTransmissionTypeFiltered = new LinkedHashMap<>();

        for (Map.Entry<OMSTransmissionType, OMSTransmissionCollectionContainer> resultListForOneTransmissionType : resultListPerTransmissionType.entrySet())
        {
            List<OMSTransmission> transmissions = resultListPerTransmissionTypeFiltered.get(
                            resultListForOneTransmissionType.getKey());

            if (null == transmissions)
            {
                transmissions = new ArrayList<OMSTransmission>();
                resultListPerTransmissionTypeFiltered.put(resultListForOneTransmissionType.getKey(), transmissions);
            }
            log.info("shopOrderNo:  " + shopOrderNo);

            for (OMSTransmission transmission : resultListForOneTransmissionType.getValue().getData())
            {

                switch(filter)
                {
                    case SHOP_ORDER_NUMBER:
                        log.info("transmission: " + transmission.getShopOrderNumber());
                        if (transmission.getShopOrderNumber().equals(shopOrderNo))
                        {
                            log.info("transmission: " + transmission.getShopOrderNumber() + " in shopOrderNo:  "
                                            + shopOrderNo);
                            transmissions.add(transmission);
                        }
                        break;
                    default:
                        throw new RuntimeException("Unknown filter: " + filter);
                }
            }
        }
        return resultListPerTransmissionTypeFiltered;
    }

    /**
     * @return the first transmission of the list
     */
    public default OMSTransmission getFirst(
                    Map<OMSTransmissionType, OMSTransmissionCollectionContainer> transmissionsMap)
    {
        for (OMSTransmissionCollectionContainer container : transmissionsMap.values())
        {
            for (OMSTransmission omsTransmission : container.getData())
            {
                return omsTransmission;
            }
        }
        return null;
    }

    public enum TRANSMISSION_FILTERS
    {
        SHOP_ORDER_NUMBER
    }
}
