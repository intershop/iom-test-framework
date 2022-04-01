package com.intershop.oms.test.businessobject.orderstate;

import java.net.URI;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSOrderPositionDispatchedCarrierTracking extends OMSBusinessObject
{
    private String number;

    private URI url;

    public OMSOrderPositionDispatchedCarrierTracking number(String number)
    {
      this.number = number;
      return this;
    }

    public OMSOrderPositionDispatchedCarrierTracking url(URI url)
    {
      this.url = url;
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
      OMSOrderPositionDispatchedCarrierTracking oMSOrderPositionDispatchedCarrierTracking = (OMSOrderPositionDispatchedCarrierTracking) o;
      return Objects.equals(this.number, oMSOrderPositionDispatchedCarrierTracking.number) && Objects.equals(this.url, oMSOrderPositionDispatchedCarrierTracking.url);
    }

    @Override
    public int hashCode()
    {
      return Objects.hash(number, url);
    }

    @Override
    public String toString()
    {
      StringBuilder sb = new StringBuilder();
      sb.append("class OMSOrderPositionDispatchedCarrierTracking {\n");
      sb.append("    number: ").append(toIndentedString(number)).append("\n");
      sb.append("    url: ").append(toIndentedString(url)).append("\n");
      sb.append("}");
      return sb.toString();
    }
}
