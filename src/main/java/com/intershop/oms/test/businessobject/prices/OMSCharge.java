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

    public OMSCharge addTaxesItem(OMSTax taxesItem)
    {
        taxes.add(taxesItem);
        return this;
    }

    public OMSCharge addPromotionsItem(OMSPromotion promotionsItem)
    {
        if (promotions == null)
        {
            promotions = new ArrayList<>();
        }
        promotions.add(promotionsItem);
        return this;
    }
}
