package com.intershop.oms.test.businessobject.transmission;

/**
 * The type of the transmission.&lt;br&gt; &lt;br&gt; &#x60;ORDERTRANSMISSION&#x60; - Transmissions that are used for order processing messages, e.g. to announce an order entry. Subtypes of this type start with &#x60;ORDER_&#x60;.&lt;br&gt;  &#x60;DISPATCHTRANSMISSION&#x60; - Transmissions that are used for dispatch messages, e.g. a delivery that was made. Subtypes of this type start with &#x60;DISPATCH_&#x60;.&lt;br&gt; &#x60;RETURNTRANSMISSION&#x60; - Transmissions that are used for return messages, e.g. a received return. Subtypes of this type start with &#x60;RETURN_&#x60;.&lt;br&gt; &#x60;PAYMENTTRANSMISSION&#x60; - Transmissions that are used for payment notification messages, e.g. a received payment. Subtypes of this type start with &#x60;PAYMENT_&#x60;.&lt;br&gt; &#x60;MAILTRANSMISSION&#x60; - Transmissions that are used for emails, e.g. to send a delivery email to the customer. Subtypes of this type start with &#x60;MAIL_&#x60;.&lt;br&gt; &#x60;RESPONSETRANSMISSION&#x60; - Transmissions that are used for response messages, e.g. to respond to a delivery request. Subtypes of this type start with &#x60;RESPONSE_&#x60;.&lt;br&gt; &#x60;INVOICETRANSMISSION&#x60; - Transmissions that are used for invoice processing messages. Subtypes of this type start with &#x60;INVOICE_&#x60;.&lt;br&gt; &#x60;DOCUMENTTRANSMISSION&#x60; - Transmissions that are used for document processing messages. Subtypes of this type start with &#x60;DOCUMENT_&#x60;.&lt;br&gt;    &#x60;RETURNANNOUNCEMENTTRANSMISSION&#x60; - Transmissions that are used for return announcements messages. Subtypes of this type start with &#x60;RETURNANNOUNCEMENT_&#x60;.&lt;br&gt; &lt;br&gt; For any type use &#x60;OTHERS&#x60; for subtypes that are not listed, e.g. custom ones.&lt;br&gt; 
 */
public enum OMSTransmissionType
{
  ORDERTRANSMISSION("ORDERTRANSMISSION"),
  DISPATCHTRANSMISSION("DISPATCHTRANSMISSION"),
  RETURNTRANSMISSION("RETURNTRANSMISSION"),
  PAYMENTTRANSMISSION("PAYMENTTRANSMISSION"),
  MAILTRANSMISSION("MAILTRANSMISSION"),
  RESPONSETRANSMISSION("RESPONSETRANSMISSION"),
  INVOICETRANSMISSION("INVOICETRANSMISSION"),
  DOCUMENTTRANSMISSION("DOCUMENTTRANSMISSION"),
  RETURNANNOUNCEMENTTRANSMISSION("RETURNANNOUNCEMENTTRANSMISSION");

  private String value;

  OMSTransmissionType(String value)
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

  public static OMSTransmissionType fromValue(String text)
  {
      for (OMSTransmissionType b : OMSTransmissionType.values())
      {
          if (String.valueOf(b.value).equals(text))
          {
              return b;
          }
      }
      return null;
  }
}
