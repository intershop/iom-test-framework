package com.intershop.oms.test.businessobject.transmission;

/**
 * The attribute on which should be sorted.&lt;br&gt; &lt;br&gt; &#x60;id&#x60; - Sort by the identifier in scope of it&#x27;s type.&lt;br&gt; &#x60;transmissionSubtype&#x60; - Sort by the subtype of the transmission.&lt;br&gt; &#x60;status&#x60; - Sort by the processing status.&lt;br&gt; &#x60;responseStatus&#x60; - Sort by the response status.&lt;br&gt; &#x60;creationDate&#x60; - Sort by the creation date.&lt;br&gt; &#x60;modificationDate&#x60; - Sort by the modification date.&lt;br&gt; &#x60;orderId&#x60; - Sort by the id of the order.&lt;br&gt;  &#x60;shopOrderNumber&#x60; - Sort by the shop order number.&lt;br&gt; &#x60;shopId&#x60; - Sort by the id of the shop.&lt;br&gt; &#x60;shopName&#x60; - Sort by the name of the shop.&lt;br&gt; &#x60;supplierId&#x60; - Sort by the id of the supplier.&lt;br&gt; &#x60;supplierName&#x60; - Sort by the name of the supplier.&lt;br&gt; &#x60;receiver&#x60; - Sort by the receiver of the transmission.&lt;br&gt; &#x60;retryCount&#x60; - Sort by the number of sending attempts.&lt;br&gt; &#x60;retryDate&#x60; - Sort by the date of the last attempt to send.&lt;br&gt; &#x60;nextRetryDate&#x60; - Sort by the next date to send.&lt;br&gt; &#x60;errorText&#x60; - Sort by the possible error message of the transmission. 
 */
public enum OMSSortableTransmissionAttribute
{
  ID("id"),
  TRANSMISSIONTYPE("transmissionType"),
  TRANSMISSIONSUBTYPE("transmissionSubtype"),
  STATUS("status"),
  RESPONSESTATUS("responseStatus"),
  CREATIONDATE("creationDate"),
  MODIFICATIONDATE("modificationDate"),
  SHOPORDERNUMBER("shopOrderNumber"),
  ORDERID("orderId"),
  SHOPID("shopId"),
  SHOPNAME("shopName"),
  SUPPLIERID("supplierId"),
  SUPPLIERNAME("supplierName"),
  RECEIVERTYPE("receiverType"),
  RETRYCOUNT("retryCount"),
  RETRYDATE("retryDate"),
  NEXTRETRYDATE("nextRetryDate"),
  ERRORTEXT("errorText");

  private String value;

  OMSSortableTransmissionAttribute(String value)
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

  public static OMSSortableTransmissionAttribute fromValue(String text)
  {
      for (OMSSortableTransmissionAttribute b : OMSSortableTransmissionAttribute.values())
      {
          if (String.valueOf(b.value).equals(text))
          {
              return b;
          }
      }
      return null;
  }
}
