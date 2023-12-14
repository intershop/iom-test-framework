package com.intershop.oms.test.businessobject.orderstate;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.address.OMSAddressInvoice;
import com.intershop.oms.test.businessobject.order.OMSCustomerData;
import com.intershop.oms.test.businessobject.order.OMSPayment;
import com.intershop.oms.test.businessobject.order.OMSShippingBucket;
import com.intershop.oms.test.businessobject.prices.OMSSales;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSOrderState extends OMSBusinessObject
{
    private String shopOrderNumber;
    private OffsetDateTime shopOrderCreationDate;
    private OffsetDateTime shopOrderUpdateDate;
    private Long reservationId;
    private String costCenter;
    private String project;
    private OMSCustomerData customerData;
    private OMSAddressInvoice invoiceAddress;
    private OMSSales sales;
    private OMSPayment payment;
    private String optimizationRule;
    private Boolean splitShipmentAllowed;
    private Map<String, Map<String, String>> additionalAttributes = null;
    private List<OMSShippingBucket> shippingBuckets = new ArrayList<>();
    private String status;
    private List<OMSOrderNote> notes = null;
    private OMSPaymentState paymentState;
    private List<OMSOrderDocument> documents = null;

    public OMSOrderState putAdditionalAttributesItem(String key, Map<String, String> additionalAttributesItem)
    {
        if (additionalAttributes == null)
        {
            additionalAttributes = new HashMap<>();
        }
        additionalAttributes.put(key, additionalAttributesItem);
        return this;
    }

    public OMSOrderState addShippingBucketsItem(OMSShippingBucket shippingBucketsItem)
    {
        if (shippingBuckets == null)
        {
            shippingBuckets = new ArrayList<>();
        }
        shippingBuckets.add(shippingBucketsItem);
        return this;
    }

    public OMSOrderState addNotesItem(OMSOrderNote notesItem)
    {
        if (notes == null)
        {
            notes = new ArrayList<>();
        }
        notes.add(notesItem);
        return this;
    }

    public OMSOrderState addDocumentsItem(OMSOrderDocument documentsItem)
    {
        if (documents == null)
        {
            documents = new ArrayList<>();
        }
        documents.add(documentsItem);
        return this;
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderState shopOrderNumber(String shopOrderNumber)
    {
        return setShopOrderNumber(shopOrderNumber);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderState shopOrderCreationDate(OffsetDateTime shopOrderCreationDate)
    {
        return setShopOrderCreationDate(shopOrderCreationDate);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderState shopOrderUpdateDate(OffsetDateTime shopOrderUpdateDate)
    {
        return setShopOrderUpdateDate(shopOrderUpdateDate);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderState reservationId(Long reservationId)
    {
        return setReservationId(reservationId);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderState costCenter(String costCenter)
    {
        return setCostCenter(costCenter);
    }
    
    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderState project(String project)
    {
        return setProject(project);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderState customerData(OMSCustomerData customerData)
    {
        return setCustomerData(customerData);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderState invoiceAddress(OMSAddressInvoice invoiceAddress)
    {
        return setInvoiceAddress(invoiceAddress);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderState sales(OMSSales sales)
    {
        return setSales(sales);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderState payment(OMSPayment payment)
    {
        return setPayment(payment);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderState optimizationRule(String optimizationRule)
    {
        return setOptimizationRule(optimizationRule);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderState splitShipmentAllowed(Boolean splitShipmentAllowed)
    {
        return setSplitShipmentAllowed(splitShipmentAllowed);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderState additionalAttributes(Map<String, Map<String, String>> additionalAttributes)
    {
        return setAdditionalAttributes(additionalAttributes);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderState shippingBuckets(List<OMSShippingBucket> shippingBuckets)
    {
        return setShippingBuckets(shippingBuckets);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderState status(String status)
    {
        return setStatus(status);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderState notes(List<OMSOrderNote> notes)
    {
        return setNotes(notes);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderState paymentState(OMSPaymentState paymentState)
    {
        return setPaymentState(paymentState);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderState documents(List<OMSOrderDocument> documents)
    {
        return setDocuments(documents);
    }
}