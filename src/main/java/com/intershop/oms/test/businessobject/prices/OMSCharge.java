package com.intershop.oms.test.businessobject.prices;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSCharge extends OMSBusinessObject
{
    private Long number;

    private String type;

    private OMSPrice net;

    private OMSPrice gross;

    private List<OMSTax> taxes = new ArrayList<>();

    private List<OMSPromotion> promotions = null;

    public OMSCharge number(Long number)
    {
        this.number = number;
        return this;
    }

    public OMSCharge type(String type)
    {
        this.type = type;
        return this;
    }

    public OMSCharge net(OMSPrice net)
    {
        this.net = net;
        return this;
    }

    public OMSCharge gross(OMSPrice gross)
    {
        this.gross = gross;
        return this;
    }

    public OMSCharge taxes(List<OMSTax> taxes)
    {
        this.taxes = taxes;
        return this;
    }

    public OMSCharge addTaxesItem(OMSTax taxesItem)
    {
        this.taxes.add(taxesItem);
        return this;
    }

    public OMSCharge promotions(List<OMSPromotion> promotions)
    {
        this.promotions = promotions;
        return this;
    }

    public OMSCharge addPromotionsItem(OMSPromotion promotionsItem)
    {
        if (this.promotions == null)
        {
            this.promotions = new ArrayList<>();
        }
        this.promotions.add(promotionsItem);
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
        OMSCharge charge = (OMSCharge) o;
        return Objects.equals(this.number, charge.number) &&
                        Objects.equals(this.type, charge.type) &&
                        Objects.equals(this.net, charge.net) &&
                        Objects.equals(this.gross, charge.gross) &&
                        Objects.equals(this.taxes, charge.taxes) &&
                        Objects.equals(this.promotions, charge.promotions);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(number, type, net, gross, taxes, promotions);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class Charge {\n");
        sb.append("    number: ").append(toIndentedString(number)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    net: ").append(toIndentedString(net)).append("\n");
        sb.append("    gross: ").append(toIndentedString(gross)).append("\n");
        sb.append("    taxes: ").append(toIndentedString(taxes)).append("\n");
        sb.append("    promotions: ").append(toIndentedString(promotions)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
