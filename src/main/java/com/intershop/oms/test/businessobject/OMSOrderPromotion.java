package com.intershop.oms.test.businessobject;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.intershop.oms.test.businessobject.prices.OMSPromotion;
import com.intershop.oms.test.businessobject.prices.OMSPromotion.OMSPromotionValueTypeEnum;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Accessors(chain = true)
public class OMSOrderPromotion
{
    String id                   = null;
    String budgetSourceID       = "budgetSourceID";
    String descriptorId         = null;
    String name                 = null;
    BigDecimal netValue;
    BigDecimal grossValue;
    BigDecimal percentageValue;
    BigDecimal fixedValue;

    public OMSOrderPromotion(String anId, BigDecimal aNetValue, BigDecimal aGrossValue, BigDecimal aPercentageValue, BigDecimal aFixValue)
    {
        id              = anId;
        name            = anId;
        descriptorId    = anId;
        netValue        = aNetValue.setScale( 2, RoundingMode.HALF_UP );
        grossValue      = aGrossValue.setScale( 2, RoundingMode.HALF_UP );
        if (null != aPercentageValue)
        {
            percentageValue = aPercentageValue.setScale( 2, RoundingMode.HALF_UP);
        }
        if (null != aFixValue)
        {
            fixedValue      = aFixValue.setScale( 2, RoundingMode.HALF_UP );
        }
    }

    public BigDecimal getTaxValue()
    {
        if (getGrossValue() != null && getNetValue() != null)
        {
            return getGrossValue().subtract(getNetValue());
        }
        else
        {
            return null;
        }
    }

    public OMSPromotion getOMSPromotion()
    {
        OMSPromotion omsPromotion = new OMSPromotion();
        if (percentageValue != null)
        {
            omsPromotion.setPromotionValueType(OMSPromotionValueTypeEnum.PERCENTAGE);
            omsPromotion.setPromotionValue(percentageValue);
        }
        else if (fixedValue != null)
        {
            omsPromotion.setPromotionValueType(OMSPromotionValueTypeEnum.FIXED);
            omsPromotion.setPromotionValue(fixedValue);
        }
        omsPromotion.setBudgetSourceId("PromoTestBudgetSourceId");
        omsPromotion.setCode("PromoTestCode");
        omsPromotion.setDescriptorId(descriptorId);
        omsPromotion.setNetValue(netValue);
        omsPromotion.setGrossValue(grossValue);
        omsPromotion.setId(budgetSourceID);
        omsPromotion.setName(name);

        return omsPromotion;
    }
}