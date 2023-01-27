package com.intershop.oms.test.businessobject.prices;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSSales extends OMSBusinessObject
{
    private String currencyCode;

    private OMSSumPrice subTotal;

    private List<OMSCharge> charges = new ArrayList<>();

    private OMSTotalPrice total;

    public OMSSales currencyCode(String currencyCode)
    {
        this.currencyCode = currencyCode;
        return this;
    }

    public OMSSales subTotal(OMSSumPrice subTotal)
    {
        this.subTotal = subTotal;
        return this;
    }

    public OMSSales charges(List<OMSCharge> charges)
    {
        this.charges = charges;
        return this;
    }

    public OMSSales addChargesItem(OMSCharge chargesItem)
    {
        this.charges.add(chargesItem);
        return this;
    }

    public OMSSales total(OMSTotalPrice total)
    {
        this.total = total;
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
        OMSSales sales = (OMSSales) o;
        return Objects.equals(this.currencyCode, sales.currencyCode) &&
                        Objects.equals(this.subTotal, sales.subTotal) &&
                        Objects.equals(this.charges, sales.charges) &&
                        Objects.equals(this.total, sales.total);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(currencyCode, subTotal, charges, total);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class Sales {\n");
        sb.append("    currencyCode: ").append(toIndentedString(currencyCode)).append("\n");
        sb.append("    subTotal: ").append(toIndentedString(subTotal)).append("\n");
        sb.append("    charges: ").append(toIndentedString(charges)).append("\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * @return a list of all the used taxes within these sales
     */
    public List<OMSTax> getUsedTaxes()
    {
        List<OMSTax> taxes = new ArrayList<>();
        if (total != null)
        {
            taxes.addAll(total.getTaxes());
        }
        if (subTotal != null)
        {
            taxes.addAll(subTotal.getTaxes());
        }
        if (charges != null)
        {
            charges.forEach(ch -> taxes.addAll(ch.getTaxes()));
        }
        return taxes;
    }
}
