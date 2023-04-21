package com.intershop.oms.test.businessobject.prices;

import java.math.BigDecimal;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true, fluent = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSPromotion extends OMSBusinessObject
{
    public enum OMSPromotionValueTypeEnum
    {
        PERCENTAGE("PERCENTAGE"),
        FIXED("FIXED");

        private String value;

        OMSPromotionValueTypeEnum(String value)
        {
            this.value = value;
        }

        public String getValue()
        {
            return value;
        }

        @Override
        public String toString()
        {
            return String.valueOf(value);
        }

        public static OMSPromotionValueTypeEnum fromValue(String value)
        {
            for (OMSPromotionValueTypeEnum b : OMSPromotionValueTypeEnum.values())
            {
                if (b.value.equals(value))
                {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    private OMSPromotionValueTypeEnum promotionValueType;

    private BigDecimal promotionValue = null;

    private String id;

    private String name;

    private String descriptorId;

    private String code;

    private String budgetSourceId;

    private BigDecimal netValue = null;

    private BigDecimal grossValue = null;

    public OMSPromotion setNetValue(String amount)
    {
        if (amount != null)
        {
            return netValue(new BigDecimal(amount));
        }
        else
        {
            return netValue((BigDecimal)null);
        }
    }

    public OMSPromotion setGrossValue(String amount)
    {
        if (amount != null)
        {
            return grossValue(new BigDecimal(amount));
        }
        else
        {
            return grossValue((BigDecimal)null);
        }
    }

    public OMSPromotion setPromotionValue(String amount)
    {
        if (amount != null)
        {
            return promotionValue(new BigDecimal(amount));
        }
        else
        {
            return promotionValue((BigDecimal)null);
        }
    }
}
