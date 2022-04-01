package com.intershop.oms.test.servicehandler.transmissionservice.v1_0;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.rest.shared.ApiResponse;
import com.intershop.oms.rest.transmission.v1.api.TransmissionApi;
import com.intershop.oms.rest.transmission.v1.model.ReceiverType;
import com.intershop.oms.rest.transmission.v1.model.SortDirection;
import com.intershop.oms.rest.transmission.v1.model.SortableTransmissionAttribute;
import com.intershop.oms.rest.transmission.v1.model.TransmissionCollectionContainer;
import com.intershop.oms.rest.transmission.v1.model.TransmissionResponseStatus;
import com.intershop.oms.rest.transmission.v1.model.TransmissionStatus;
import com.intershop.oms.rest.transmission.v1.model.TransmissionSubtype;
import com.intershop.oms.rest.transmission.v1.model.TransmissionType;
import com.intershop.oms.test.businessobject.OMSMultiStatusCollectionContainer;
import com.intershop.oms.test.businessobject.OMSShop;
import com.intershop.oms.test.businessobject.OMSSortDirection;
import com.intershop.oms.test.businessobject.transmission.OMSReceiverType;
import com.intershop.oms.test.businessobject.transmission.OMSSortableTransmissionAttribute;
import com.intershop.oms.test.businessobject.transmission.OMSTransmission;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionCollectionContainer;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionResponseStatus;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionStatus;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionSubtype;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionType;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionUpdate;
import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.RESTServiceHandler;
import com.intershop.oms.test.servicehandler.transmissionservice.v1_0.mapping.ReceiverTypeMapper;
import com.intershop.oms.test.servicehandler.transmissionservice.v1_0.mapping.SortDirectionMapper;
import com.intershop.oms.test.servicehandler.transmissionservice.v1_0.mapping.SortableTransmissionAttributeMapper;
import com.intershop.oms.test.servicehandler.transmissionservice.v1_0.mapping.TransmissionCollectionContainerMapper;
import com.intershop.oms.test.servicehandler.transmissionservice.v1_0.mapping.TransmissionResponseStatusMapper;
import com.intershop.oms.test.servicehandler.transmissionservice.v1_0.mapping.TransmissionStatusMapper;
import com.intershop.oms.test.servicehandler.transmissionservice.v1_0.mapping.TransmissionSubtypeMapper;
import com.intershop.oms.test.servicehandler.transmissionservice.v1_0.mapping.TransmissionTypeMapper;

