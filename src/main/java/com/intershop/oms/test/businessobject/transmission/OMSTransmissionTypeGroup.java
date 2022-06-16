package com.intershop.oms.test.businessobject.transmission;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The type of the transmission.&lt;br&gt; &lt;br&gt; &#x60;ORDERTRANSMISSION&#x60; - Transmissions that are used for order processing messages, e.g. to announce an order entry. Subtypes of this type start with &#x60;ORDER_&#x60;.&lt;br&gt;  &#x60;DISPATCHTRANSMISSION&#x60; - Transmissions that are used for dispatch messages, e.g. a delivery that was made. Subtypes of this type start with &#x60;DISPATCH_&#x60;.&lt;br&gt; &#x60;RETURNTRANSMISSION&#x60; - Transmissions that are used for return messages, e.g. a received return. Subtypes of this type start with &#x60;RETURN_&#x60;.&lt;br&gt; &#x60;PAYMENTTRANSMISSION&#x60; - Transmissions that are used for payment notification messages, e.g. a received payment. Subtypes of this type start with &#x60;PAYMENT_&#x60;.&lt;br&gt; &#x60;MAILTRANSMISSION&#x60; - Transmissions that are used for emails, e.g. to send a delivery email to the customer. Subtypes of this type start with &#x60;MAIL_&#x60;.&lt;br&gt; &#x60;RESPONSETRANSMISSION&#x60; - Transmissions that are used for response messages, e.g. to respond to a delivery request. Subtypes of this type start with &#x60;RESPONSE_&#x60;.&lt;br&gt; &#x60;INVOICETRANSMISSION&#x60; - Transmissions that are used for invoice processing messages. Subtypes of this type start with &#x60;INVOICE_&#x60;.&lt;br&gt; &#x60;DOCUMENTTRANSMISSION&#x60; - Transmissions that are used for document processing messages. Subtypes of this type start with &#x60;DOCUMENT_&#x60;.&lt;br&gt;    &#x60;RETURNANNOUNCEMENTTRANSMISSION&#x60; - Transmissions that are used for return announcements messages. Subtypes of this type start with &#x60;RETURNANNOUNCEMENT_&#x60;.&lt;br&gt; &lt;br&gt; For any type use &#x60;OTHERS&#x60; for subtypes that are not listed, e.g. custom ones.&lt;br&gt;
 */
@EqualsAndHashCode
@ToString
public class OMSTransmissionTypeGroup
{
    public static final OMSTransmissionTypeGroup ORDERTRANSMISSION = new OMSTransmissionTypeGroup("ORDERTRANSMISSION");
    public static final OMSTransmissionTypeGroup DISPATCHTRANSMISSION = new OMSTransmissionTypeGroup("DISPATCHTRANSMISSION");
    public static final OMSTransmissionTypeGroup RETURNTRANSMISSION = new OMSTransmissionTypeGroup("RETURNTRANSMISSION");
    public static final OMSTransmissionTypeGroup PAYMENTTRANSMISSION = new OMSTransmissionTypeGroup("PAYMENTTRANSMISSION");
    public static final OMSTransmissionTypeGroup MAILTRANSMISSION = new OMSTransmissionTypeGroup("MAILTRANSMISSION");
    public static final OMSTransmissionTypeGroup RESPONSETRANSMISSION = new OMSTransmissionTypeGroup("RESPONSETRANSMISSION");
    public static final OMSTransmissionTypeGroup INVOICETRANSMISSION = new OMSTransmissionTypeGroup("INVOICETRANSMISSION");
    public static final OMSTransmissionTypeGroup DOCUMENTTRANSMISSION = new OMSTransmissionTypeGroup("DOCUMENTTRANSMISSION");
    public static final OMSTransmissionTypeGroup RETURNANNOUNCEMENTTRANSMISSION = new OMSTransmissionTypeGroup("RETURNANNOUNCEMENTTRANSMISSION");

    private String transmissionTypeGroupName;

    public OMSTransmissionTypeGroup(String transmissionTypeGroupName)
    {
        this.transmissionTypeGroupName = transmissionTypeGroupName;
    }

    public String getTransmissionTypeGroupName()
    {
        return transmissionTypeGroupName;
    }
}
