package com.intershop.oms.test.businessobject.transmission;

import java.util.Arrays;
import java.util.Collection;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * The subtype of the transmission, that defines a special transmission of a type, e.g. an email that was sent to confirm an order.&lt;br&gt; &lt;br&gt; Use subtypes that start with &#x60;ORDER_&#x60; for type &#x60;ORDERTRANSMISSION&#x60;.&lt;br&gt; Use subtypes that start with &#x60;DISPATCH_&#x60; for type &#x60;DISPATCHTRANSMISSION&#x60;.&lt;br&gt; Use subtypes that start with &#x60;RETURN_&#x60; for type &#x60;RETURNTRANSMISSION&#x60;.&lt;br&gt; Use subtypes that start with &#x60;PAYMENT_&#x60; for type &#x60;PAYMENTTRANSMISSION&#x60;.&lt;br&gt; Use subtypes that start with &#x60;MAIL_&#x60; for type &#x60;MAILTRANSMISSION&#x60;.&lt;br&gt; Use subtypes that start with &#x60;RESPONSE_&#x60; for type &#x60;RESPONSETRANSMISSION&#x60;.&lt;br&gt; Use subtypes that start with &#x60;INVOICE_&#x60; for type &#x60;INVOICETRANSMISSION&#x60;.&lt;br&gt; Use subtypes that start with &#x60;DOCUMENT_&#x60; for type &#x60;DOCUMENTTRANSMISSION&#x60;.&lt;br&gt; Use subtypes that start with &#x60;RETURNANNOUNCEMENT_&#x60; for type &#x60;RETURNANNOUNCEMENTTRANSMISSION&#x60;.&lt;br&gt; Use the subtype &#x60;OTHERS&#x60; for any type for unlisted subtypes, e.g. custom ones.&lt;br&gt; &lt;br&gt; &#x60;ORDER_CHECKED&#x60; - Send a message that an order was validated.&lt;br&gt; &#x60;ORDER_ANNOUNCEMENT&#x60; - Send an order.&lt;br&gt; &#x60;ORDER_BACKLOG&#x60; - Send a message that the scheduled delivery date has been exceeded. At least one product has not yet been delivered.&lt;br&gt; &#x60;ORDER_RECALL&#x60; - Send a recall request for an order.&lt;br&gt; &#x60;ORDER_CONFIRMATION&#x60; - Send the confirmation for an order.&lt;br&gt; &#x60;ORDER_RECALL_REQUEST&#x60; - Resend the recall request for an order.&lt;br&gt; &#x60;ORDER_BACKLOG_REQUEST&#x60; - Send a reminding message that the scheduled delivery date has been exceeded. At least one product has not yet been delivered.&lt;br&gt; &#x60;ORDER_APPROVAL&#x60; - Send the approval of an order.&lt;br&gt; &#x60;ORDER_RESEND&#x60; - Resend an order.&lt;br&gt; &#x60;DISPATCH_SEND&#x60; - Send a dispatch message.&lt;br&gt; &#x60;DISPATCH_REQUEST_NEW&#x60; - Send the request for a new dispatch.&lt;br&gt; &#x60;RETURN_SEND&#x60; - Send a return message.&lt;br&gt; &#x60;RETURN_CONFIRMATION&#x60; - Send the confirmation of a return.&lt;br&gt;  &#x60;RETURN_REJECT&#x60; - Send the rejection of a return.&lt;br&gt; &#x60;RETURN_REQUEST_NEW&#x60; - Send a request for a new return.&lt;br&gt; &#x60;PAYMENT_SEND&#x60; - Send a payment notification.&lt;br&gt; &#x60;MAIL_ORDER&#x60; - Send an email to confirm an order.&lt;br&gt; &#x60;MAIL_ORDER_MERGE&#x60; - Send an email that orders was merged to one fulfillment.&lt;br&gt; &#x60;MAIL_READY_FOR_PICKUP&#x60; - Send an email that a delivery is ready to pickup.&lt;br&gt; &#x60;MAIL_ORDER_COMMISSIONED&#x60; - Send an email that an order was successfully commissioned.&lt;br&gt; &#x60;MAIL_TEMPORARY_RESPONSE&#x60; - Send an email that an order will be routed to the assigned fulfillment locations.&lt;br&gt; &#x60;MAIL_INITIAL_RESPONSE&#x60; - Send an email that a response was created.&lt;br&gt; &#x60;MAIL_COUNTINUOUS_RESPONSE&#x60; - Send an email that a response from the supplier was received.&lt;br&gt; &#x60;MAIL_DISPATCH&#x60; - Send an email that a dispatch was made.&lt;br&gt; &#x60;MAIL_DISPATCH_IMMATERIAL&#x60; - Send an email with links to download digital goods.&lt;br&gt; &#x60;MAIL_RETURN_CAN&#x60; - Send an email to note or confirm a return because of a cancellation.&lt;br&gt; &#x60;MAIL_RETURN_RCL&#x60; - Send an email that the order was canceled.&lt;br&gt; &#x60;MAIL_RETURN_INV&#x60; - Send an email to note an inversion, e.g. the package could not be delivered because of a wrong shipping address.&lt;br&gt; &#x60;MAIL_RETURN_RET&#x60; - Send an email for a general return.&lt;br&gt; &#x60;MAIL_RETURN_DEF&#x60; - Send an email to confirm a return because of defects of a product.&lt;br&gt; &#x60;MAIL_PAYMENT_REMINDER&#x60; - Send an email that a payment has not yet been made.&lt;br&gt; &#x60;MAIL_DELIVERY_DELAY&#x60; - Send an email to note that the expected delivery date will be delayed.&lt;br&gt; &#x60;MAIL_INVOICE&#x60; - Send an email with an attached invoice document.&lt;br&gt; &#x60;MAIL_CEDIT_NOTE&#x60; - Send an email with an attached credit note document.&lt;br&gt; &#x60;MAIL_RETURN_RCL010&#x60; - Send an email that the order was canceled. The customer has requested the cancellation.&lt;br&gt; &#x60;MAIL_RETURN_RCL020&#x60; - Send an email that the order was canceled. The product is end-of-life and not available any longer.&lt;br&gt; &#x60;MAIL_RETURN_RCL021&#x60; - Send an email that the order was canceled. The product is currently not available.&lt;br&gt; &#x60;MAIL_RETURN_RCL045&#x60; - Send an email that the order was canceled. The payment was not received yet.&lt;br&gt; &#x60;MAIL_RETURN_RCL980&#x60; - Send an email that the order was canceled. There is a suspicion of fraud.&lt;br&gt; &#x60;MAIL_RETURN_REJECTED&#x60; - Send an email that a return was rejected.&lt;br&gt; &#x60;MAIL_DISPATCH_INVOICE&#x60; - Send an email that a dispatch was made. An invoice document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_CREDIT_NOTE&#x60; - Send an email to note or confirm a return or cancellation. A credit note document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_CAN_CREDIT_NOTE&#x60; - Send an email to note or confirm a return because of a cancellation. A credit note document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_RCL_CREDIT_NOTE&#x60; - Send an email that the order was canceled. A credit note document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_INV_CREDIT_NOTE&#x60; - Send an email to note an inversion, e.g. the package could not be delivered because of a wrong shipping address. A credit note document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_RET_CREDIT_NOTE&#x60; - Send an email for a general return. A credit note document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_DEF_CREDIT_NOTE&#x60; - Send an email to confirm a return because of defects of a product. A credit note document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_RCL010_CREDIT_NOTE&#x60; - Send an email that the order was canceled. The customer has requested the return or cancellation. A credit note document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_RCL020_CREDIT_NOTE&#x60; - Send an email that the order was canceled. The product is end-of-life and not available any longer. A credit note document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_RCL045_CREDIT_NOTE&#x60; - Send an email that the order was canceled. The payment was not received yet. A credit note document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_REFUNDED&#x60; - Send an email that a refund was made.&lt;br&gt; &#x60;MAIL_RETURN_RCL980_CREDIT_NOTE&#x60; - Send an email that the order was canceled. There is a suspicion of fraud. A credit note document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_RCL021_CREDIT_NOTE&#x60; - Send an email that the order was canceled. The product is currently not available. A credit note document is attached to the email.&lt;br&gt; &#x60;MAIL_RETURN_LABEL&#x60; - Send an email with an attached return label.&lt;br&gt; &#x60;MAIL_RETURN_ANNOUNCEMENT&#x60; - Send an email that a return request was created.&lt;br&gt; &#x60;MAIL_RETURN_ANNOUNCEMENT_TRANSMISSION&#x60; - Send an email that a transmission for a return request was created.&lt;br&gt; &#x60;RESPONSE_SEND&#x60; - Send a response message.&lt;br&gt; &#x60;RESPONSE_CONFIRMATION&#x60; - Send the confirmation of a response.&lt;br&gt; &#x60;RESPONSE_REQUEST_NEW&#x60; - Send the request for a new response.&lt;br&gt; &#x60;RESPONSE_TEMPORARY&#x60; - Send a temporary response.&lt;br&gt; &#x60;RESPONSE_INITIAL&#x60; - Send an initial response.&lt;br&gt; &#x60;RESPONSE_CONTINUOUS&#x60; - Send a following response.&lt;br&gt; &#x60;INVOICE_CREATE_DOCUMENT&#x60; - Create an invoice document.&lt;br&gt; &#x60;INVOICE_DEBITOR_SEND&#x60;- Send debitor related information.&lt;br&gt; &#x60;INVOICE_OPEN_ITEM_SEND&#x60; - Send invoice related information.&lt;br&gt; &#x60;DOCUMENT_SUPPLIER_DELIVERY_NOTE_SEND&#x60; - Send a delivery note to a supplier.&lt;br&gt; &#x60;DOCUMENT_SUPPLIER_RETURN_SLIP_SEND&#x60; - Send a return slip to a supplier.&lt;br&gt; &#x60;DOCUMENT_SUPPLIER_ORDER_RETURN_LABEL_SEND&#x60; - Send a return label to a supplier.&lt;br&gt; &#x60;DOCUMENT_SHOP_DELIVERY_NOTE_SEND&#x60; - Send a delivery note to a shop.&lt;br&gt; &#x60;DOCUMENT_SHOP_RETURN_SLIP_SEND&#x60; - Send a return slip to a shop.&lt;br&gt; &#x60;DOCUMENT_SHOP_ORDER_RETURN_LABEL_SEND&#x60; - Send a return label to a shop.&lt;br&gt; &#x60;DOCUMENT_SHOP_INVOICE_CREDIT_NOTE&#x60; - Send an invoice and/or credit note to a shop.&lt;br&gt; &#x60;RETURN_ANNOUNCEMENT_SEND&#x60; - Send a return announcement.&lt;br&gt; &#x60;OTHERS&#x60; - All subtypes that are not listed, e.g. custom ones.&lt;br&gt;
 */