public class OMSTransmissionServiceHandlerV1_0 extends RESTServiceHandler
                implements com.intershop.oms.test.servicehandler.transmissionservice.OMSTransmissionServiceHandler
{
    private static final Logger log = LoggerFactory.getLogger(OMSTransmissionServiceHandlerV1_0.class);
    private final TransmissionApi transmissionApi;

    OMSTransmissionServiceHandlerV1_0(ServiceConfiguration serviceConfiguration)
    {
        super(serviceConfiguration, "/rest", log);
        transmissionApi = new TransmissionApi(apiClient);
    }

    @Override
    public OMSTransmissionCollectionContainer getSampleTransmissions(OMSShop shop,
                    OMSTransmissionType omsTransmissionType) throws ApiException
    {
        OffsetDateTime creationDateGte = OffsetDateTime.now().minusMinutes(5);
        OffsetDateTime creationDateLte = OffsetDateTime.now();
        List<Long> shopIds = new ArrayList<>();
        shopIds.add(shop.getId());
        List<OMSTransmissionType> transmissionTypes = new ArrayList<>();
        transmissionTypes.add(omsTransmissionType);
        return getTransmissions(creationDateGte, creationDateLte, transmissionTypes, shopIds, null);
    }

    @Override
    public Map<OMSTransmissionType, OMSTransmissionCollectionContainer> getSampleTransmissions(OMSShop shop)
                    throws ApiException
    {
        StringBuilder output = new StringBuilder();
        OffsetDateTime creationDateGte = OffsetDateTime.now().minusMinutes(5);
        OffsetDateTime creationDateLte = OffsetDateTime.now();
        Map<OMSTransmissionType, OMSTransmissionCollectionContainer> resultListPerTransmissionType = new LinkedHashMap<>();
        List<Long> shopIds = new ArrayList<>();
        shopIds.add(shop.getId());
        for (TransmissionType transmissionType : TransmissionType.values())
        {
            OMSTransmissionType omsTransmissionType = TransmissionTypeMapper.INSTANCE
                            .fromApiTransmissionType(transmissionType);
            List<OMSTransmissionType> transmissionTypes = new ArrayList<>();
            transmissionTypes.add(omsTransmissionType);
            OMSTransmissionCollectionContainer omsTransmissionCollectionContainer = getTransmissions(creationDateGte,
                            creationDateLte, transmissionTypes, shopIds, null);
            List<OMSTransmission> transmissions = omsTransmissionCollectionContainer.getData();
            output.append("\ngot ").append(transmissions.size()).append(" transmissions of type ")
                            .append(transmissionType).append(".");
            resultListPerTransmissionType.put(omsTransmissionType, omsTransmissionCollectionContainer);
        }
        log.info(output.toString());
        return resultListPerTransmissionType;
    }

    private OMSTransmissionCollectionContainer getTransmissions(OffsetDateTime creationDateGte,
                    OffsetDateTime creationDateLte, List<OMSTransmissionType> transmissionTypes, List<Long> shopIds,
                    List<Long> supplierIds) throws ApiException
    {
        List<OMSTransmissionSubtype> transmissionSubtypes = null;
        List<OMSTransmissionStatus> transmissionStatuses = null;
        List<OMSTransmissionResponseStatus> transmissionResponseStatuses = null;
        List<OMSReceiverType> receiverTypes = null;
        Integer limit = null;
        Integer offset = null;
        OMSSortableTransmissionAttribute orderBy = null;
        OMSSortDirection sortDir = null;

        return getTransmissions(creationDateGte, creationDateLte, transmissionTypes, transmissionSubtypes,
                        transmissionStatuses, transmissionResponseStatuses, receiverTypes, shopIds, supplierIds, limit,
                        offset, orderBy, sortDir);
    }

    private OMSTransmissionCollectionContainer getTransmissions(OffsetDateTime creationDateGte,
                    OffsetDateTime creationDateLte, List<OMSTransmissionType> omsTransmissionTypes,
                    List<OMSTransmissionSubtype> omsTransmissionSubtypes,
                    List<OMSTransmissionStatus> omsTransmissionStatuses,
                    List<OMSTransmissionResponseStatus> omsTransmissionResponseStatuses,
                    List<OMSReceiverType> omsReceiverTypes, List<Long> shopIds, List<Long> supplierIds, Integer limit,
                    Integer offset, OMSSortableTransmissionAttribute omsOrderBy, OMSSortDirection omssortDir)
                    throws ApiException
    {
        List<TransmissionType> transmissionTypes = new ArrayList<>();
        if (null != omsTransmissionTypes)
        {
            TransmissionTypeMapper.INSTANCE.toApiTransmissionTypeList(omsTransmissionTypes, transmissionTypes);
        }

        List<TransmissionSubtype> transmissionSubtypes = new ArrayList<>();
        if (null != omsTransmissionSubtypes)
        {
            TransmissionSubtypeMapper.INSTANCE.toApiTransmissionSubtypeList(omsTransmissionSubtypes,
                            transmissionSubtypes);
        }

        List<TransmissionStatus> transmissionStatuses = new ArrayList<>();
        if (null != omsTransmissionStatuses)
        {
            TransmissionStatusMapper.INSTANCE.toApiTransmissionStatusList(omsTransmissionStatuses,
                            transmissionStatuses);
        }

        List<TransmissionResponseStatus> transmissionResponseStatuses = new ArrayList<>();
        if (null != omsTransmissionResponseStatuses)
        {
            TransmissionResponseStatusMapper.INSTANCE.toApiTransmissionResponseStatusList(
                            omsTransmissionResponseStatuses, transmissionResponseStatuses);
        }

        List<ReceiverType> receiverTypes = new ArrayList<>();
        if (null != omsReceiverTypes)
        {
            ReceiverTypeMapper.INSTANCE.toApiReceiverTypeList(omsReceiverTypes, receiverTypes);
        }

        SortableTransmissionAttribute orderBy = SortableTransmissionAttributeMapper.INSTANCE
                        .toApiSortableTransmissionAttribute(omsOrderBy);
        SortDirection sortDir = SortDirectionMapper.INSTANCE.toApiSortDirection(omssortDir);

        ApiResponse<TransmissionCollectionContainer> response;

        response = transmissionApi.getTransmissionsWithHttpInfo(creationDateGte, creationDateLte, transmissionTypes,
                        transmissionSubtypes, transmissionStatuses, transmissionResponseStatuses, receiverTypes,
                        shopIds, supplierIds, limit, offset, orderBy, sortDir);

        TransmissionCollectionContainer transmissionCollectionContainer = response.getData();
        return TransmissionCollectionContainerMapper.INSTANCE
                        .fromApiTransmissionCollectionContainer(transmissionCollectionContainer);
    }

    @Override
    public OMSTransmission getTransmission(String transmissionId) throws ApiException
    {
        throw new ApiException("getTransmission(String) not implemented in v1_0");
    }

    @Override
    public OMSMultiStatusCollectionContainer updateTransmissions(List<OMSTransmissionUpdate> transmissionUpdates)
                    throws ApiException
    {
        throw new ApiException(
                        "updateTransmissions(List<OMSTransmissionUpdate> transmissionUpdates) not implemented in v1_0");
    }

    @Override
    public int updateTransmissionsGetStatusCode(List<OMSTransmissionUpdate> omsTransmissionUpdates) throws ApiException
    {
        throw new ApiException(
                        "updateTransmissionsGetStatusCode(List<OMSTransmissionUpdate> transmissionUpdates) not implemented in v1_0");
    }

    @Override
    protected Collection<Object> unwrapApiClient()
    {
        return Set.of(transmissionApi);
    }
}
