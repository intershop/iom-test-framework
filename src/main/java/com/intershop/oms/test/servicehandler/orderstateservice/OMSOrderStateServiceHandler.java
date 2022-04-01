package com.intershop.oms.test.servicehandler.orderstateservice;

import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderStateCollectionContainer;
import com.intershop.oms.test.servicehandler.OMSServiceHandler;
import com.intershop.oms.test.util.Experimental;

import java.time.LocalDate;
import java.util.List;

@Experimental("Full rework of the handler is still pending")
public interface OMSOrderStateServiceHandler extends OMSServiceHandler
{
    public OMSOrderStateCollectionContainer getOrderStatesBySortCriterias(Long shopId, List<String> shopOrderNumbers,
                    List<String> shopCustomerNumbers, List<String> productNumbers, List<String> statuses,
                    LocalDate shopOrderCreationDateGte, LocalDate shopOrderCreationDateLte, String orderBy,
                    String sortDirection, Integer offset, Integer limit) throws ApiException;

}