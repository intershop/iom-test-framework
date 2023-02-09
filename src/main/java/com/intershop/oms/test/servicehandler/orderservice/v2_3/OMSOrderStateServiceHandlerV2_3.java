package com.intershop.oms.test.servicehandler.orderservice.v2_3;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.intershop.oms.rest.order.v2_3.api.OrderStateApi;
import com.intershop.oms.rest.order.v2_3.model.OrderPositionReturned;
import com.intershop.oms.rest.order.v2_3.model.OrderStateCollectionContainer;
import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.rest.shared.ApiResponse;
import com.intershop.oms.rest.transmission.v2_0.model.TransmissionSearchRequest;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderFilter;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderStateCollectionContainer;
import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.RESTServiceHandler;
import com.intershop.oms.test.servicehandler.orderservice.v2_3.mapping.OrderPositionReturnedMixIn;
import com.intershop.oms.test.servicehandler.orderservice.v2_3.mapping.OrderStateCollectionContainerMapper;
import com.intershop.oms.test.servicehandler.transmissionservice.v2_0.mapping.TransmissionCollectionContainerMapper;
import com.intershop.oms.test.servicehandler.transmissionservice.v2_0.mapping.TransmissionFilterMapper;
import com.intershop.oms.test.util.OMSSearchParams;

public class OMSOrderStateServiceHandlerV2_3 extends RESTServiceHandler
                implements com.intershop.oms.test.servicehandler.orderservice.OMSOrderStateServiceHandler
{
    private static final Logger log = LoggerFactory.getLogger(OMSOrderStateServiceHandlerV2_3.class);
    private final OrderStateApi orderStateApi;

    OMSOrderStateServiceHandlerV2_3(ServiceConfiguration serviceConfiguration)
    {
        super(serviceConfiguration, "/rest/order-service", log);
        apiClient.getJSON().getMapper().addMixIn(OrderPositionReturned.class, OrderPositionReturnedMixIn.class);
        orderStateApi = new OrderStateApi(apiClient);
        log.info("OMSOrderStateServiceHandlerV2_3 configured with mapper");
    }

   /**
    * @deprecated, use version with the additional email list as search criteria
    */
    @Override
    @Deprecated
    public OMSOrderStateCollectionContainer getOrderStatesBySortCriterias(Long shopId, List<String> shopOrderNumbers,
                    List<String> shopCustomerNumbers, List<String> productNumbers, List<String> statuses,
                    LocalDate shopOrderCreationDateGte, LocalDate shopOrderCreationDateLte, String orderBy,
                    String sortDirection, Integer offset, Integer limit) throws ApiException
    {

        ApiResponse<OrderStateCollectionContainer> response = orderStateApi.getOrderStatesBySortCriteriasWithHttpInfo(
                        shopId, shopOrderNumbers, shopCustomerNumbers, productNumbers, statuses, null,
                        shopOrderCreationDateGte, shopOrderCreationDateLte, orderBy, sortDirection, offset, limit);

        return OrderStateCollectionContainerMapper.INSTANCE.fromApiOrderStateCollectionContainer(response.getData());

    }

    @Override
    public OMSOrderStateCollectionContainer getOrderStatesBySortCriterias(Long shopId, 
                    OMSOrderFilter filter, OMSSearchParams searchParams) throws ApiException
    {
        if (searchParams == null)
        {
            searchParams = new OMSSearchParams();
        }
        ApiResponse<OrderStateCollectionContainer> response = orderStateApi.getOrderStatesBySortCriteriasWithHttpInfo(
                        shopId, 
                        filter.getShopOrderNumbers(), 
                        filter.getShopCustomerNumbers(), 
                        filter.getProductNumbers(), 
                        filter.getStatuses(), 
                        filter.getEmails(), 
                        filter.getShopOrderCreationDateFrom(), 
                        filter.getShopOrderCreationDateTo(),
                        searchParams.getSortableAttribute(), 
                        searchParams.getSortDirection().name(), //use a String to avoid an API break (TODO: check if required)
                        searchParams.getOffset(), 
                        searchParams.getLimit()
                        );
        return OrderStateCollectionContainerMapper.INSTANCE.fromApiOrderStateCollectionContainer(response.getData());

        
    }
    
    @Override
    protected Collection<Object> unwrapApiClient()
    {
        return Set.of(orderStateApi);
    }

}