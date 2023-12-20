package com.intershop.oms.test.servicehandler.orderstateservice;

import java.time.LocalDate;
import java.util.List;

import com.intershop.oms.rest.shared.ApiException;
import com.intershop.oms.test.businessobject.orderstate.OMSOrderStateCollectionContainer;
import com.intershop.oms.test.servicehandler.OMSServiceHandler;
import com.intershop.oms.test.servicehandler.orderservice.OMSOrderServiceHandler;

/**
 * @deprecated functionality has been merged into {@link OMSOrderServiceHandler}, starting with IOM Order Service v2.3
 */
@Deprecated
public interface OMSOrderStateServiceHandler extends OMSServiceHandler
{
    public OMSOrderStateCollectionContainer getOrderStatesBySortCriterias(Long shopId, List<String> shopOrderNumbers,
                    List<String> shopCustomerNumbers, List<String> productNumbers, List<String> statuses,
                    LocalDate shopOrderCreationDateGte, LocalDate shopOrderCreationDateLte, String orderBy,
                    String sortDirection, Integer offset, Integer limit) throws ApiException;
}