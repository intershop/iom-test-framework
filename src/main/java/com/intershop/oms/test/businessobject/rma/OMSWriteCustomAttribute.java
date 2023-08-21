package com.intershop.oms.test.businessobject.rma;

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
public class OMSWriteCustomAttribute extends OMSBusinessObject
{
    private String key = "Example Test Key";

    private String value = "Example Test Value";

    /**
     * @deprecated use setKey()
     */
    @Deprecated(forRemoval = true, since= "6.0.0")
    public OMSWriteCustomAttribute key(String key)
    {
      this.key = key;
      return this;
    }

    /**
     * @deprecated use setKey()
     */
    @Deprecated(forRemoval = true, since= "6.0.0")
    public OMSWriteCustomAttribute value(String value)
    {
      this.value = value;
      return this;
    }
}