package com.intershop.oms.test.businessobject.transmission;

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
public enum OMSReceiverType
{
    OMS("OMS"),
    SHOP("SHOP"),
    SUPPLIER("SUPPLIER"),
    CUSTOMER("CUSTOMER"),
    PAYMENTPROVIDER("PAYMENTPROVIDER"),
    FINANCECONTROLLER("FINANCECONTROLLER");

    private String value;

    OMSReceiverType(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }

    public static OMSReceiverType fromValue(String text)
    {
        for (OMSReceiverType b : OMSReceiverType.values())
        {
            if (String.valueOf(b.value).equals(text))
            {
                return b;
            }
        }
        return null;
    }
}
