package com.intershop.oms.test.businessobject.rma;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSWriteReturnRequest extends OMSBusinessObject
{
    /**
     * The type that specifies the return request.
     */
    @JsonAdapter(TypeEnum.Adapter.class)
    public enum TypeEnum {
        RETURN("RETURN"),

        PICKUP("PICKUP");

        private String value;

        TypeEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }

        public static TypeEnum fromValue(String value) {
            for (TypeEnum b : TypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }

        public static class Adapter extends TypeAdapter<TypeEnum> {
            @Override
            public void write(final JsonWriter jsonWriter, final TypeEnum enumeration) throws IOException {
                jsonWriter.value(enumeration.getValue());
            }

            @Override
            public TypeEnum read(final JsonReader jsonReader) throws IOException {
                String value = jsonReader.nextString();
                return TypeEnum.fromValue(value);
            }
        }
    }

    private TypeEnum type;

    private String rmaNumber;

    private String comment;

    private List<OMSWriteReturnRequestPosition> positions = new ArrayList<>();

    private OMSWritePickupAddress pickupAddress;

    private List<OMSWriteCustomAttribute> customAttributes = null;

    public OMSWriteReturnRequest(String shopOrderNo, Collection<OMSWriteReturnRequestPosition> returnRequestPositions)
    {
        setType(OMSWriteReturnRequest.TypeEnum.PICKUP);
        setComment("Testing Example Comment");
        setCustomAttributes(Arrays.asList(new OMSWriteCustomAttribute()));
        setPickupAddress(new OMSWritePickupAddress());
        setRmaNumber(buildRMANumber(shopOrderNo));
        for (OMSWriteReturnRequestPosition returnRequestpos : returnRequestPositions)
        {
            addPositionsItem(returnRequestpos);
        }
    }

    public OMSWriteReturnRequest()
    {
    }

    public OMSWriteReturnRequest type(TypeEnum type)
    {
        this.type = type;
        return this;
    }

    public OMSWriteReturnRequest rmaNumber(String rmaNumber)
    {
        this.rmaNumber = rmaNumber;
        return this;
    }

    public OMSWriteReturnRequest comment(String comment)
    {
        this.comment = comment;
        return this;
    }

    public OMSWriteReturnRequest positions(List<OMSWriteReturnRequestPosition> positions)
    {
        this.positions = positions;
        return this;
    }

    public OMSWriteReturnRequest addPositionsItem(OMSWriteReturnRequestPosition positionsItem)
    {
        this.positions.add(positionsItem);
        return this;
    }

    public OMSWriteReturnRequest pickupAddress(OMSWritePickupAddress pickupAddress)
    {
        this.pickupAddress = pickupAddress;
        return this;
    }

    public OMSWriteReturnRequest customAttributes(List<OMSWriteCustomAttribute> customAttributes)
    {
        this.customAttributes = customAttributes;
        return this;
    }

    public OMSWriteReturnRequest addCustomAttributesItem(OMSWriteCustomAttribute customAttributesItem)
    {
        if (this.customAttributes == null)
        {
            this.customAttributes = new ArrayList<>();
        }
        this.customAttributes.add(customAttributesItem);
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
        OMSWriteReturnRequest writeReturnRequest = (OMSWriteReturnRequest) o;
        return Objects.equals(this.type, writeReturnRequest.type) &&
                        Objects.equals(this.rmaNumber, writeReturnRequest.rmaNumber) &&
                        Objects.equals(this.comment, writeReturnRequest.comment) &&
                        Objects.equals(this.positions, writeReturnRequest.positions) &&
                        Objects.equals(this.pickupAddress, writeReturnRequest.pickupAddress) &&
                        Objects.equals(this.customAttributes, writeReturnRequest.customAttributes);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(type, rmaNumber, comment, positions, pickupAddress, customAttributes);
    }


    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OMSWriteReturnRequest {\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    rmaNumber: ").append(toIndentedString(rmaNumber)).append("\n");
        sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
        sb.append("    positions: ").append(toIndentedString(positions)).append("\n");
        sb.append("    pickupAddress: ").append(toIndentedString(pickupAddress)).append("\n");
        sb.append("    customAttributes: ").append(toIndentedString(customAttributes)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    public static String buildRMANumber(String shopOrderNo)
    {
        String rmaNumber = "RMA_" + shopOrderNo;

        // RMANumber may only be 50 chars long
        if (rmaNumber.length() > 50 && shopOrderNo.startsWith("TestOrder_"))
        {
            rmaNumber = shopOrderNo.replaceAll("TestOrder", "RMA");
        }

        // RMANumber may only be 50 chars long
        if (rmaNumber.length() > 50)
        {
            MessageDigest md;
            try
            {
                md = MessageDigest.getInstance("MD5");
            }
            catch (NoSuchAlgorithmException e)
            {
                throw new RuntimeException("MD5 Hashing is not supported! :-(", e);
            }
            byte[] hashInBytes = md.digest( shopOrderNo.getBytes(StandardCharsets.UTF_8) );

            StringBuilder sb = new StringBuilder();
            for (byte b : hashInBytes)
            {
                sb.append(String.format("%02x", b));
            }
            rmaNumber = "RMA_" + sb.toString();
        }
        return rmaNumber;
    }
}