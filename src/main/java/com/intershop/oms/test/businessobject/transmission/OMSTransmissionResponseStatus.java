package com.intershop.oms.test.businessobject.transmission;

import java.util.Arrays;
import java.util.Collection;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The status of the response of the transmission.&lt;br&gt; &lt;br&gt;
 * &#x60;OK&#x60; - The transmission was successfully received.&lt;br&gt;
 * &#x60;INVALID&#x60; - An error occurred during validation check at the
 * receiver.&lt;br&gt; &#x60;REJECTED&#x60; - The operation was rejected by the
 * receiver.&lt;br&gt; &#x60;EXTERNAL_ERROR&#x60; - An unexpected error occurred
 * at the receiver.&lt;br&gt; &#x60;INTERNAL_ERROR&#x60; - Internal unexpected
 * error has occurred.&lt;br&gt; &#x60;NOT_AVAILABLE&#x60; - The response status
 * is not set.
 */
@EqualsAndHashCode
@ToString
public class OMSTransmissionResponseStatus
{
    public static final OMSTransmissionResponseStatus OK             = new OMSTransmissionResponseStatus("OK");
    public static final OMSTransmissionResponseStatus INVALID        = new OMSTransmissionResponseStatus("INVALID");
    public static final OMSTransmissionResponseStatus REJECTED       = new OMSTransmissionResponseStatus("REJECTED");
    public static final OMSTransmissionResponseStatus EXTERNAL_ERROR = new OMSTransmissionResponseStatus("EXTERNAL_ERROR");
    public static final OMSTransmissionResponseStatus INTERNAL_ERROR = new OMSTransmissionResponseStatus("INTERNAL_ERROR");
    public static final OMSTransmissionResponseStatus NOT_AVAILABLE  = new OMSTransmissionResponseStatus("NOT_AVAILABLE");

    private String value;

    public OMSTransmissionResponseStatus(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    public static Collection<OMSTransmissionResponseStatus> getAllValues()
    {
        return Arrays.asList(
            OK,
            INVALID,
            REJECTED,
            EXTERNAL_ERROR,
            INTERNAL_ERROR,
            NOT_AVAILABLE
        );
    }
}
