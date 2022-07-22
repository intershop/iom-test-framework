package com.intershop.oms.test.businessobject.transmission;

import java.util.Arrays;
import java.util.Collection;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The type of the receiver of a transmission.&lt;br&gt; &lt;br&gt;
 * &#x60;OMS&#x60; - The OMS-application itself.&lt;br&gt; &#x60;SHOP&#x60; -
 * The shop is the receiver of the transmission.&lt;br&gt; &#x60;SUPPLIER&#x60;
 * - The supplier is the receiver of the transmission, e.g. of a delivery
 * request.&lt;br&gt; &#x60;CUSTOMER&#x60; - The customer is the receiver of the
 * transmission, e.g. an email to confirm an order.&lt;br&gt;
 * &#x60;PAYMENTPROVIDER&#x60; - A payment provider is the receiver of the
 * transmission, e.g. to note a received payment to.&lt;br&gt;
 * &#x60;FINANCECONTROLLER&#x60; - A finance contoller is the receiver of the
 * transmission, e.g. a debitor management system that receives open amounts.
 */
@EqualsAndHashCode
@ToString
public class OMSReceiverType
{
    public static final OMSReceiverType OMS               = new OMSReceiverType("OMS");
    public static final OMSReceiverType SHOP              = new OMSReceiverType("SHOP");
    public static final OMSReceiverType SUPPLIER          = new OMSReceiverType("SUPPLIER");
    public static final OMSReceiverType CUSTOMER          = new OMSReceiverType("CUSTOMER");
    public static final OMSReceiverType PAYMENTPROVIDER   = new OMSReceiverType("PAYMENTPROVIDER");
    public static final OMSReceiverType FINANCECONTROLLER = new OMSReceiverType("FINANCECONTROLLER");

    private String value;

    public OMSReceiverType(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    public static Collection<OMSReceiverType> getAllValues()
    {
        return Arrays.asList(
            OMS,
            SHOP,
            SUPPLIER,
            CUSTOMER,
            PAYMENTPROVIDER,
            FINANCECONTROLLER
        );
    }
}
