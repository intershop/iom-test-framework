
package com.intershop.oms.test.businessobject.orderstate;

import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSOrderPositionConfirmedCancelledReason extends OMSBusinessObject
{
    private String id;

    private String value;

    public OMSOrderPositionConfirmedCancelledReason id(String id)
    {
        this.id = id;
        return this;
    }

    public OMSOrderPositionConfirmedCancelledReason value(String value)
    {
        this.value = value;
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
        OMSOrderPositionConfirmedCancelledReason orderPositionConfirmedCancelledReason = (OMSOrderPositionConfirmedCancelledReason) o;
        return Objects.equals(this.id, orderPositionConfirmedCancelledReason.id) && Objects.equals(this.value, orderPositionConfirmedCancelledReason.value);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, value);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrderPositionConfirmedCancelledReason {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
