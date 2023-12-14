package com.intershop.oms.test.businessobject.orderstate;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
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

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentStateHistoryItem amount(BigDecimal amount)
    {
        return setAmount(amount);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentStateHistoryItem currencyCode(String currencyCode)
    {
        return setCurrencyCode(currencyCode);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentStateHistoryItem type(String type)
    {
        return setType(type);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentStateHistoryItem action(String action)
    {
        return setAction(action);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentStateHistoryItem paymentMethod(String paymentMethod)
    {
        return setPaymentMethod(paymentMethod);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentStateHistoryItem creationDate(OffsetDateTime creationDate)
    {
        return setCreationDate(creationDate);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentStateHistoryItem modificationDate(OffsetDateTime modificationDate)
    {
        return setModificationDate(modificationDate);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentStateHistoryItem comment(String comment)
    {
        return setComment(comment);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentStateHistoryItem invoiceNumber(String invoiceNumber)
    {
        return setInvoiceNumber(invoiceNumber);
    }
}