@EqualsAndHashCode
@ToString
public class OMSTransmissionType
{
    public static final OMSTransmissionType SEND_CHECKED_ORDER                                  = new OMSTransmissionType("SEND_CHECKED_ORDER");
    public static final OMSTransmissionType SEND_ANNOUNCE_ORDER                                 = new OMSTransmissionType("SEND_ANNOUNCE_ORDER");
    public static final OMSTransmissionType CONFIRM_RESERVATION                                 = new OMSTransmissionType("CONFIRM_RESERVATION");
    public static final OMSTransmissionType CANCEL_RESERVATION                                  = new OMSTransmissionType("CANCEL_RESERVATION");
    public static final OMSTransmissionType SEND_ORDER_BACKLOG                                  = new OMSTransmissionType("SEND_ORDER_BACKLOG");
    public static final OMSTransmissionType SEND_ORDER_RECALL                                   = new OMSTransmissionType("SEND_ORDER_RECALL");
    public static final OMSTransmissionType SEND_ORDER_CONFIRM                                  = new OMSTransmissionType("SEND_ORDER_CONFIRM");
    public static final OMSTransmissionType SEND_ORDER_RESERVATION                              = new OMSTransmissionType("SEND_ORDER_RESERVATION");
    public static final OMSTransmissionType SEND_RECALL_REQUEST                                 = new OMSTransmissionType("SEND_RECALL_REQUEST");
    public static final OMSTransmissionType SEND_BACKLOG_REQUEST                                = new OMSTransmissionType("SEND_BACKLOG_REQUEST");
    public static final OMSTransmissionType SEND_ORDER_APPROVAL                                 = new OMSTransmissionType("SEND_ORDER_APPROVAL");
    public static final OMSTransmissionType RESEND_ORDER                                        = new OMSTransmissionType("RESEND_ORDER");
    public static final OMSTransmissionType SEND_RESPONSE                                       = new OMSTransmissionType("SEND_RESPONSE");
    public static final OMSTransmissionType CONFIRM_RESPONSE                                    = new OMSTransmissionType("CONFIRM_RESPONSE");
    public static final OMSTransmissionType REQUEST_NEW_RESPONSE                                = new OMSTransmissionType("REQUEST_NEW_RESPONSE");
    public static final OMSTransmissionType SEND_RESPONSE_TEMP                                  = new OMSTransmissionType("SEND_RESPONSE_TEMP");
    public static final OMSTransmissionType SEND_RESPONSE_INIT                                  = new OMSTransmissionType("SEND_RESPONSE_INIT");
    public static final OMSTransmissionType SEND_RESPONSE_CONT                                  = new OMSTransmissionType("SEND_RESPONSE_CONT");
    public static final OMSTransmissionType SEND_RETURN                                         = new OMSTransmissionType("SEND_RETURN");
    public static final OMSTransmissionType CONFIRM_RETURN                                      = new OMSTransmissionType("CONFIRM_RETURN");
    public static final OMSTransmissionType REJECT_RETURN                                       = new OMSTransmissionType("REJECT_RETURN");
    public static final OMSTransmissionType REQUEST_NEW_RETURN                                  = new OMSTransmissionType("REQUEST_NEW_RETURN");
    public static final OMSTransmissionType SEND_RETURN_ANNOUNCEMENT                            = new OMSTransmissionType("SEND_RETURN_ANNOUNCEMENT");
    public static final OMSTransmissionType SEND_DISPATCH                                       = new OMSTransmissionType("SEND_DISPATCH");
    public static final OMSTransmissionType REQUEST_NEW_DISPATCH                                = new OMSTransmissionType("REQUEST_NEW_DISPATCH");
    public static final OMSTransmissionType SEND_SUPPLIER_DOCUMENT_DELIVERY_NOTE                = new OMSTransmissionType("SEND_SUPPLIER_DOCUMENT_DELIVERY_NOTE");
    public static final OMSTransmissionType SEND_SUPPLIER_DOCUMENT_RETURN_SLIP                  = new OMSTransmissionType("SEND_SUPPLIER_DOCUMENT_RETURN_SLIP");
    public static final OMSTransmissionType SEND_SUPPLIER_DOCUMENT_ORDER_RETURN_LABEL           = new OMSTransmissionType("SEND_SUPPLIER_DOCUMENT_ORDER_RETURN_LABEL");
    public static final OMSTransmissionType SEND_SHOP_DOCUMENT_DELIVERY_NOTE                    = new OMSTransmissionType("SEND_SHOP_DOCUMENT_DELIVERY_NOTE");
    public static final OMSTransmissionType SEND_SHOP_DOCUMENT_RETURN_SLIP                      = new OMSTransmissionType("SEND_SHOP_DOCUMENT_RETURN_SLIP");
    public static final OMSTransmissionType SEND_SHOP_DOCUMENT_ORDER_RETURN_LABEL               = new OMSTransmissionType("SEND_SHOP_DOCUMENT_ORDER_RETURN_LABEL");
    public static final OMSTransmissionType ARTICLE_DATA_IMPORT                                 = new OMSTransmissionType("ARTICLE_DATA_IMPORT");
    public static final OMSTransmissionType SEND_DYNAMIC_ARTICLE_DATA                           = new OMSTransmissionType("SEND_DYNAMIC_ARTICLE_DATA");
    public static final OMSTransmissionType ARTICLE_PICTURE                                     = new OMSTransmissionType("ARTICLE_PICTURE");
    public static final OMSTransmissionType RECEIVE_ORDER                                       = new OMSTransmissionType("RECEIVE_ORDER");
    public static final OMSTransmissionType RECEIVE_RESPONSE                                    = new OMSTransmissionType("RECEIVE_RESPONSE");
    public static final OMSTransmissionType RECEIVE_RETURN                                      = new OMSTransmissionType("RECEIVE_RETURN");
    public static final OMSTransmissionType RECEIVE_DISPATCH                                    = new OMSTransmissionType("RECEIVE_DISPATCH");
    public static final OMSTransmissionType CREATE_DOCUMENT                                     = new OMSTransmissionType("CREATE_DOCUMENT");
    public static final OMSTransmissionType CREATE_CUSTOMER_CONTACT                             = new OMSTransmissionType("CREATE_CUSTOMER_CONTACT");
    public static final OMSTransmissionType PAYMENTJOB                                          = new OMSTransmissionType("PAYMENTJOB");
    public static final OMSTransmissionType SEND_OPEN_ITEM                                      = new OMSTransmissionType("SEND_OPEN_ITEM");
    public static final OMSTransmissionType RETURN_REPORT                                       = new OMSTransmissionType("RETURN_REPORT");
    public static final OMSTransmissionType SEND_DEBITOR                                        = new OMSTransmissionType("SEND_DEBITOR");
    public static final OMSTransmissionType RECEIVE_OPEN_ITEM                                   = new OMSTransmissionType("RECEIVE_OPEN_ITEM");
    public static final OMSTransmissionType SEND_SHOP_DOCUMENT_INVOICECREDIT_NOTE               = new OMSTransmissionType("SEND_SHOP_DOCUMENT_INVOICECREDIT_NOTE");
    public static final OMSTransmissionType REQUEST_IDENT_CODE                                  = new OMSTransmissionType("REQUEST_IDENT_CODE");
    public static final OMSTransmissionType DOWNLOAD_DOCUMENT                                   = new OMSTransmissionType("DOWNLOAD_DOCUMENT");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_ORDER                            = new OMSTransmissionType("SEND_CUSTOMER_MAIL_ORDER");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_ORDER_MERGE                      = new OMSTransmissionType("SEND_CUSTOMER_MAIL_ORDER_MERGE");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_READY_FOR_PICK_UP                = new OMSTransmissionType("SEND_CUSTOMER_MAIL_READY_FOR_PICK_UP");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_ORDER_COMMISSIONED               = new OMSTransmissionType("SEND_CUSTOMER_MAIL_ORDER_COMMISSIONED");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RESPONSE_TEMPORARY               = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RESPONSE_TEMPORARY");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RESPONSE_INITIAL                 = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RESPONSE_INITIAL");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RESPONSE_CONTINUOUS              = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RESPONSE_CONTINUOUS");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_DISPATCH                         = new OMSTransmissionType("SEND_CUSTOMER_MAIL_DISPATCH");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_DISPATCH_IMMATERIAL              = new OMSTransmissionType("SEND_CUSTOMER_MAIL_DISPATCH_IMMATERIAL");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_CAN                       = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_CAN");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_RCL                       = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_RCL");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_INV                       = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_INV");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_RET                       = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_RET");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_DEF                       = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_DEF");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_PAYMENT_REMINDER                 = new OMSTransmissionType("SEND_CUSTOMER_MAIL_PAYMENT_REMINDER");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_DELIVERY_DELAY                   = new OMSTransmissionType("SEND_CUSTOMER_MAIL_DELIVERY_DELAY");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_INVOICE                          = new OMSTransmissionType("SEND_CUSTOMER_MAIL_INVOICE");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_CREDITNOTE                       = new OMSTransmissionType("SEND_CUSTOMER_MAIL_CREDITNOTE");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_RCL010                    = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_RCL010");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_RCL020                    = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_RCL020");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_RCL021                    = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_RCL021");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_RCL045                    = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_RCL045");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_RCL980                    = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_RCL980");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_REJECTED                  = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_REJECTED");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_DISPATCH_INVOICE                 = new OMSTransmissionType("SEND_CUSTOMER_MAIL_DISPATCH_INVOICE");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_CREDITNOTE                = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_CREDITNOTE");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_CAN_CREDITNOTE            = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_CAN_CREDITNOTE");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_RCL_CREDITNOTE            = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_RCL_CREDITNOTE");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_INV_CREDITNOTE            = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_INV_CREDITNOTE");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_RET_CREDITNOTE            = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_RET_CREDITNOTE");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_DEF_CREDITNOTE            = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_DEF_CREDITNOTE");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_RCL010_CREDITNOTE         = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_RCL010_CREDITNOTE");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_RCL020_CREDITNOTE         = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_RCL020_CREDITNOTE");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_RCL045_CREDITNOTE         = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_RCL045_CREDITNOTE");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_REFUNDED                  = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_REFUNDED");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_RCL980_CREDITNOTE         = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_RCL980_CREDITNOTE");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_RCL021_CREDITNOTE         = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_RCL021_CREDITNOTE");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_LABEL                     = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_LABEL");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_ANNOUNCEMENT              = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_ANNOUNCEMENT");
    public static final OMSTransmissionType SEND_CUSTOMER_MAIL_RETURN_ANNOUNCEMENT_TRANSMISSION = new OMSTransmissionType("SEND_CUSTOMER_MAIL_RETURN_ANNOUNCEMENT_TRANSMISSION");
    public static final OMSTransmissionType EXT_SEND_ORDER                                      = new OMSTransmissionType("EXT_SEND_ORDER");
    public static final OMSTransmissionType EXT_RECEIVE_ORDER                                   = new OMSTransmissionType("EXT_RECEIVE_ORDER");
    public static final OMSTransmissionType EXT_SEND_ARTICLE                                    = new OMSTransmissionType("EXT_SEND_ARTICLE");
    public static final OMSTransmissionType EXT_RECEIVE_ARTICLE                                 = new OMSTransmissionType("EXT_RECEIVE_ARTICLE");
    public static final OMSTransmissionType EXT_RECEIVE_STOCK                                   = new OMSTransmissionType("EXT_RECEIVE_STOCK");
    public static final OMSTransmissionType EXT_RECEIVE_RETURN                                  = new OMSTransmissionType("EXT_RECEIVE_RETURN");
    public static final OMSTransmissionType EXT_RECEIVE_DISPATCH                                = new OMSTransmissionType("EXT_RECEIVE_DISPATCH");
    public static final OMSTransmissionType EXT_TRANSFER_FILE                                   = new OMSTransmissionType("EXT_TRANSFER_FILE");
    public static final OMSTransmissionType EXT_SEND_SUPPLIER_DOCUMENT_DELIVERY_NOTE            = new OMSTransmissionType("EXT_SEND_SUPPLIER_DOCUMENT_DELIVERY_NOTE");
    public static final OMSTransmissionType EXT_SEND_SALES_ANALYTICS                            = new OMSTransmissionType("EXT_SEND_SALES_ANALYTICS");

    private String value;

    public OMSTransmissionType(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    public Collection<OMSTransmissionType> getAllValues()
    {
        return Arrays.asList(
            SEND_CHECKED_ORDER,
            SEND_ANNOUNCE_ORDER,
            CONFIRM_RESERVATION,
            CANCEL_RESERVATION,
            SEND_ORDER_BACKLOG,
            SEND_ORDER_RECALL,
            SEND_ORDER_CONFIRM,
            SEND_ORDER_RESERVATION,
            SEND_RECALL_REQUEST,
            SEND_BACKLOG_REQUEST,
            SEND_ORDER_APPROVAL,
            RESEND_ORDER,
            SEND_RESPONSE,
            CONFIRM_RESPONSE,
            REQUEST_NEW_RESPONSE,
            SEND_RESPONSE_TEMP,
            SEND_RESPONSE_INIT,
            SEND_RESPONSE_CONT,
            SEND_RETURN,
            CONFIRM_RETURN,
            REJECT_RETURN,
            REQUEST_NEW_RETURN,
            SEND_RETURN_ANNOUNCEMENT,
            SEND_DISPATCH,
            REQUEST_NEW_DISPATCH,
            SEND_SUPPLIER_DOCUMENT_DELIVERY_NOTE,
            SEND_SUPPLIER_DOCUMENT_RETURN_SLIP,
            SEND_SUPPLIER_DOCUMENT_ORDER_RETURN_LABEL,
            SEND_SHOP_DOCUMENT_DELIVERY_NOTE,
            SEND_SHOP_DOCUMENT_RETURN_SLIP,
            SEND_SHOP_DOCUMENT_ORDER_RETURN_LABEL,
            ARTICLE_DATA_IMPORT,
            SEND_DYNAMIC_ARTICLE_DATA,
            ARTICLE_PICTURE,
            RECEIVE_ORDER,
            RECEIVE_RESPONSE,
            RECEIVE_RETURN,
            RECEIVE_DISPATCH,
            CREATE_DOCUMENT,
            CREATE_CUSTOMER_CONTACT,
            PAYMENTJOB,
            SEND_OPEN_ITEM,
            RETURN_REPORT,
            SEND_DEBITOR,
            RECEIVE_OPEN_ITEM,
            SEND_SHOP_DOCUMENT_INVOICECREDIT_NOTE,
            REQUEST_IDENT_CODE,
            DOWNLOAD_DOCUMENT,
            SEND_CUSTOMER_MAIL_ORDER,
            SEND_CUSTOMER_MAIL_ORDER_MERGE,
            SEND_CUSTOMER_MAIL_READY_FOR_PICK_UP,
            SEND_CUSTOMER_MAIL_ORDER_COMMISSIONED,
            SEND_CUSTOMER_MAIL_RESPONSE_TEMPORARY,
            SEND_CUSTOMER_MAIL_RESPONSE_INITIAL,
            SEND_CUSTOMER_MAIL_RESPONSE_CONTINUOUS,
            SEND_CUSTOMER_MAIL_DISPATCH,
            SEND_CUSTOMER_MAIL_DISPATCH_IMMATERIAL,
            SEND_CUSTOMER_MAIL_RETURN_CAN,
            SEND_CUSTOMER_MAIL_RETURN_RCL,
            SEND_CUSTOMER_MAIL_RETURN_INV,
            SEND_CUSTOMER_MAIL_RETURN_RET,
            SEND_CUSTOMER_MAIL_RETURN_DEF,
            SEND_CUSTOMER_MAIL_PAYMENT_REMINDER,
            SEND_CUSTOMER_MAIL_DELIVERY_DELAY,
            SEND_CUSTOMER_MAIL_INVOICE,
            SEND_CUSTOMER_MAIL_CREDITNOTE,
            SEND_CUSTOMER_MAIL_RETURN_RCL010,
            SEND_CUSTOMER_MAIL_RETURN_RCL020,
            SEND_CUSTOMER_MAIL_RETURN_RCL021,
            SEND_CUSTOMER_MAIL_RETURN_RCL045,
            SEND_CUSTOMER_MAIL_RETURN_RCL980,
            SEND_CUSTOMER_MAIL_RETURN_REJECTED,
            SEND_CUSTOMER_MAIL_DISPATCH_INVOICE,
            SEND_CUSTOMER_MAIL_RETURN_CREDITNOTE,
            SEND_CUSTOMER_MAIL_RETURN_CAN_CREDITNOTE,
            SEND_CUSTOMER_MAIL_RETURN_RCL_CREDITNOTE,
            SEND_CUSTOMER_MAIL_RETURN_INV_CREDITNOTE,
            SEND_CUSTOMER_MAIL_RETURN_RET_CREDITNOTE,
            SEND_CUSTOMER_MAIL_RETURN_DEF_CREDITNOTE,
            SEND_CUSTOMER_MAIL_RETURN_RCL010_CREDITNOTE,
            SEND_CUSTOMER_MAIL_RETURN_RCL020_CREDITNOTE,
            SEND_CUSTOMER_MAIL_RETURN_RCL045_CREDITNOTE,
            SEND_CUSTOMER_MAIL_RETURN_REFUNDED,
            SEND_CUSTOMER_MAIL_RETURN_RCL980_CREDITNOTE,
            SEND_CUSTOMER_MAIL_RETURN_RCL021_CREDITNOTE,
            SEND_CUSTOMER_MAIL_RETURN_LABEL,
            SEND_CUSTOMER_MAIL_RETURN_ANNOUNCEMENT,
            SEND_CUSTOMER_MAIL_RETURN_ANNOUNCEMENT_TRANSMISSION,
            EXT_SEND_ORDER,
            EXT_RECEIVE_ORDER,
            EXT_SEND_ARTICLE,
            EXT_RECEIVE_ARTICLE,
            EXT_RECEIVE_STOCK,
            EXT_RECEIVE_RETURN,
            EXT_RECEIVE_DISPATCH,
            EXT_TRANSFER_FILE,
            EXT_SEND_SUPPLIER_DOCUMENT_DELIVERY_NOTE,
            EXT_SEND_SALES_ANALYTICS
            );
    }
}