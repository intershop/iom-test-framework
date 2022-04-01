package com.intershop.oms.test.businessobject.transmission;

/**
 * The subtype of the transmission, that defines a special transmission of a type, e.g. an email that was sent to confirm an order.&lt;br&gt; &lt;br&gt; Use subtypes that start with &#x60;ORDER_&#x60; for type &#x60;ORDERTRANSMISSION&#x60;.&lt;br&gt; Use subtypes that start with &#x60;DISPATCH_&#x60; for type &#x60;DISPATCHTRANSMISSION&#x60;.&lt;br&gt; Use subtypes that start with &#x60;RETURN_&#x60; for type &#x60;RETURNTRANSMISSION&#x60;.&lt;br&gt; Use subtypes that start with &#x60;PAYMENT_&#x60; for type &#x60;PAYMENTTRANSMISSION&#x60;.&lt;br&gt; Use subtypes that start with &#x60;MAIL_&#x60; for type &#x60;MAILTRANSMISSION&#x60;.&lt;br&gt; Use subtypes that start with &#x60;RESPONSE_&#x60; for type &#x60;RESPONSETRANSMISSION&#x60;.&lt;br&gt; Use subtypes that start with &#x60;INVOICE_&#x60; for type &#x60;INVOICETRANSMISSION&#x60;.&lt;br&gt; Use subtypes that start with &#x60;DOCUMENT_&#x60; for type &#x60;DOCUMENTTRANSMISSION&#x60;.&lt;br&gt; Use subtypes that start with &#x60;RETURNANNOUNCEMENT_&#x60; for type &#x60;RETURNANNOUNCEMENTTRANSMISSION&#x60;.&lt;br&gt; Use the subtype &#x60;OTHERS&#x60; for any type for unlisted subtypes, e.g. custom ones.&lt;br&gt; &lt;br&gt; &#x60;ORDER_CHECKED&#x60; - Send a message that an order was validated.&lt;br&gt; &#x60;ORDER_ANNOUNCEMENT&#x60; - Send an order.&lt;br&gt; &#x60;ORDER_BACKLOG&#x60; - Send a message that the scheduled delivery date has been exceeded. At least one product has not yet been delivered.&lt;br&gt; &#x60;ORDER_RECALL&#x60; - Send a recall request for an order.&lt;br&gt; &#x60;ORDER_CONFIRMATION&#x60; - Send the confirmation for an order.&lt;br&gt; &#x60;ORDER_RECALL_REQUEST&#x60; - Resend the recall request for an order.&lt;br&gt; &#x60;ORDER_BACKLOG_REQUEST&#x60; - Send a reminding message that the scheduled delivery date has been exceeded. At least one product has not yet been delivered.&lt;br&gt; &#x60;ORDER_APPROVAL&#x60; - Send the approval of an order.&lt;br&gt; &#x60;ORDER_RESEND&#x60; - Resend an order.&lt;br&gt; &#x60;DISPATCH_SEND&#x60; - Send a dispatch message.&lt;br&gt; &#x60;DISPATCH_REQUEST_NEW&#x60; - Send the request for a new dispatch.&lt;br&gt; &#x60;RETURN_SEND&#x60; - Send a return message.&lt;br&gt; &#x60;RETURN_CONFIRMATION&#x60; - Send the confirmation of a return.&lt;br&gt;  &#x60;RETURN_REJECT&#x60; - Send the rejection of a return.&lt;br&gt; &#x60;RETURN_REQUEST_NEW&#x60; - Send a request for a new return.&lt;br&gt; &#x60;PAYMENT_SEND&#x60; - Send a payment notification.&lt;br&gt; &#x60;MAIL_ORDER&#x60; - Send an email to confirm an order.&lt;br&gt; &#x60;MAIL_ORDER_MERGE&#x60; - Send an email that orders was merged to one fulfillment.&lt;br&gt; &#x60;MAIL_READY_FOR_PICKUP&#x60; - Send an email that a delivery is ready to pickup.&lt;br&gt; &#x60;MAIL_ORDER_COMMISSIONED&#x60; - Send an email that an order was successfully commissioned.&lt;br&gt; &#x60;MAIL_TEMPORARY_RESPONSE&#x60; - Send an email that an order will be routed to the assigned fulfillment locations.&lt;br&gt; &#x60;MAIL_INITIAL_RESPONSE&#x60; - Send an email that a response was created.&lt;br&gt; &#x60;MAIL_COUNTINUOUS_RESPONSE&#x60; - Send an email that a response from the supplier was received.&lt;br&gt; &#x60;MAIL_DISPATCH&#x60; - Send an email that a dispatch was made.&lt;br&gt; &#x60;MAIL_DISPATCH_IMMATERIAL&#x60; - Send an email with links to download digital goods.&lt;br&gt; &#x60;MAIL_RETURN_CAN&#x60; - Send an email to note or confirm a return because of a cancellation.&lt;br&gt; &#x60;MAIL_RETURN_RCL&#x60; - Send an email that the order was canceled.&lt;br&gt; &#x60;MAIL_RETURN_INV&#x60; - Send an email to note an inversion, e.g. the package could not be delivered because of a wrong shipping address.&lt;br&gt; &#x60;MAIL_RETURN_RET&#x60; - Send an email for a general return.&lt;br&gt; &#x60;MAIL_RETURN_DEF&#x60; - Send an email to confirm a return because of defects of a product.&lt;br&gt; &#x60;MAIL_PAYMENT_REMINDER&#x60; - Send an email that a payment has not yet been made.&lt;br&gt; &#x60;MAIL_DELIVERY_DELAY&#x60; - Send an email to note that the expected delivery date will be delayed.&lt;br&gt; &#x60;MAIL_INVOICE&#x60; - Send an email with an attached invoice document.&lt;br&gt; &#x60;MAIL_CEDIT_NOTE&#x60; - Send an email with an attached credit note document.&lt;br&gt; &#x60;MAIL_RETURN_RCL010&#x60; - Send an email that the order was canceled. The customer has requested the cancellation.&lt;br&gt; &#x60;MAIL_RETURN_RCL020&#x60; - Send an email that the order was canceled. The product is end-of-life and not available any longer.&lt;br&gt; &#x60;MAIL_RETURN_RCL021&#x60; - Send an email that the order was canceled. The product is currently not available.&lt;br&gt; &#x60;MAIL_RETURN_RCL045&#x60; - Send an email that the order was canceled. The payment was not received yet.&lt;br&gt; &#x60;MAIL_RETURN_RCL980&#x60; - Send an email that the order was canceled. There is a suspicion of fraud.&lt;br&gt; &#x60;MAIL_RETURN_REJECTED&#x60; - Send an email that a return was rejected.&lt;br&gt; &#x60;MAIL_DISPATCH_INVOICE&#x60; - Send an email that a dispatch was made. An invoice document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_CREDIT_NOTE&#x60; - Send an email to note or confirm a return or cancellation. A credit note document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_CAN_CREDIT_NOTE&#x60; - Send an email to note or confirm a return because of a cancellation. A credit note document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_RCL_CREDIT_NOTE&#x60; - Send an email that the order was canceled. A credit note document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_INV_CREDIT_NOTE&#x60; - Send an email to note an inversion, e.g. the package could not be delivered because of a wrong shipping address. A credit note document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_RET_CREDIT_NOTE&#x60; - Send an email for a general return. A credit note document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_DEF_CREDIT_NOTE&#x60; - Send an email to confirm a return because of defects of a product. A credit note document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_RCL010_CREDIT_NOTE&#x60; - Send an email that the order was canceled. The customer has requested the return or cancellation. A credit note document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_RCL020_CREDIT_NOTE&#x60; - Send an email that the order was canceled. The product is end-of-life and not available any longer. A credit note document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_RCL045_CREDIT_NOTE&#x60; - Send an email that the order was canceled. The payment was not received yet. A credit note document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_REFUNDED&#x60; - Send an email that a refund was made.&lt;br&gt; &#x60;MAIL_RETURN_RCL980_CREDIT_NOTE&#x60; - Send an email that the order was canceled. There is a suspicion of fraud. A credit note document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_RCL021_CREDIT_NOTE&#x60; - Send an email that the order was canceled. The product is currently not available. A credit note document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_LABEL&#x60; - Send an email with an attached return label.&lt;br&gt; &#x60;MAIL_RETURN_ANNOUNCEMENT&#x60; - Send an email that a return request was created.&lt;br&gt; &#x60;MAIL_RETURN_ANNOUNCEMENT_TRANSMISSION&#x60; - Send an email that a transmission for a return request was created.&lt;br&gt; &#x60;RESPONSE_SEND&#x60; - Send a response message.&lt;br&gt; &#x60;RESPONSE_CONFIRMATION&#x60; - Send the confirmation of a response.&lt;br&gt; &#x60;RESPONSE_REQUEST_NEW&#x60; - Send the request for a new response.&lt;br&gt; &#x60;RESPONSE_TEMPORARY&#x60; - Send a temporary response.&lt;br&gt; &#x60;RESPONSE_INITIAL&#x60; - Send an initial response.&lt;br&gt; &#x60;RESPONSE_CONTINUOUS&#x60; - Send a following response.&lt;br&gt; &#x60;INVOICE_CREATE_DOCUMENT&#x60; - Create an invoice document.&lt;br&gt; &#x60;INVOICE_DEBITOR_SEND&#x60;- Send debitor related information.&lt;br&gt; &#x60;INVOICE_OPEN_ITEM_SEND&#x60; - Send invoice related information.&lt;br&gt; &#x60;DOCUMENT_SUPPLIER_DELIVERY_NOTE_SEND&#x60; - Send a delivery note to a supplier.&lt;br&gt; &#x60;DOCUMENT_SUPPLIER_RETURN_SLIP_SEND&#x60; - Send a return slip to a supplier.&lt;br&gt; &#x60;DOCUMENT_SUPPLIER_ORDER_RETURN_LABEL_SEND&#x60; - Send a return label to a supplier.&lt;br&gt; &#x60;DOCUMENT_SHOP_DELIVERY_NOTE_SEND&#x60; - Send a delivery note to a shop.&lt;br&gt; &#x60;DOCUMENT_SHOP_RETURN_SLIP_SEND&#x60; - Send a return slip to a shop.&lt;br&gt; &#x60;DOCUMENT_SHOP_ORDER_RETURN_LABEL_SEND&#x60; - Send a return label to a shop.&lt;br&gt; &#x60;DOCUMENT_SHOP_INVOICE_CREDIT_NOTE&#x60; - Send an invoice and/or credit note to a shop.&lt;br&gt; &#x60;RETURN_ANNOUNCEMENT_SEND&#x60; - Send a return announcement.&lt;br&gt; &#x60;OTHERS&#x60; - All subtypes that are not listed, e.g. custom ones.&lt;br&gt;
 */
