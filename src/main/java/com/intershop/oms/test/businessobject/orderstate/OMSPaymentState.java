package com.intershop.oms.test.businessobject.orderstate;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public OMSPaymentState invoicingDebitAmount(BigDecimal invoicingDebitAmount)
    {
        this.invoicingDebitAmount = invoicingDebitAmount;
        return this;
    }

    public OMSPaymentState invoicingCreditAmount(BigDecimal invoicingCreditAmount)
    {
        this.invoicingCreditAmount = invoicingCreditAmount;
        return this;
    }

    public OMSPaymentState chargedDebitAmount(BigDecimal chargedDebitAmount)
    {
        this.chargedDebitAmount = chargedDebitAmount;
        return this;
    }

    public OMSPaymentState chargedCreditAmount(BigDecimal chargedCreditAmount)
    {
        this.chargedCreditAmount = chargedCreditAmount;
        return this;
    }

    public OMSPaymentState chargedOpenAmount(BigDecimal chargedOpenAmount)
    {
        this.chargedOpenAmount = chargedOpenAmount;
        return this;
    }

    public OMSPaymentState bookedDebitAmount(BigDecimal bookedDebitAmount)
    {
        this.bookedDebitAmount = bookedDebitAmount;
        return this;
    }

    public OMSPaymentState bookedCreditAmount(BigDecimal bookedCreditAmount)
    {
        this.bookedCreditAmount = bookedCreditAmount;
        return this;
    }

    public OMSPaymentState bookedOpenAmount(BigDecimal bookedOpenAmount)
    {
        this.bookedOpenAmount = bookedOpenAmount;
        return this;
    }

    public OMSPaymentState creationDate(OffsetDateTime creationDate)
    {
        this.creationDate = creationDate;
        return this;
    }

    public OMSPaymentState lastCaptureDate(OffsetDateTime lastCaptureDate)
    {
        this.lastCaptureDate = lastCaptureDate;
        return this;
    }

    public OMSPaymentState lastRefundDate(OffsetDateTime lastRefundDate)
    {
        this.lastRefundDate = lastRefundDate;
        return this;
    }

    public OMSPaymentState lastCalculationDate(OffsetDateTime lastCalculationDate)
    {
        this.lastCalculationDate = lastCalculationDate;
        return this;
    }

    public OMSPaymentState currencyCode(String currencyCode)
    {
        this.currencyCode = currencyCode;
        return this;
    }

    public OMSPaymentState paymentHistory(List<OMSPaymentStateHistoryItem> paymentHistory)
    {
        this.paymentHistory = paymentHistory;
        return this;
    }

    public OMSPaymentState addPaymentHistoryItem(OMSPaymentStateHistoryItem paymentHistoryItem)
    {
        if (this.paymentHistory == null)
        {
            this.paymentHistory = new ArrayList<>();
        }
        this.paymentHistory.add(paymentHistoryItem);
        return this;
    }

    @Override
    public boolean equals(java.lang.Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass()
                        ) {
            return false;
        }
        OMSPaymentState paymentState = (OMSPaymentState) o;
        return Objects.equals(this.invoicingDebitAmount, paymentState.invoicingDebitAmount) &&
                        Objects.equals(this.invoicingCreditAmount, paymentState.invoicingCreditAmount) &&
                        Objects.equals(this.chargedDebitAmount, paymentState.chargedDebitAmount) &&
                        Objects.equals(this.chargedCreditAmount, paymentState.chargedCreditAmount) &&
                        Objects.equals(this.chargedOpenAmount, paymentState.chargedOpenAmount) &&
                        Objects.equals(this.bookedDebitAmount, paymentState.bookedDebitAmount) &&
                        Objects.equals(this.bookedCreditAmount, paymentState.bookedCreditAmount) &&
                        Objects.equals(this.bookedOpenAmount, paymentState.bookedOpenAmount) &&
                        Objects.equals(this.creationDate, paymentState.creationDate) &&
                        Objects.equals(this.lastCaptureDate, paymentState.lastCaptureDate) &&
                        Objects.equals(this.lastRefundDate, paymentState.lastRefundDate) &&
                        Objects.equals(this.lastCalculationDate, paymentState.lastCalculationDate) &&
                        Objects.equals(this.currencyCode, paymentState.currencyCode) &&
                        Objects.equals(this.paymentHistory, paymentState.paymentHistory);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(invoicingDebitAmount, invoicingCreditAmount, chargedDebitAmount, chargedCreditAmount, chargedOpenAmount, bookedDebitAmount, bookedCreditAmount, bookedOpenAmount, creationDate, lastCaptureDate, lastRefundDate, lastCalculationDate, currencyCode, paymentHistory);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PaymentState {\n");
        sb.append("    invoicingDebitAmount: ").append(toIndentedString(invoicingDebitAmount)).append("\n");
        sb.append("    invoicingCreditAmount: ").append(toIndentedString(invoicingCreditAmount)).append("\n");
        sb.append("    chargedDebitAmount: ").append(toIndentedString(chargedDebitAmount)).append("\n");
        sb.append("    chargedCreditAmount: ").append(toIndentedString(chargedCreditAmount)).append("\n");
        sb.append("    chargedOpenAmount: ").append(toIndentedString(chargedOpenAmount)).append("\n");
        sb.append("    bookedDebitAmount: ").append(toIndentedString(bookedDebitAmount)).append("\n");
        sb.append("    bookedCreditAmount: ").append(toIndentedString(bookedCreditAmount)).append("\n");
        sb.append("    bookedOpenAmount: ").append(toIndentedString(bookedOpenAmount)).append("\n");
        sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
        sb.append("    lastCaptureDate: ").append(toIndentedString(lastCaptureDate)).append("\n");
        sb.append("    lastRefundDate: ").append(toIndentedString(lastRefundDate)).append("\n");
        sb.append("    lastCalculationDate: ").append(toIndentedString(lastCalculationDate)).append("\n");
        sb.append("    currencyCode: ").append(toIndentedString(currencyCode)).append("\n");
        sb.append("    paymentHistory: ").append(toIndentedString(paymentHistory)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
