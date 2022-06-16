package com.intershop.oms.test.businessobject.transmission;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The processing status of the transmission.&lt;br&gt; &lt;br&gt;
 * &#x60;INITIAL&#x60; - Transmission is initially stored.&lt;br&gt;
 * &#x60;DO_PULL&#x60; - Transmission will be pulled.&lt;br&gt;
 * &#x60;PULLED&#x60; - Transmission was received (pulled) from the
 * sender.&lt;br&gt; &#x60;DO_PUSH&#x60; - Transmission can be sent (pushed) to
 * the receiver.&lt;br&gt; &#x60;PUSHED&#x60; - Transmission was sent (pushed)
 * to the receiver.&lt;br&gt; &#x60;DO_MANUAL_CHECK&#x60; - The processing of
 * transmission was canceled because of errors. The transmission has to be
 * checked manually.&lt;br&gt; &#x60;CHECKED&#x60; - The transmission was
 * checked manually and is ready to process.&lt;br&gt; &#x60;DO_CANCEL&#x60; -
 * The transmission will be canceled.&lt;br&gt; &#x60;CANCELED&#x60; -
 * Transmission can be canceled because processing is not required any
 * longer.&lt;br&gt; &#x60;TRANSMISSIONED&#x60; - Transmission was
 * submitted.&lt;br&gt; &#x60;CONFIRMED&#x60; - Transmission was confirmed from
 * the receiver.
 */
@EqualsAndHashCode
@ToString
public class OMSTransmissionStatus
{
    public static final OMSTransmissionStatus INITIAL = new OMSTransmissionStatus("INITIAL");
    public static final OMSTransmissionStatus DO_PULL = new OMSTransmissionStatus("DO_PULL");
    public static final OMSTransmissionStatus PULLED = new OMSTransmissionStatus("PULLED");
    public static final OMSTransmissionStatus DO_PUSH = new OMSTransmissionStatus("DO_PUSH");
    public static final OMSTransmissionStatus PUSHED = new OMSTransmissionStatus("PUSHED");
    public static final OMSTransmissionStatus DO_MANUAL_CHECK = new OMSTransmissionStatus("DO_MANUAL_CHECK");
    public static final OMSTransmissionStatus CHECKED = new OMSTransmissionStatus("CHECKED");
    public static final OMSTransmissionStatus DO_CANCEL = new OMSTransmissionStatus("DO_CANCEL");
    public static final OMSTransmissionStatus CANCELED = new OMSTransmissionStatus("CANCELED");
    public static final OMSTransmissionStatus TRANSMISSIONED = new OMSTransmissionStatus("TRANSMISSIONED");
    public static final OMSTransmissionStatus CONFIRMED = new OMSTransmissionStatus("CONFIRMED");

    private String value;

    public OMSTransmissionStatus(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

}
