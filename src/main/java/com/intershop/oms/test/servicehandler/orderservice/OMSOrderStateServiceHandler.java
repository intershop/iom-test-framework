package com.intershop.oms.test.servicehandler.orderservice;

import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderStateCollectionContainer;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderFilter;
import com.intershop.oms.test.servicehandler.OMSServiceHandler;
import com.intershop.oms.test.util.Experimental;
import com.intershop.oms.test.util.OMSSearchParams;

import java.time.LocalDate;
import java.util.List;
import javax.annotation.Nullable;

@Experimental("Full rework of the handler is still pending")
public interface OMSOrderStateServiceHandler extends OMSServiceHandler
{


    public OMSOrderStateCollectionContainer getOrderStatesBySortCriterias(Long shopId, List<String> shopOrderNumbers,
                    List<String> shopCustomerNumbers, List<String> productNumbers, List<String> statuses,
                    LocalDate shopOrderCreationDateGte, LocalDate shopOrderCreationDateLte, String orderBy,
                    String sortDirection, Integer offset, Integer limit) throws ApiException;

    /**
     * 
     * @param filter
     * @param searchParams
     * @return
     * @throws ApiException
     */
    public OMSOrderStateCollectionContainer getOrderStatesBySortCriterias(Long shopId, OMSOrderFilter filter, @Nullable OMSSearchParams searchParams) throws ApiException;
    

}