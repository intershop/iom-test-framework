package com.intershop.oms.test.businessobject.prices;

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
public class OMSCharge extends OMSBusinessObject
{
    private Long number;

    private String type;

    private OMSPrice net;

    private OMSPrice gross;

    private List<OMSTax> taxes = new ArrayList<>();

    private List<OMSPromotion> promotions = null;

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSCharge number(Long number)
    {
        return setNumber(number);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSCharge type(String type)
    {
        return setType(type);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSCharge net(OMSPrice net)
    {
        return setNet(net);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSCharge gross(OMSPrice gross)
    {
        return setGross(gross);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSCharge taxes(List<OMSTax> taxes)
    {
        return setTaxes(taxes);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSCharge promotions(List<OMSPromotion> promotions)
    {
        return setPromotions(promotions);
    }

    public OMSCharge addTaxesItem(OMSTax taxesItem)
    {
        taxes.add(taxesItem);
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
}
