package com.intershop.oms.test.servicehandler.orderstateservice.v1;

import com.intershop.oms.rest.orderstate.v1.api.OrderStateServiceApi;
import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderStateCollectionContainer;
import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.RESTServiceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

class OMSOrderStateServiceHandlerV1 extends RESTServiceHandler
                implements com.intershop.oms.test.servicehandler.orderstateservice.OMSOrderStateServiceHandler
{
    private static final Logger log = LoggerFactory.getLogger(OMSOrderStateServiceHandlerV1.class);
    private final OrderStateServiceApi orderStateApi;

    OMSOrderStateServiceHandlerV1(ServiceConfiguration serviceConfiguration)
    {
        super(serviceConfiguration, "/rest/order-state", log);
        //apiClient.getJSON().getMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        orderStateApi = new OrderStateServiceApi(apiClient);
    }

    @Override
    public OMSOrderStateCollectionContainer getOrderStatesBySortCriterias(Long shopId, List<String> shopOrderNumbers,
                    List<String> shopCustomerNumbers, List<String> productNumbers, List<String> statuses,
                    LocalDate shopOrderCreationDateGte, LocalDate shopOrderCreationDateLte, String orderBy,
                    String sortDirection, Integer offset, Integer limit) throws ApiException
    {
        throw new UnsupportedOperationException("Not implemented for service version v1. Call ");
    }

    @Override
    protected Collection<Object> unwrapApiClient()
    {
        return Set.of(orderStateApi);
    }
}
