package com.intershop.oms.test.servicehandler.scheduleservice.v1;

import com.intershop.oms.rest.schedule.v1.api.ScheduleConfigsApi;
import com.intershop.oms.rest.schedule.v1.api.ScheduleStatesApi;
import com.intershop.oms.rest.schedule.v1.model.ScheduleCollectionContainer;
import com.intershop.oms.rest.schedule.v1.model.ScheduleState;
import com.intershop.oms.rest.schedule.v1.model.ScheduleStateCollectionContainer;
import com.intershop.oms.rest.schedule.v1.model.ScheduleUpdate;
import com.intershop.oms.rest.schedule.v1.model.SortDirection;
import com.intershop.oms.rest.schedule.v1.model.SortableScheduleAttribute;
import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.rest.shared.ApiResponse;
import com.intershop.oms.test.businessobject.OMSSortDirection;
import com.intershop.oms.test.businessobject.schedule.OMSScheduleCollectionContainer;
import com.intershop.oms.test.businessobject.schedule.OMSScheduleState;
import com.intershop.oms.test.businessobject.schedule.OMSScheduleStateCollectionContainer;
import com.intershop.oms.test.businessobject.schedule.OMSScheduleUpdate;
import com.intershop.oms.test.businessobject.schedule.OMSSortableScheduleAttribute;
import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.RESTServiceHandler;
import com.intershop.oms.test.servicehandler.scheduleservice.v1.mapping.ScheduleCollectionContainerMapper;
import com.intershop.oms.test.servicehandler.scheduleservice.v1.mapping.ScheduleStateCollectionContainerMapper;
import com.intershop.oms.test.servicehandler.scheduleservice.v1.mapping.ScheduleStateMapper;
import com.intershop.oms.test.servicehandler.scheduleservice.v1.mapping.ScheduleUpdateMapper;
import com.intershop.oms.test.servicehandler.scheduleservice.v1.mapping.SortDirectionMapper;
import com.intershop.oms.test.servicehandler.scheduleservice.v1.mapping.SortableScheduleAttributeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class OMSScheduleServiceHandlerV1 extends RESTServiceHandler
                implements com.intershop.oms.test.servicehandler.scheduleservice.OMSScheduleServiceHandler
{
    private static final Logger log = LoggerFactory.getLogger(OMSScheduleServiceHandlerV1.class);
    private final ScheduleConfigsApi scheduleConfigsApi;
    private final ScheduleStatesApi scheduleStatesApi;

    OMSScheduleServiceHandlerV1(ServiceConfiguration serviceConfiguration)
    {
        super(serviceConfiguration, "/rest/schedules", log);
        this.scheduleConfigsApi = new ScheduleConfigsApi(apiClient);
        this.scheduleStatesApi = new ScheduleStatesApi(apiClient);
    }

    @Override
    public OMSScheduleCollectionContainer getSchedules() throws ApiException
    {
        return getSchedules(null, null, null, null, null, null, null, null, null, null);
    }

    @Override
    public OMSScheduleCollectionContainer getSchedules(Boolean active, List<Integer> ids, List<String> jobNames,
                    List<String> keys, Boolean isLocked, Boolean willRetry, OMSSortableScheduleAttribute omsOrderBy,
                    OMSSortDirection omsSortDir, Integer limit, Integer offset) throws ApiException
    {
        SortableScheduleAttribute orderBy = null;
        SortDirection sortDir = null;

        if (null != omsOrderBy)
        {
            orderBy = SortableScheduleAttributeMapper.INSTANCE.toApiSortableScheduleAttribute(omsOrderBy);
        }
        if (null != omsSortDir)
        {
            sortDir = SortDirectionMapper.INSTANCE.toApiSortDirection(omsSortDir);
        }

        ScheduleCollectionContainer scheduleCollectionContainer = scheduleConfigsApi.getSchedules(active, ids, jobNames,
                        keys, isLocked, willRetry, orderBy, sortDir, limit, offset);
        return ScheduleCollectionContainerMapper.INSTANCE.fromApiScheduleCollectionContainer(
                        scheduleCollectionContainer);
    }

    @Override
    public OMSScheduleStateCollectionContainer getSchedulesStates() throws ApiException
    {
        return getSchedulesStates(null, null, null, null, null, null, null, null, null, null);
    }

    @Override
    public OMSScheduleStateCollectionContainer getSchedulesStates(Boolean active, List<Integer> ids,
                    List<String> jobNames, List<String> keys, Boolean isLocked, Boolean stopped,
                    OMSSortableScheduleAttribute omsOrderBy, OMSSortDirection omsSortDir, Integer limit, Integer offset)
                    throws ApiException
    {
        ApiResponse<ScheduleStateCollectionContainer> response;

        SortableScheduleAttribute orderBy = SortableScheduleAttributeMapper.INSTANCE.toApiSortableScheduleAttribute(
                        omsOrderBy);
        SortDirection sortDir = SortDirectionMapper.INSTANCE.toApiSortDirection(omsSortDir);

        response = scheduleStatesApi.getSchedulesStatesWithHttpInfo(active, ids, jobNames, keys, isLocked, stopped,
                        orderBy, sortDir, limit, offset);
        ScheduleStateCollectionContainer scheduleStateCollectionContainer = response.getData();
        return ScheduleStateCollectionContainerMapper.INSTANCE.fromApiScheduleStateCollectionContainer(
                        scheduleStateCollectionContainer);
    }

    @Override
    public OMSScheduleState getScheduleStates(Integer scheduleId) throws ApiException
    {
        ApiResponse<ScheduleState> response;
        response = scheduleStatesApi.getScheduleStatesWithHttpInfo(scheduleId);

        return ScheduleStateMapper.INSTANCE.fromApiScheduleState(response.getData());
    }

    @Override
    public OMSScheduleState updateSchedule(Integer scheduleId, OMSScheduleUpdate omsScheduleUpdate) throws ApiException
    {
        ScheduleUpdate scheduleUpdate = ScheduleUpdateMapper.INSTANCE.toApiScheduleUpdate(omsScheduleUpdate);

        ScheduleState scheduleState = scheduleStatesApi.scheduleUpdate(scheduleId, scheduleUpdate);
        return ScheduleStateMapper.INSTANCE.fromApiScheduleState(scheduleState);
    }

    @Override
    protected Collection<Object> unwrapApiClient()
    {
        return Set.of(scheduleConfigsApi, scheduleStatesApi);
    }
}
