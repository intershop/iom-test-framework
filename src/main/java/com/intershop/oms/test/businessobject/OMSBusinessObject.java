package com.intershop.oms.test.businessobject;

public abstract class OMSBusinessObject
{
    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    protected String toIndentedString(Object o )
    {
      if (o == null)
      {
        return "null";
      }
      return o.toString().replace("\n", "\n    ");
    }

}