package com.intershop.oms.test.businessobject.orderstate;

import java.time.OffsetDateTime;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSOrderPositionConfirmedCancelled extends OMSBusinessObject
{
    private Integer quantity;

    private OffsetDateTime cancelDate;

    private OMSOrderPositionConfirmedCancelledReason reason;

    public OMSOrderPositionConfirmedCancelled quantity(Integer quantity)
    {
        this.quantity = quantity;
        return this;
    }

    public OMSOrderPositionConfirmedCancelled cancelDate(OffsetDateTime cancelDate)
    {
        this.cancelDate = cancelDate;
        return this;
    }

    public OMSOrderPositionConfirmedCancelled reason(OMSOrderPositionConfirmedCancelledReason reason)
    {
        this.reason = reason;
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
        OMSOrderPositionConfirmedCancelled orderPositionConfirmedCancelled = (OMSOrderPositionConfirmedCancelled) o;
        return Objects.equals(this.quantity, orderPositionConfirmedCancelled.quantity) &&
                        Objects.equals(this.cancelDate, orderPositionConfirmedCancelled.cancelDate) &&
                        Objects.equals(this.reason, orderPositionConfirmedCancelled.reason);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(quantity, cancelDate, reason);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrderPositionConfirmedCancelled {\n");
        sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
        sb.append("    cancelDate: ").append(toIndentedString(cancelDate)).append("\n");
        sb.append("    reason: ").append(toIndentedString(reason)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
