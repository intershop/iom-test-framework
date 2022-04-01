package com.intershop.oms.test.businessobject.transmission;

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
public enum OMSTransmissionStatus
{
    INITIAL("INITIAL"),
    DO_PULL("DO_PULL"),
    PULLED("PULLED"),
    DO_PUSH("DO_PUSH"),
    PUSHED("PUSHED"),
    DO_MANUAL_CHECK("DO_MANUAL_CHECK"),
    CHECKED("CHECKED"),
    DO_CANCEL("DO_CANCEL"),
    CANCELED("CANCELED"),
    TRANSMISSIONED("TRANSMISSIONED"),
    CONFIRMED("CONFIRMED");

    private String value;

    OMSTransmissionStatus(String value)
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

    public static OMSTransmissionStatus fromValue(String text)
    {
        for (OMSTransmissionStatus b : OMSTransmissionStatus.values())
        {
            if (String.valueOf(b.value).equals(text))
            {
                return b;
            }
        }
        return null;
    }
}
