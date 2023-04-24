package com.intershop.oms.test.businessobject.prices;

import java.math.BigDecimal;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.Tolerate;

@Getter
@Setter
@Accessors(chain = true)
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

    @Tolerate
    public OMSPromotion netValue(String amount)
    {
        if (amount != null)
        {
            return setNetValue(new BigDecimal(amount));
        }
        else
        {
            return setNetValue((BigDecimal)null);
        }
    }

    @Tolerate
    public OMSPromotion grossValue(String amount)
    {
        if (amount != null)
        {
            return setGrossValue(new BigDecimal(amount));
        }
        else
        {
            return setGrossValue((BigDecimal)null);
        }
    }

    @Tolerate
    public OMSPromotion promotionValue(String amount)
    {
        if (amount != null)
        {
            return setPromotionValue(new BigDecimal(amount));
        }
        else
        {
            return setPromotionValue((BigDecimal)null);
        }
    }

    @Deprecated(since = "4.5.0", forRemoval = true)
    public OMSPromotion netValue(BigDecimal amount)
    {
        return setNetValue(amount);
    }
    
    @Deprecated(since = "4.5.0", forRemoval = true)
    public OMSPromotion grossValue(BigDecimal amount)
    {
        return setGrossValue(amount);
    }
    
    @Deprecated(since = "4.5.0", forRemoval = true)
    public OMSPromotion promotionValue(BigDecimal amount)
    {
        return setPromotionValue(amount);
    }
}
