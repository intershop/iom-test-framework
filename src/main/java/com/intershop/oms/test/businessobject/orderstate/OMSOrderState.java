package com.intershop.oms.test.businessobject.orderstate;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.address.OMSAddressInvoice;
import com.intershop.oms.test.businessobject.order.OMSCustomerData;
import com.intershop.oms.test.businessobject.order.OMSPayment;
import com.intershop.oms.test.businessobject.order.OMSShippingBucket;
import com.intershop.oms.test.businessobject.prices.OMSSales;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSOrderState extends OMSBusinessObject
{
    private String shopOrderNumber;

    private OffsetDateTime shopOrderCreationDate;

    private OffsetDateTime shopOrderUpdateDate;

    private Long reservationId;

    private String costCenter;

    private String project;

    private OMSCustomerData customerData;

    private OMSAddressInvoice invoiceAddress;

    private OMSSales sales;

    private OMSPayment payment;

    private String optimizationRule;

    private Boolean splitShipmentAllowed;

    private Map<String, Map<String, String>> additionalAttributes = null;

    private List<OMSShippingBucket> shippingBuckets = new ArrayList<>();

    private String status;

    private List<OMSOrderNote> notes = null;

    private OMSPaymentState paymentState;

    private List<OMSOrderDocument> documents = null;

    public OMSOrderState shopOrderNumber(String shopOrderNumber)
    {
        this.shopOrderNumber = shopOrderNumber;
        return this;
    }

    public OMSOrderState shopOrderCreationDate(OffsetDateTime shopOrderCreationDate)
    {
        this.shopOrderCreationDate = shopOrderCreationDate;
        return this;
    }

    public OMSOrderState shopOrderUpdateDate(OffsetDateTime shopOrderUpdateDate)
    {
        this.shopOrderUpdateDate = shopOrderUpdateDate;
        return this;
    }

    public OMSOrderState reservationId(Long reservationId)
    {
        this.reservationId = reservationId;
        return this;
    }

    public OMSOrderState costCenter(String costCenter)
    {
        this.costCenter = costCenter;
        return this;
    }
    public OMSOrderState project(String project)
    {
        this.project = project;
        return this;
    }

    public OMSOrderState customerData(OMSCustomerData customerData)
    {
        this.customerData = customerData;
        return this;
    }

    public OMSOrderState invoiceAddress(OMSAddressInvoice invoiceAddress)
    {
        this.invoiceAddress = invoiceAddress;
        return this;
    }

    public OMSOrderState sales(OMSSales sales)
    {
        this.sales = sales;
        return this;
    }

    public OMSOrderState payment(OMSPayment payment)
    {
        this.payment = payment;
        return this;
    }

    public OMSOrderState optimizationRule(String optimizationRule)
    {
        this.optimizationRule = optimizationRule;
        return this;
    }

    public OMSOrderState splitShipmentAllowed(Boolean splitShipmentAllowed)
    {
        this.splitShipmentAllowed = splitShipmentAllowed;
        return this;
    }

    public OMSOrderState additionalAttributes(Map<String, Map<String, String>> additionalAttributes)
    {
        this.additionalAttributes = additionalAttributes;
        return this;
    }

    public OMSOrderState putAdditionalAttributesItem(String key, Map<String, String> additionalAttributesItem)
    {
        if (this.additionalAttributes == null)
        {
            this.additionalAttributes = new HashMap<>();
        }
        this.additionalAttributes.put(key, additionalAttributesItem);
        return this;
    }

    public OMSOrderState shippingBuckets(List<OMSShippingBucket> shippingBuckets)
    {
        this.shippingBuckets = shippingBuckets;
        return this;
    }

    public OMSOrderState addShippingBucketsItem(OMSShippingBucket shippingBucketsItem)
    {
        this.shippingBuckets.add(shippingBucketsItem);
        return this;
    }

    public OMSOrderState status(String status)
    {
        this.status = status;
        return this;
    }

    public OMSOrderState notes(List<OMSOrderNote> notes)
    {
        this.notes = notes;
        return this;
    }

    public OMSOrderState addNotesItem(OMSOrderNote notesItem)
    {
        if (this.notes == null)
        {
            this.notes = new ArrayList<>();
        }
        this.notes.add(notesItem);
        return this;
    }

    public OMSOrderState paymentState(OMSPaymentState paymentState)
    {
        this.paymentState = paymentState;
        return this;
    }

    public OMSOrderState documents(List<OMSOrderDocument> documents)
    {
        this.documents = documents;
        return this;
    }

    public OMSOrderState addDocumentsItem(OMSOrderDocument documentsItem)
    {
        if (this.documents == null)
        {
            this.documents = new ArrayList<>();
        }
        this.documents.add(documentsItem);
        return this;
    }

    @Override
    public boolean equals(java.lang.Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        OMSOrderState orderState = (OMSOrderState) o;
        return Objects.equals(this.shopOrderNumber, orderState.shopOrderNumber) &&
                        Objects.equals(this.shopOrderCreationDate, orderState.shopOrderCreationDate) &&
                        Objects.equals(this.shopOrderUpdateDate, orderState.shopOrderUpdateDate) &&
                        Objects.equals(this.reservationId, orderState.reservationId) &&
                        Objects.equals(this.costCenter, orderState.costCenter) &&
                        Objects.equals(this.project, orderState.project) &&
                        Objects.equals(this.customerData, orderState.customerData) &&
                        Objects.equals(this.invoiceAddress, orderState.invoiceAddress) &&
                        Objects.equals(this.sales, orderState.sales) &&
                        Objects.equals(this.payment, orderState.payment) &&
                        Objects.equals(this.optimizationRule, orderState.optimizationRule) &&
                        Objects.equals(this.splitShipmentAllowed, orderState.splitShipmentAllowed) &&
                        Objects.equals(this.additionalAttributes, orderState.additionalAttributes) &&
                        Objects.equals(this.shippingBuckets, orderState.shippingBuckets) &&
                        Objects.equals(this.status, orderState.status) &&
                        Objects.equals(this.notes, orderState.notes) &&
                        Objects.equals(this.paymentState, orderState.paymentState) &&
                        Objects.equals(this.documents, orderState.documents);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(shopOrderNumber, shopOrderCreationDate, shopOrderUpdateDate, reservationId, costCenter, project, customerData, invoiceAddress, sales, payment, optimizationRule, splitShipmentAllowed, additionalAttributes, shippingBuckets, status, notes, paymentState, documents);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrderState {\n");
        sb.append("    shopOrderNumber: ").append(toIndentedString(shopOrderNumber)).append("\n");
        sb.append("    shopOrderCreationDate: ").append(toIndentedString(shopOrderCreationDate)).append("\n");
        sb.append("    shopOrderUpdateDate: ").append(toIndentedString(shopOrderUpdateDate)).append("\n");
        sb.append("    reservationId: ").append(toIndentedString(reservationId)).append("\n");
        sb.append("    costCenter: ").append(toIndentedString(costCenter)).append("\n");
        sb.append("    project: ").append(toIndentedString(project)).append("\n");
        sb.append("    customerData: ").append(toIndentedString(customerData)).append("\n");
        sb.append("    invoiceAddress: ").append(toIndentedString(invoiceAddress)).append("\n");
        sb.append("    sales: ").append(toIndentedString(sales)).append("\n");
        sb.append("    payment: ").append(toIndentedString(payment)).append("\n");
        sb.append("    optimizationRule: ").append(toIndentedString(optimizationRule)).append("\n");
        sb.append("    splitShipmentAllowed: ").append(toIndentedString(splitShipmentAllowed)).append("\n");
        sb.append("    additionalAttributes: ").append(toIndentedString(additionalAttributes)).append("\n");
        sb.append("    shippingBuckets: ").append(toIndentedString(shippingBuckets)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    notes: ").append(toIndentedString(notes)).append("\n");
        sb.append("    paymentState: ").append(toIndentedString(paymentState)).append("\n");
        sb.append("    documents: ").append(toIndentedString(documents)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
