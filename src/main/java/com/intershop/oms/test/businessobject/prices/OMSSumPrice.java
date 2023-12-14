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
public class OMSSumPrice extends OMSBusinessObject
{
    private OMSPrice net;

    private OMSPrice gross;

    private List<OMSTax> taxes = new ArrayList<>();

    private List<OMSPromotion> promotions = new ArrayList<>();

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
