package com.intershop.oms.test.businessobject.order;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSPropertyGroupOwner;
import com.intershop.oms.test.businessobject.OMSShop;
import com.intershop.oms.test.businessobject.address.OMSAddressInvoice;
import com.intershop.oms.test.businessobject.prices.OMSSales;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * Represents the current state of the schedule including basic and runtime configuration.&lt;br&gt; The basic configuration parameters have the suffix &#x60;Configured&#x60;.&lt;br&gt; The runtime configuration parameters have the suffix &#x60;Runtime&#x60;. If not set, the basic configuration will be used.
 */
@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSOrder extends OMSBusinessObject implements OMSPropertyGroupOwner
{
    private static final AtomicInteger counter = new AtomicInteger();
    // my copy of the counter
    protected int threadSafeCounter = counter.incrementAndGet();

    protected String shopOrderNumber;
    protected Long id;
    protected OffsetDateTime shopOrderCreationDate;
    protected OffsetDateTime shopOrderUpdateDate;
    protected Long reservationId;
    protected String costCenter;
    protected String project;
    protected OMSCustomerData customerData;
    protected OMSAddressInvoice invoiceAddress;
    protected OMSSales sales;
    protected OMSPayment payment;
    protected String optimizationRule;
    protected Boolean splitShipmentAllowed;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.PROTECTED)
    protected Map<String, Map<String, String>> propertyGroups = new HashMap<>();
    
    protected List<OMSShippingBucket> shippingBuckets = new ArrayList<>();
    protected OMSShop shop;

    public OMSOrder putAdditionalAttributesItem(String key, Map<String, String> additionalAttributesItem)
    {
        if (propertyGroups == null)
        {
            propertyGroups = new HashMap<>();
        }
        propertyGroups.put(key, additionalAttributesItem);
        return this;
    }

    public OMSOrder addShippingBucketsItem(OMSShippingBucket shippingBucketsItem)
    {
        shippingBuckets.add(shippingBucketsItem);
        return this;
    }
    
    public void setTestCaseId(String testCaseId)
    {
        OffsetDateTime nowOffset = OffsetDateTime.now();
        Date date = new Date();
        String formattedNow = getFormattedShopOrderCreationDate(date);

        setShopOrderNumber(buildShopOrderNo(testCaseId, date));
        if (getCustomerData() != null)
        {
            // any different number will do here. No need to handle race conditions...
            getCustomerData().setShopCustomerNumber("Customer_" + testCaseId + "_" + formattedNow + "_" + new DecimalFormat("0000").format(threadSafeCounter));
        }
        setShopOrderCreationDate(nowOffset);
        setShopOrderUpdateDate(nowOffset);
    }

    public String getFormattedShopOrderCreationDate(Date aShopOrderCreationDate)
    {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(aShopOrderCreationDate);
    }

    public String buildShopOrderNo(String testCaseId, Date aShopOrderCreationDate)
    {
        StringBuilder orderNo = new StringBuilder();
        if (null != testCaseId)
        {
            orderNo.append("Order_");
            orderNo.append(testCaseId);
            orderNo.append("_");
        }
        orderNo.append(getFormattedShopOrderCreationDate(aShopOrderCreationDate));
        orderNo.append("_");
        orderNo.append(new DecimalFormat("0000").format(threadSafeCounter));

        /* must not be longer than 50 characters to fit API */
        return orderNo.length() > 50 ? orderNo.substring(0, 50) : orderNo.toString();
    }

    @Override
    public Map<String, Map<String, String>> getPropertyGroups()
    {
        return propertyGroups;
    }

    public Long getShopId()
    {
        return shop.getId();
    }

    public String getShopName()
    {
        return shop.getName();
    }

    /**
     * @deprecated use threadSafeCounter directly to thread-locally have the next order number
     */
    @Deprecated(forRemoval=true, since="4.1.0")
    public int getNextOrderNumber()
    {
        return threadSafeCounter;
    }
}