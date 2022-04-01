package com.intershop.oms.test.businessobject.address;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSAddressLocationPackstation extends OMSAddressLocation
{
    private String userId;

    private String stationNumber;

    public OMSAddressLocationPackstation()
    {
        type = "AddressLocationPackstation";
    }

    public OMSAddressLocationPackstation userId(String userId)
    {
        this.userId = userId;
        return this;
    }

    public OMSAddressLocationPackstation stationNumber(String stationNumber)
    {
        this.stationNumber = stationNumber;
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
        OMSAddressLocationPackstation addressLocationPackstation = (OMSAddressLocationPackstation) o;
        return Objects.equals(this.userId, addressLocationPackstation.userId) &&
                        Objects.equals(this.stationNumber, addressLocationPackstation.stationNumber) &&
                        super.equals(o);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(userId, stationNumber, super.hashCode());
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class AddressLocationPackstation {\n");
        sb.append("    ").append(toIndentedString(super.toString())).append("\n");
        sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
        sb.append("    stationNumber: ").append(toIndentedString(stationNumber)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
