package com.intershop.oms.test.businessobject.transmission;

import java.util.Arrays;
import java.util.Collection;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The attribute on which should be sorted.&lt;br&gt; &lt;br&gt;
 *
 * &#x60;id&#x60; - Sort by the identifier in scope of it&#x27;s type.&lt;br&gt;
 * &#x60;status&#x60; - Sort by the processing status.&lt;br&gt;
 * &#x60;responseStatus&#x60; - Sort by the response status.&lt;br&gt;
 * &#x60;creationDate&#x60; - Sort by the creation date.&lt;br&gt;
 * &#x60;modificationDate&#x60; - Sort by the modification date.&lt;br&gt;
 * &#x60;shopId&#x60; - Sort by the id of the shop.&lt;br&gt;
 * &#x60;shopName&#x60; - Sort by the name of the shop.&lt;br&gt;
 * &#x60;supplierId&#x60; - Sort by the id of the supplier.&lt;br&gt;
 * &#x60;supplierName&#x60; - Sort by the name of the supplier.&lt;br&gt;
 * &#x60;receiver&#x60; - Sort by the receiver of the transmission.&lt;br&gt;
 * &#x60;retryCount&#x60; - Sort by the number of sending attempts.&lt;br&gt;
 * &#x60;retryDate&#x60; - Sort by the date of the last attempt to send.&lt;br&gt;
 * &#x60;nextRetryDate&#x60; - Sort by the next date to send.&lt;br&gt;
 * &#x60;invoiceNumber&#x60; - Sort by the id of an invoice.&lt;br&gt;
 */
@EqualsAndHashCode
@ToString
public class OMSSortableTransmissionAttribute
{
    public static final OMSSortableTransmissionAttribute ID                  = new OMSSortableTransmissionAttribute("id");
    public static final OMSSortableTransmissionAttribute TRANSMISSIONTYPE    = new OMSSortableTransmissionAttribute("transmissionType");
    public static final OMSSortableTransmissionAttribute STATUS              = new OMSSortableTransmissionAttribute("status");
    public static final OMSSortableTransmissionAttribute RESPONSESTATUS      = new OMSSortableTransmissionAttribute("responseStatus");
    public static final OMSSortableTransmissionAttribute CREATIONDATE        = new OMSSortableTransmissionAttribute("creationDate");
    public static final OMSSortableTransmissionAttribute MODIFICATIONDATE    = new OMSSortableTransmissionAttribute("modificationDate");
    public static final OMSSortableTransmissionAttribute SHOPID              = new OMSSortableTransmissionAttribute("shopId");
    public static final OMSSortableTransmissionAttribute SHOPNAME            = new OMSSortableTransmissionAttribute("shopName");
    public static final OMSSortableTransmissionAttribute SUPPLIERID          = new OMSSortableTransmissionAttribute("supplierId");
    public static final OMSSortableTransmissionAttribute SUPPLIERNAME        = new OMSSortableTransmissionAttribute("supplierName");
    public static final OMSSortableTransmissionAttribute RECEIVERTYPE        = new OMSSortableTransmissionAttribute("receiverType");
    public static final OMSSortableTransmissionAttribute RETRYCOUNT          = new OMSSortableTransmissionAttribute("retryCount");
    public static final OMSSortableTransmissionAttribute RETRYDATE           = new OMSSortableTransmissionAttribute("retryDate");
    public static final OMSSortableTransmissionAttribute NEXTRETRYDATE       = new OMSSortableTransmissionAttribute("nextRetryDate");
    public static final OMSSortableTransmissionAttribute INVOICENUMBER       = new OMSSortableTransmissionAttribute("invoiceNumber");

    private String value;

    public OMSSortableTransmissionAttribute(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    public static Collection<OMSSortableTransmissionAttribute> getAllValues()
    {
        return Arrays.asList(
            ID,
            TRANSMISSIONTYPE,
            STATUS,
            RESPONSESTATUS,
            CREATIONDATE,
            MODIFICATIONDATE,
            SHOPID,
            SHOPNAME,
            SUPPLIERID,
            SUPPLIERNAME,
            RECEIVERTYPE,
            RETRYCOUNT,
            RETRYDATE,
            NEXTRETRYDATE,
            INVOICENUMBER,
            ERRORTEXT
        );
    }
}
