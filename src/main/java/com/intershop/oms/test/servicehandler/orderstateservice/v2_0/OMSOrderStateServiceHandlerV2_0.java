package com.intershop.oms.test.servicehandler.orderstateservice.v2_0;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.intershop.oms.rest.order.v2_0.api.OrderStateApi;
import com.intershop.oms.rest.order.v2_0.model.OrderStateCollectionContainer;
import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.rest.shared.ApiResponse;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderStateCollectionContainer;
import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.RESTServiceHandler;
import com.intershop.oms.test.servicehandler.orderstateservice.v2_0.mapping.OrderStateCollectionContainerMapper;

public class OMSOrderStateServiceHandlerV2_0 extends RESTServiceHandler
                implements com.intershop.oms.test.servicehandler.orderstateservice.OMSOrderStateServiceHandler
{
    private static final Logger log = LoggerFactory.getLogger(OMSOrderStateServiceHandlerV2_0.class);
    private final OrderStateApi orderStateApi;

    OMSOrderStateServiceHandlerV2_0(ServiceConfiguration serviceConfiguration)
    {
        super(serviceConfiguration, "/rest/order-service", log);
        orderStateApi = new OrderStateApi(apiClient);
    }

    @Override
    public OMSOrderStateCollectionContainer getOrderStatesBySortCriterias(Long shopId, List<String> shopOrderNumbers,
                    List<String> shopCustomerNumbers, List<String> productNumbers, List<String> statuses,
                    LocalDate shopOrderCreationDateGte, LocalDate shopOrderCreationDateLte, String orderBy,
                    String sortDirection, Integer offset, Integer limit) throws ApiException
    {

        ApiResponse<OrderStateCollectionContainer> response = orderStateApi.getOrderStatesBySortCriteriasWithHttpInfo(
                        shopId, shopOrderNumbers, shopCustomerNumbers, productNumbers, statuses,
                        shopOrderCreationDateGte, shopOrderCreationDateLte, orderBy, sortDirection, offset, limit);

        return OrderStateCollectionContainerMapper.INSTANCE.fromApiOrderStateCollectionContainer(response.getData());

    }

    @Override
    protected Collection<Object> unwrapApiClient()
    {
        return Set.of(orderStateApi);
    }
}