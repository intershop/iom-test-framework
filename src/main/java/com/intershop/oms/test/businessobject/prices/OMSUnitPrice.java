package com.intershop.oms.test.businessobject.prices;

import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSUnitPrice extends OMSBusinessObject
{
    private OMSListPrice net;

    private OMSListPrice gross;

    public OMSUnitPrice net(OMSListPrice net)
    {
        this.net = net;
        return this;
    }

    public OMSUnitPrice gross(OMSListPrice gross)
    {
        this.gross = gross;
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
        OMSUnitPrice unitPrice = (OMSUnitPrice) o;
        return Objects.equals(this.net, unitPrice.net) &&
                        Objects.equals(this.gross, unitPrice.gross);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(net, gross);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class UnitPrice {\n");
        sb.append("    net: ").append(toIndentedString(net)).append("\n");
        sb.append("    gross: ").append(toIndentedString(gross)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}