package com.intershop.oms.test.businessobject;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum OMSReservationState
{
    RESERVED("reserved"),

    EXPIRED("expired");

    private String value;

    OMSReservationState(String value)
    {
      this.value = value;
    }

    @JsonValue
    public String getValue()
    {
      return value;
    }

    @Override
    public String toString()
    {
      return String.valueOf(value);
    }

    @JsonCreator
    public static OMSReservationState fromValue(String value)
    {
      for (OMSReservationState b : OMSReservationState.values())
      {
        if (b.value.equals(value))
        {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
}