public enum OMSTransmissionSubtype
{
  ORDER_CHECKED("ORDER_CHECKED"),
  ORDER_ANNOUNCEMENT("ORDER_ANNOUNCEMENT"),
  ORDER_BACKLOG("ORDER_BACKLOG"),
  ORDER_RECALL("ORDER_RECALL"),
  ORDER_CONFIRMATION("ORDER_CONFIRMATION"),
  ORDER_RECALL_REQUEST("ORDER_RECALL_REQUEST"),
  ORDER_BACKLOG_REQUEST("ORDER_BACKLOG_REQUEST"),
  ORDER_APPROVAL("ORDER_APPROVAL"),
  ORDER_RESEND("ORDER_RESEND"),
  DISPATCH_SEND("DISPATCH_SEND"),
  DISPATCH_REQUEST_NEW("DISPATCH_REQUEST_NEW"),
  RETURN_SEND("RETURN_SEND"),
  RETURN_CONFIRMATION("RETURN_CONFIRMATION"),
  RETURN_REJECT("RETURN_REJECT"),
  RETURN_REQUEST_NEW("RETURN_REQUEST_NEW"),
  PAYMENT_SEND("PAYMENT_SEND"),
  MAIL_ORDER("MAIL_ORDER"),
  MAIL_ORDER_MERGE("MAIL_ORDER_MERGE"),
  MAIL_READY_FOR_PICKUP("MAIL_READY_FOR_PICKUP"),
  MAIL_ORDER_COMMISSIONED("MAIL_ORDER_COMMISSIONED"),
  MAIL_TEMPORARY_RESPONSE("MAIL_TEMPORARY_RESPONSE"),
  MAIL_INITIAL_RESPONSE("MAIL_INITIAL_RESPONSE"),
  MAIL_COUNTINUOUS_RESPONSE("MAIL_COUNTINUOUS_RESPONSE"),
  MAIL_DISPATCH("MAIL_DISPATCH"),
  MAIL_DISPATCH_IMMATERIAL("MAIL_DISPATCH_IMMATERIAL"),
  MAIL_RETURN_CAN("MAIL_RETURN_CAN"),
  MAIL_RETURN_RCL("MAIL_RETURN_RCL"),
  MAIL_RETURN_INV("MAIL_RETURN_INV"),
  MAIL_RETURN_RET("MAIL_RETURN_RET"),
  MAIL_RETURN_DEF("MAIL_RETURN_DEF"),
  MAIL_PAYMENT_REMINDER("MAIL_PAYMENT_REMINDER"),
  MAIL_DELIVERY_DELAY("MAIL_DELIVERY_DELAY"),
  MAIL_INVOICE("MAIL_INVOICE"),
  MAIL_CEDIT_NOTE("MAIL_CEDIT_NOTE"),
  MAIL_RETURN_RCL010("MAIL_RETURN_RCL010"),
  MAIL_RETURN_RCL020("MAIL_RETURN_RCL020"),
  MAIL_RETURN_RCL021("MAIL_RETURN_RCL021"),
  MAIL_RETURN_RCL045("MAIL_RETURN_RCL045"),
  MAIL_RETURN_RCL980("MAIL_RETURN_RCL980"),
  MAIL_RETURN_REJECTED("MAIL_RETURN_REJECTED"),
  MAIL_DISPATCH_INVOICE("MAIL_DISPATCH_INVOICE"),
  MAIL_RETURN_CREDIT_NOTE("MAIL_RETURN_CREDIT_NOTE"),
  MAIL_RETURN_CAN_CREDIT_NOTE("MAIL_RETURN_CAN_CREDIT_NOTE"),
  MAIL_RETURN_RCL_CREDIT_NOTE("MAIL_RETURN_RCL_CREDIT_NOTE"),
  MAIL_RETURN_INV_CREDIT_NOTE("MAIL_RETURN_INV_CREDIT_NOTE"),
  MAIL_RETURN_RET_CREDIT_NOTE("MAIL_RETURN_RET_CREDIT_NOTE"),
  MAIL_RETURN_DEF_CREDIT_NOTE("MAIL_RETURN_DEF_CREDIT_NOTE"),
  MAIL_RETURN_RCL010_CREDIT_NOTE("MAIL_RETURN_RCL010_CREDIT_NOTE"),
  MAIL_RETURN_RCL020_CREDIT_NOTE("MAIL_RETURN_RCL020_CREDIT_NOTE"),
  MAIL_RETURN_RCL045_CREDIT_NOTE("MAIL_RETURN_RCL045_CREDIT_NOTE"),
  MAIL_RETURN_REFUNDED("MAIL_RETURN_REFUNDED"),
  MAIL_RETURN_RCL980_CREDIT_NOTE("MAIL_RETURN_RCL980_CREDIT_NOTE"),
  MAIL_RETURN_RCL021_CREDIT_NOTE("MAIL_RETURN_RCL021_CREDIT_NOTE"),
  MAIL_RETURN_LABEL("MAIL_RETURN_LABEL"),
  MAIL_RETURN_ANNOUNCEMENT("MAIL_RETURN_ANNOUNCEMENT"),
  MAIL_RETURN_ANNOUNCEMENT_TRANSMISSION("MAIL_RETURN_ANNOUNCEMENT_TRANSMISSION"),
  RESPONSE_SEND("RESPONSE_SEND"),
  RESPONSE_CONFIRMATION("RESPONSE_CONFIRMATION"),
  RESPONSE_REQUEST_NEW("RESPONSE_REQUEST_NEW"),
  RESPONSE_TEMPORARY("RESPONSE_TEMPORARY"),
  RESPONSE_INITIAL("RESPONSE_INITIAL"),
  RESPONSE_CONTINUOUS("RESPONSE_CONTINUOUS"),
  INVOICE_CREATE_DOCUMENT("INVOICE_CREATE_DOCUMENT"),
  INVOICE_DEBITOR_SEND("INVOICE_DEBITOR_SEND"),
  INVOICE_OPEN_ITEM_SEND("INVOICE_OPEN_ITEM_SEND"),
  DOCUMENT_SUPPLIER_DELIVERY_NOTE_SEND("DOCUMENT_SUPPLIER_DELIVERY_NOTE_SEND"),
  DOCUMENT_SUPPLIER_RETURN_SLIP_SEND("DOCUMENT_SUPPLIER_RETURN_SLIP_SEND"),
  DOCUMENT_SUPPLIER_ORDER_RETURN_LABEL_SEND("DOCUMENT_SUPPLIER_ORDER_RETURN_LABEL_SEND"),
  DOCUMENT_SHOP_DELIVERY_NOTE_SEND("DOCUMENT_SHOP_DELIVERY_NOTE_SEND"),
  DOCUMENT_SHOP_RETURN_SLIP_SEND("DOCUMENT_SHOP_RETURN_SLIP_SEND"),
  DOCUMENT_SHOP_ORDER_RETURN_LABEL_SEND("DOCUMENT_SHOP_ORDER_RETURN_LABEL_SEND"),
  DOCUMENT_SHOP_INVOICE_CREDIT_NOTE("DOCUMENT_SHOP_INVOICE_CREDIT_NOTE"),
  RETURN_ANNOUNCEMENT_SEND("RETURN_ANNOUNCEMENT_SEND"),
  OTHERS("OTHERS");

    private String value;

    OMSTransmissionSubtype(String value)
    {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static OMSTransmissionSubtype fromValue(String text)
    {
        for (OMSTransmissionSubtype b : OMSTransmissionSubtype.values())
        {
            if (String.valueOf(b.value).equals(text))
            {
                return b;
            }
        }
        return null;
    }
}