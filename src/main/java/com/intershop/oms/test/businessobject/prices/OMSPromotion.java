package com.intershop.oms.test.businessobject.prices;

import java.math.BigDecimal;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

    public OMSPromotion id(String id)
    {
        this.id = id;
        return this;
    }

    public OMSPromotion promotionValueType(OMSPromotionValueTypeEnum promotionValueType)
    {
        this.promotionValueType = promotionValueType;
        return this;
      }

    public OMSPromotion promotionValue(BigDecimal promotionValue)
    {
        this.promotionValue = promotionValue;
        return this;
    }

    public OMSPromotion name(String name)
    {
        this.name = name;
        return this;
    }

    public OMSPromotion descriptorId(String descriptorId)
    {
        this.descriptorId = descriptorId;
        return this;
    }

    public OMSPromotion code(String code)
    {
        this.code = code;
        return this;
    }

    public OMSPromotion budgetSourceId(String budgetSourceId)
    {
        this.budgetSourceId = budgetSourceId;
        return this;
    }

    public OMSPromotion netValue(BigDecimal netValue)
    {
        this.netValue = netValue;
        return this;
    }

    public OMSPromotion grossValue(BigDecimal grossValue)
    {
        this.grossValue = grossValue;
        return this;
    }

    public OMSPromotion netValue(String amount)
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

    public OMSPromotion grossValue(String amount)
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

    public OMSPromotion promotionValue(String amount)
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
      OMSPromotion promotion = (OMSPromotion) o;
      return Objects.equals(this.promotionValueType, promotion.promotionValueType) &&
          Objects.equals(this.promotionValue, promotion.promotionValue) &&
          Objects.equals(this.id, promotion.id) &&
          Objects.equals(this.name, promotion.name) &&
          Objects.equals(this.descriptorId, promotion.descriptorId) &&
          Objects.equals(this.code, promotion.code) &&
          Objects.equals(this.budgetSourceId, promotion.budgetSourceId) &&
          Objects.equals(this.netValue, promotion.netValue) &&
          Objects.equals(this.grossValue, promotion.grossValue);
    }

    @Override
    public int hashCode()
    {
      return Objects.hash(promotionValueType, promotionValue, id, name, descriptorId, code, budgetSourceId, netValue, grossValue);
    }

    @Override
    public String toString()
    {
      StringBuilder sb = new StringBuilder();
      sb.append("class Promotion {\n");
      sb.append("    promotionValueType: ").append(toIndentedString(promotionValueType)).append("\n");
      sb.append("    promotionValue: ").append(toIndentedString(promotionValue)).append("\n");
      sb.append("    id: ").append(toIndentedString(id)).append("\n");
      sb.append("    name: ").append(toIndentedString(name)).append("\n");
      sb.append("    descriptorId: ").append(toIndentedString(descriptorId)).append("\n");
      sb.append("    code: ").append(toIndentedString(code)).append("\n");
      sb.append("    budgetSourceId: ").append(toIndentedString(budgetSourceId)).append("\n");
      sb.append("    netValue: ").append(toIndentedString(netValue)).append("\n");
      sb.append("    grossValue: ").append(toIndentedString(grossValue)).append("\n");
      sb.append("}");
      return sb.toString();
    }
}
