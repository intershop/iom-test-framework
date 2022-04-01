package com.intershop.oms.test.businessobject.orderstate;

import java.time.OffsetDateTime;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSOrderNote extends OMSBusinessObject
{
    private String message;

    private String type;

    private OffsetDateTime creationDate;

    public OMSOrderNote message(String message)
    {
        this.message = message;
        return this;
    }

    public OMSOrderNote type(String type)
    {
        this.type = type;
        return this;
    }

    public OMSOrderNote creationDate(OffsetDateTime creationDate)
    {
        this.creationDate = creationDate;
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
        OMSOrderNote orderNote = (OMSOrderNote) o;
        return Objects.equals(this.message, orderNote.message) &&
                        Objects.equals(this.type, orderNote.type) &&
                        Objects.equals(this.creationDate, orderNote.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, type, creationDate);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrderNote {\n");
        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
