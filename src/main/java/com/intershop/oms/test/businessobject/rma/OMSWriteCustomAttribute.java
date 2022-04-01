package com.intershop.oms.test.businessobject.rma;

import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSWriteCustomAttribute extends OMSBusinessObject
{
    private String key;

    private String value;

    public OMSWriteCustomAttribute()
    {
        setKey("Example Test Key");
        setValue("Example Test Value");
    }

    public OMSWriteCustomAttribute key(String key)
    {
      this.key = key;
      return this;
    }

    public OMSWriteCustomAttribute value(String value)
    {
      this.value = value;
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
      OMSWriteCustomAttribute writeCustomAttribute = (OMSWriteCustomAttribute) o;
      return Objects.equals(this.key, writeCustomAttribute.key) &&
          Objects.equals(this.value, writeCustomAttribute.value);
    }

    @Override
    public int hashCode()
    {
      return Objects.hash(key, value);
    }

    @Override
    public String toString()
    {
      StringBuilder sb = new StringBuilder();
      sb.append("class OMSWriteCustomAttribute {\n");
      sb.append("    key: ").append(toIndentedString(key)).append("\n");
      sb.append("    value: ").append(toIndentedString(value)).append("\n");
      sb.append("}");
      return sb.toString();
    }
}