package com.intershop.oms.test.businessobject.prices;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSTotalPrice extends OMSBusinessObject
{
    private OMSPrice net;

    private OMSPrice gross;

    private List<OMSTax> taxes = null;

    public OMSTotalPrice net(OMSPrice net)
    {
        this.net = net;
        return this;
    }

    public OMSTotalPrice gross(OMSPrice gross)
    {
        this.gross = gross;
        return this;
    }

    public OMSTotalPrice taxes(List<OMSTax> taxes)
    {
        this.taxes = taxes;
        return this;
    }

    public OMSTotalPrice addTaxesItem(OMSTax taxesItem)
    {
        if (taxes == null)
        {
            taxes = new ArrayList<>();
        }
        else
        {
            taxes = new ArrayList<>(taxes);
        }
        taxes.add(taxesItem);
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
        OMSTotalPrice totalPrice = (OMSTotalPrice) o;
        return Objects.equals(this.net, totalPrice.net) &&
                        Objects.equals(this.gross, totalPrice.gross) &&
                        Objects.equals(this.taxes, totalPrice.taxes);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(net, gross, taxes);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class TotalPrice {\n");
        sb.append("    net: ").append(toIndentedString(net)).append("\n");
        sb.append("    gross: ").append(toIndentedString(gross)).append("\n");
        sb.append("    taxes: ").append(toIndentedString(taxes)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
