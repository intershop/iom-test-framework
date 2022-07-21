package com.intershop.oms.test.servicehandler.transmissionservice.v2_0;

import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.rest.transmission.v2_0.api.TransmissionApi;
import com.intershop.oms.rest.transmission.v2_0.api.TransmissionUpdateApi;
import com.intershop.oms.rest.transmission.v2_0.model.TransmissionBulkUpdateRequest;
import com.intershop.oms.rest.transmission.v2_0.model.TransmissionSearchRequest;
import com.intershop.oms.rest.transmission.v2_0.model.TransmissionTypeMappingInner;
import com.intershop.oms.rest.transmission.v2_0.model.TransmissionUpdateRequest;
import com.intershop.oms.test.businessobject.OMSMultiStatusCollectionContainer;
import com.intershop.oms.test.businessobject.transmission.OMSTransmission;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionCollectionContainer;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionFilter;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionType;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionTypeGroup;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionUpdate;
import com.intershop.oms.test.businessobject.transmission.OMSTransmissionUpdateType;
import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.RESTServiceHandler;
import com.intershop.oms.test.servicehandler.transmissionservice.v2_0.mapping.MultiStatusCollectionContainerMapper;
import com.intershop.oms.test.servicehandler.transmissionservice.v2_0.mapping.TransmissionCollectionContainerMapper;
import com.intershop.oms.test.servicehandler.transmissionservice.v2_0.mapping.TransmissionFilterMapper;
import com.intershop.oms.test.servicehandler.transmissionservice.v2_0.mapping.TransmissionMapper;
import com.intershop.oms.test.servicehandler.transmissionservice.v2_0.mapping.TransmissionUpdateMapper;
import com.intershop.oms.test.servicehandler.transmissionservice.v2_0.mapping.TransmissionUpdateTypeMapper;
import com.intershop.oms.test.util.OMSSearchParams;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

public class OMSTransmissionServiceHandlerV2_0 extends RESTServiceHandler
                implements com.intershop.oms.test.servicehandler.transmissionservice.OMSTransmissionServiceHandler
{
    private static final Logger log = LoggerFactory.getLogger(OMSTransmissionServiceHandlerV2_0.class);
    private final TransmissionUpdateApi transmissionUpdateApi;
    private final TransmissionApi transmissionApi;

    OMSTransmissionServiceHandlerV2_0(ServiceConfiguration serviceConfiguration)
    {
        super(serviceConfiguration, "/rest", log);
        transmissionUpdateApi = new TransmissionUpdateApi(apiClient);
        transmissionApi = new TransmissionApi(apiClient);
    }

    @Override
    protected Collection<Object> unwrapApiClient()
    {
        return Set.of(transmissionApi, transmissionUpdateApi);
    }

    @Override
    public OMSTransmissionCollectionContainer searchTransmissions(OMSTransmissionFilter filter, @Nullable OMSSearchParams searchParams) throws ApiException
    {
        if (searchParams == null)
        {
            searchParams = new OMSSearchParams();
        }
        TransmissionSearchRequest request = new TransmissionSearchRequest();
        if (searchParams.getLimit() != null)
        {
            request.setLimit(searchParams.getLimit());
        }
        if (searchParams.getOffset() != null)
        {
            request.setOffset(searchParams.getOffset());
        }
        if (searchParams.getSortDirection() != null)
        {
            request.setSortDirection(TransmissionSearchRequest.SortDirectionEnum.fromValue(searchParams.getSortDirection().toString()));
        }
        if (searchParams.getSortableAttribute() != null)
        {
            request.setSortBy(searchParams.getSortableAttribute());
        }
        request.setTransmissionFilter(TransmissionFilterMapper.INSTANCE.toApiTransmissionFilter(filter));
        return TransmissionCollectionContainerMapper.INSTANCE.fromApiTransmissionCollectionContainer(
                        transmissionApi.searchTransmissions(request));
    }

    @Override
    public OMSTransmission getTransmission(String transmissionId) throws ApiException
    {
        return TransmissionMapper.INSTANCE.fromApiTransmission(transmissionApi.getTransmission(transmissionId));
    }

    @Override
    public OMSMultiStatusCollectionContainer updateTransmissions(List<OMSTransmissionUpdate> transmissionUpdates)
                    throws ApiException
    {
        List<TransmissionUpdateRequest> req = new ArrayList<>();
        TransmissionUpdateMapper.INSTANCE.toApiTransmissionUpdateList(transmissionUpdates, req);
        return MultiStatusCollectionContainerMapper.INSTANCE.fromApiMultiStatusCollectionContainer(
                        transmissionUpdateApi.updateTransmissionsById(req));
    }

    @Override
    public void updateTransmissionsByFilter(OMSTransmissionFilter filter, OMSTransmissionUpdateType updateType)
                    throws ApiException
    {
        TransmissionBulkUpdateRequest req = new TransmissionBulkUpdateRequest();
        req.setTransmissionFilter(TransmissionFilterMapper.INSTANCE.toApiTransmissionFilter(filter));
        req.setUpdateType(TransmissionUpdateTypeMapper.INSTANCE.toApiTransmissionUpdateType(updateType));
        transmissionUpdateApi.updateTransmissionsByFilter(req);
    }

    @Override
    public Map<OMSTransmissionTypeGroup, List<OMSTransmissionType>> getTransmissionTypeGroupMapping()
                    throws ApiException
    {
        List<TransmissionTypeMappingInner> transmissionTypes = transmissionApi.getTransmissionTypes();
        Map<OMSTransmissionTypeGroup, List<OMSTransmissionType>> result = new HashMap<>(transmissionTypes.size());
        for (TransmissionTypeMappingInner transmissionTypeMapping : transmissionTypes)
        {
            if (transmissionTypeMapping.getTransmissionTypes() == null
                            || transmissionTypeMapping.getTransmissionTypes().size() < 1)
            {
                continue;
            }

            List<OMSTransmissionType> mappedTransmissionTypes = transmissionTypeMapping.getTransmissionTypes().stream()
                            .map(tt -> new OMSTransmissionType(tt.getName())).collect(Collectors.toList());
            result.put(new OMSTransmissionTypeGroup(transmissionTypeMapping.getTransmissionTypeGroup()),
                            mappedTransmissionTypes);

        }

        return result;
    }
}