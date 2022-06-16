package com.intershop.oms.test.servicehandler.transmissionservice;

import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.test.businessobject.OMSMultiStatusCollectionContainer;
import com.intershop.oms.test.businessobject.transmission.OMSTransmission;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionCollectionContainer;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionFilter;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionType;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionTypeGroup;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionUpdate;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionUpdateType;
import com.intershop.oms.test.servicehandler.OMSServiceHandler;

import java.util.List;
import java.util.Map;

public interface OMSTransmissionServiceHandler extends OMSServiceHandler
{
    OMSTransmissionCollectionContainer searchTransmissions(OMSTransmissionFilter filter) throws ApiException;

    OMSTransmission getTransmission(String transmissionId) throws ApiException;

    //since 1_1 only
    OMSMultiStatusCollectionContainer updateTransmissions(List<OMSTransmissionUpdate> transmissionUpdates)
                    throws ApiException;

    void updateTransmissionsByFilter(OMSTransmissionFilter filter, OMSTransmissionUpdateType updateType)
                    throws ApiException;

    Map<OMSTransmissionTypeGroup, List<OMSTransmissionType>> getTransmissionTypeGroupMapping() throws ApiException;
}
