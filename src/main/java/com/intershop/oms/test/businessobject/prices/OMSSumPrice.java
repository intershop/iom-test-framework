package com.intershop.oms.test.businessobject.prices;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSSumPrice extends OMSBusinessObject
{
    private OMSPrice net;

    private OMSPrice gross;

    private List<OMSTax> taxes = new ArrayList<>();

    private List<OMSPromotion> promotions = new ArrayList<>();

    public OMSSumPrice net(OMSPrice net)
    {
        this.net = net;
        return this;
    }

    public OMSSumPrice gross(OMSPrice gross)
    {
        this.gross = gross;
        return this;
    }

    public OMSSumPrice taxes(List<OMSTax> taxes)
    {
        this.taxes = taxes;
        return this;
    }

    public OMSSumPrice addTaxesItem(OMSTax taxesItem)
    {
        taxes.add(taxesItem);
        return this;
    }

    public OMSSumPrice promotions(List<OMSPromotion> promotions)
    {
        this.promotions = promotions;
        return this;
    }

    public OMSSumPrice addPromotionsItem(OMSPromotion promotionsItem)
    {
        promotions.add(promotionsItem);
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
        OMSSumPrice sumPrice = (OMSSumPrice) o;
        return  Objects.equals(this.net, sumPrice.net) &&
                Objects.equals(this.gross, sumPrice.gross) &&
                Objects.equals(this.taxes, sumPrice.taxes) &&
                Objects.equals(this.promotions, sumPrice.promotions);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(net, gross, taxes, promotions);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class SumPrice {\n");
        sb.append("    net: ").append(toIndentedString(net)).append("\n");
        sb.append("    gross: ").append(toIndentedString(gross)).append("\n");
        sb.append("    taxes: ").append(toIndentedString(taxes)).append("\n");
        sb.append("    promotions: ").append(toIndentedString(promotions)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
