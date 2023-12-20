package com.intershop.oms.test.businessobject.orderstate;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

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
public class OMSPaymentState extends OMSBusinessObject
{
    private BigDecimal invoicingDebitAmount;
    private BigDecimal invoicingCreditAmount;
    private BigDecimal chargedDebitAmount;
    private BigDecimal chargedCreditAmount;
    private BigDecimal chargedOpenAmount;
    private BigDecimal bookedDebitAmount;
    private BigDecimal bookedCreditAmount;
    private BigDecimal bookedOpenAmount;
    private OffsetDateTime creationDate;
    private OffsetDateTime lastCaptureDate;
    private OffsetDateTime lastRefundDate;
    private OffsetDateTime lastCalculationDate;
    private String currencyCode;
    private List<OMSPaymentStateHistoryItem> paymentHistory = null;

    public OMSPaymentState addPaymentHistoryItem(OMSPaymentStateHistoryItem paymentHistoryItem)
    {
        if (paymentHistory == null)
        {
            paymentHistory = new ArrayList<>();
        }
        paymentHistory.add(paymentHistoryItem);
        return this;
    }
    
    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentState invoicingDebitAmount(BigDecimal invoicingDebitAmount)
    {
        return setInvoicingDebitAmount(invoicingDebitAmount);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentState invoicingCreditAmount(BigDecimal invoicingCreditAmount)
    {
        return setInvoicingCreditAmount(invoicingCreditAmount);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentState chargedDebitAmount(BigDecimal chargedDebitAmount)
    {
        return setChargedCreditAmount(chargedDebitAmount);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentState chargedCreditAmount(BigDecimal chargedCreditAmount)
    {
        return setChargedCreditAmount(chargedCreditAmount);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentState chargedOpenAmount(BigDecimal chargedOpenAmount)
    {
        return setChargedOpenAmount(chargedOpenAmount);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentState bookedDebitAmount(BigDecimal bookedDebitAmount)
    {
        return setBookedDebitAmount(bookedDebitAmount);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentState bookedCreditAmount(BigDecimal bookedCreditAmount)
    {
        return setBookedCreditAmount(bookedCreditAmount);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentState bookedOpenAmount(BigDecimal bookedOpenAmount)
    {
        return setBookedOpenAmount(bookedOpenAmount);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentState creationDate(OffsetDateTime creationDate)
    {
        return setCreationDate(creationDate);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentState lastCaptureDate(OffsetDateTime lastCaptureDate)
    {
        return setLastCaptureDate(lastCaptureDate);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentState lastRefundDate(OffsetDateTime lastRefundDate)
    {
        return setLastRefundDate(lastRefundDate);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentState lastCalculationDate(OffsetDateTime lastCalculationDate)
    {
        return setLastCalculationDate(lastCalculationDate);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentState currencyCode(String currencyCode)
    {
        return setCurrencyCode(currencyCode);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSPaymentState paymentHistory(List<OMSPaymentStateHistoryItem> paymentHistory)
    {
        return setPaymentHistory(paymentHistory);
    }
}
