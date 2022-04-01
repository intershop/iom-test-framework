package com.intershop.oms.test.businessobject.transmission;

/**
 * The status of the response of the transmission.&lt;br&gt; &lt;br&gt; &#x60;OK&#x60; - The transmission was successfully received.&lt;br&gt; &#x60;INVALID&#x60; - An error occurred during validation check at the receiver.&lt;br&gt; &#x60;REJECTED&#x60; - The operation was rejected by the receiver.&lt;br&gt; &#x60;EXTERNAL_ERROR&#x60; - An unexpected error occurred at the receiver.&lt;br&gt; &#x60;INTERNAL_ERROR&#x60; - Internal unexpected error has occurred.&lt;br&gt; &#x60;NOT_AVAILABLE&#x60; - The response status is not set. 
 */
public enum OMSTransmissionResponseStatus
{
  OK("OK"),
  INVALID("INVALID"),
  REJECTED("REJECTED"),
  EXTERNAL_ERROR("EXTERNAL_ERROR"),
  INTERNAL_ERROR("INTERNAL_ERROR"),
  NOT_AVAILABLE("NOT_AVAILABLE");

  private String value;

  OMSTransmissionResponseStatus(String value)
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

  public static OMSTransmissionResponseStatus fromValue(String text)
  {
    for (OMSTransmissionResponseStatus b : OMSTransmissionResponseStatus.values())
    {
      if (String.valueOf(b.value).equals(text))
      {
        return b;
      }
    }
    return null;
  }
}
