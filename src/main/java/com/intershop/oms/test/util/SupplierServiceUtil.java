package com.intershop.oms.test.util;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.intershop.oms.test.businessobject.OMSShop;
import com.intershop.oms.test.businessobject.OMSSupplier;
import com.intershop.oms.test.businessobject.communication.OMSCarrier;
import com.intershop.oms.test.businessobject.communication.OMSDispatch;
import com.intershop.oms.test.businessobject.communication.OMSDispatchPosition;
import com.intershop.oms.test.businessobject.communication.OMSOrderResponse;
import com.intershop.oms.test.businessobject.communication.OMSOrderResponsePosition;
import com.intershop.oms.test.businessobject.communication.OMSReturn;
import com.intershop.oms.test.businessobject.communication.OMSReturnPosition;
import com.intershop.oms.test.businessobject.order.OMSOrder;
import com.intershop.oms.test.businessobject.rma.OMSReturnRequest;
import com.intershop.oms.test.businessobject.rma.OMSReturnRequest.TypeEnum;
import com.intershop.oms.test.businessobject.rma.OMSReturnRequestPosition;
import com.intershop.oms.test.servicehandler.ServiceHandlerFactory;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;

public class SupplierServiceUtil
{
    public static List<OMSDispatch> prepareFullDispatch(OMSOrder order, boolean useSupplierData)
    {
        OMSDbHandler dbHandler = ServiceHandlerFactory.getDbHandler();
        Map<OMSSupplier, Collection<OMSDispatchPosition>> supplierDispatchPositions = dbHandler
                        .getDispatchPositionsForOrder(order, useSupplierData);

        return supplierDispatchPositions
                        .entrySet().stream().map(entry -> prepareDispatch(order.getShop(), entry.getKey(),
                                        order.getShopOrderNumber(), entry.getValue(), null))
                        .toList();
    }

    public static List<OMSReturn> prepareFullReturn(OMSOrder order, boolean useSupplierData)
    {
        OMSDbHandler dbHandler = ServiceHandlerFactory.getDbHandler();
        Map<OMSSupplier, Collection<OMSReturnPosition>> supplierReturnPositions = dbHandler
                        .getReturnPositionsForOrder(order, useSupplierData);

        return supplierReturnPositions
                        .entrySet().stream().map(entry -> prepareReturn(order.getShop(), entry.getKey(),
                                        order.getShopOrderNumber(), entry.getValue(), null))
                        .toList();
    }

    public static List<OMSOrderResponse> prepareFullResponse(OMSOrder order, boolean useSupplierData)
    {
        OMSDbHandler dbHandler = ServiceHandlerFactory.getDbHandler();
        Map<OMSSupplier, Collection<OMSOrderResponsePosition>> orderResponsePositionsForOrder = dbHandler
                        .getOrderResponsePositionsForOrder(order, useSupplierData);

        return orderResponsePositionsForOrder
                        .entrySet().stream().map(entry -> prepareResponse(order.getShop(), entry.getKey(),
                                        order.getShopOrderNumber(), entry.getValue(), null))
                        .toList();
    }

    public static OMSDispatch prepareDispatch(OMSShop shop, OMSSupplier supplier, String shopOrderNo,
                    Collection<OMSDispatchPosition> dispatchPositions, String messageId)
    {
        OMSDispatch dispatch = new OMSDispatch().setCarrier(new OMSCarrier()).setDispatchDate(OffsetDateTime.now())
                        .setShop(shop).setSupplier(supplier).setShopOrderNumber(shopOrderNo)
                        .setMessageId(messageId == null ? createMessageId() : messageId);
        dispatch.getPositions().addAll(dispatchPositions);
        dispatch.getPositions().forEach(pos -> pos.addProperty("DEMO-GROUP", "Test Key", "Test Value"));
        return dispatch;
    }

    public static OMSReturn prepareReturn(OMSShop shop, OMSSupplier supplier, String shopOrderNo,
                    Collection<OMSReturnPosition> returnPositions, String messageId)
    {
        OMSReturn omsReturn = new OMSReturn().setEntryDate(OffsetDateTime.now()).setShop(shop).setSupplier(supplier)
                        .setShopOrderNumber(shopOrderNo)
                        .setMessageId(messageId == null ? createMessageId() : messageId);
        omsReturn.getPositions().addAll(returnPositions);
        omsReturn.getPositions().forEach(pos -> pos.addProperty("DEMO-GROUP", "Test Key", "Test Value"));
        return omsReturn;
    }

    public static OMSOrderResponse prepareResponse(OMSShop shop, OMSSupplier supplier, String shopOrderNo,
                    Collection<OMSOrderResponsePosition> omsResponsePositions, String messageId)
    {
        OMSOrderResponse omsResponse = new OMSOrderResponse().setEntryDate(OffsetDateTime.now()).setShop(shop)
                        .setSupplier(supplier).setShopOrderNumber(shopOrderNo)
                        .setMessageId(messageId == null ? createMessageId() : messageId).setSupplierStateCode("ACC");
        omsResponse.getPositions().addAll(omsResponsePositions);
        omsResponse.getPositions().forEach(pos -> pos.addProperty("DEMO-GROUP", "Test Key", "Test Value"));
        return omsResponse;
    }

    /**
     * creates a full RMA request of type RETURN for the given dispatches /
     * dispatch positions.
     */
    public static OMSReturnRequest prepareFullReturnRequest(OMSOrder order, List<OMSDispatch> dispatches)
    {
        // map dispatch positions
        List<OMSReturnRequestPosition> rReqPos = dispatches.stream().flatMap(disp -> disp.getPositions().stream())
                        .map(OMSReturnRequestPosition::fromDispatchPosition).collect(Collectors.toList());

        return new OMSReturnRequest().setShopName(order.getShopName()).setShopOrderNumber(order.getShopOrderNumber())
                        .setCreationDate(new Date(System.currentTimeMillis())).setPositions(rReqPos)
                        .setType(TypeEnum.RETURN);
    }

    public static String createMessageId()
    {
        return "TestMsgID_" + new SimpleDateFormat("yy-MM-dd-HH:mm:ss.SSS").format(Calendar.getInstance().getTime())
                        + "_" + UUID.randomUUID();
    }
}
