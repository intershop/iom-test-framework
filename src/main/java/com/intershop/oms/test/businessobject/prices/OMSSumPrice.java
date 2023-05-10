package com.intershop.oms.test.businessobject.prices;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
public class OMSSumPrice extends OMSBusinessObject
{
    private OMSPrice net;

    private OMSPrice gross;

    private List<OMSTax> taxes = new ArrayList<>();

    private List<OMSPromotion> promotions = new ArrayList<>();

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSSumPrice net(OMSPrice net)
    {
        return setNet(net);
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSSumPrice gross(OMSPrice gross)
    {
        return setGross(gross);
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSSumPrice taxes(List<OMSTax> taxes)
    {
        return setTaxes(taxes);
    }

    @Deprecated(since = "5.0.0", forRemoval = true)
    public OMSSumPrice promotions(List<OMSPromotion> promotions)
    {
        return setPromotions(promotions);
    }
    
    public OMSSumPrice addTaxesItem(OMSTax taxesItem)
    {
        taxes.add(taxesItem);
        return this;
    }

    public OMSSumPrice addPromotionsItem(OMSPromotion promotionsItem)
    {
        promotions.add(promotionsItem);
        return this;
    }
}
