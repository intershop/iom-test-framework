package com.intershop.oms.test.businessobject.orderstate;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSPaymentStateHistoryItem extends OMSBusinessObject
{
    private BigDecimal amount;

    private String currencyCode;

    private String type;

    private String action;

    private String paymentMethod;

    private OffsetDateTime creationDate;

    private OffsetDateTime modificationDate;

    private String comment;

    private String invoiceNumber;

    public OMSPaymentStateHistoryItem amount(BigDecimal amount)
    {
        this.amount = amount;
        return this;
    }

    public OMSPaymentStateHistoryItem currencyCode(String currencyCode)
    {
        this.currencyCode = currencyCode;
        return this;
    }

    public OMSPaymentStateHistoryItem type(String type)
    {
        this.type = type;
        return this;
    }

    public OMSPaymentStateHistoryItem action(String action)
    {
        this.action = action;
        return this;
    }

    public OMSPaymentStateHistoryItem paymentMethod(String paymentMethod)
    {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public OMSPaymentStateHistoryItem creationDate(OffsetDateTime creationDate)
    {
        this.creationDate = creationDate;
        return this;
    }

    public OMSPaymentStateHistoryItem modificationDate(OffsetDateTime modificationDate)
    {
        this.modificationDate = modificationDate;
        return this;
    }

    public OMSPaymentStateHistoryItem comment(String comment)
    {
        this.comment = comment;
        return this;
    }

    public OMSPaymentStateHistoryItem invoiceNumber(String invoiceNumber)
    {
        this.invoiceNumber = invoiceNumber;
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
        OMSPaymentStateHistoryItem OMSPaymentStateHistoryItem = (OMSPaymentStateHistoryItem) o;
        return Objects.equals(this.amount, OMSPaymentStateHistoryItem.amount) &&
                        Objects.equals(this.currencyCode, OMSPaymentStateHistoryItem.currencyCode) &&
                        Objects.equals(this.type, OMSPaymentStateHistoryItem.type) &&
                        Objects.equals(this.action, OMSPaymentStateHistoryItem.action) &&
                        Objects.equals(this.paymentMethod, OMSPaymentStateHistoryItem.paymentMethod) &&
                        Objects.equals(this.creationDate, OMSPaymentStateHistoryItem.creationDate) &&
                        Objects.equals(this.modificationDate, OMSPaymentStateHistoryItem.modificationDate) &&
                        Objects.equals(this.comment, OMSPaymentStateHistoryItem.comment) &&
                        Objects.equals(this.invoiceNumber, OMSPaymentStateHistoryItem.invoiceNumber);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(amount, currencyCode, type, action, paymentMethod, creationDate, modificationDate, comment, invoiceNumber);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OMSPaymentStateHistoryItem {\n");
        sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
        sb.append("    currencyCode: ").append(toIndentedString(currencyCode)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    action: ").append(toIndentedString(action)).append("\n");
        sb.append("    paymentMethod: ").append(toIndentedString(paymentMethod)).append("\n");
        sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
        sb.append("    modificationDate: ").append(toIndentedString(modificationDate)).append("\n");
        sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
        sb.append("    invoiceNumber: ").append(toIndentedString(invoiceNumber)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
