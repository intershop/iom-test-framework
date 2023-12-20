package com.intershop.oms.test.businessobject.rma;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

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
public class OMSWriteReturnRequest extends OMSBusinessObject
{
    /**
     * The type that specifies the return request.
     */
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
    }

    private TypeEnum type;

    private String rmaNumber;

    private String comment;

    private List<OMSWriteReturnRequestPosition> positions = new ArrayList<>();

    private OMSWritePickupAddress pickupAddress;

    private List<OMSContactPerson> contactPersons = new ArrayList<>();
    
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

    public OMSWriteReturnRequest() {}

    public OMSWriteReturnRequest addCustomAttributesItem(OMSWriteCustomAttribute customAttributesItem)
    {
        if (customAttributes == null)
        {
            customAttributes = new ArrayList<>();
        }
        customAttributes.add(customAttributesItem);
        return this;
    }

    public OMSWriteReturnRequest addPositionsItem(OMSWriteReturnRequestPosition positionsItem)
    {
        if (positions == null)
        {
            positions = new ArrayList<>();
        }
        positions.add(positionsItem);
        return this;
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSWriteReturnRequest type(TypeEnum type)
    {
        return setType(type);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSWriteReturnRequest rmaNumber(String rmaNumber)
    {
        return setRmaNumber(rmaNumber);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSWriteReturnRequest comment(String comment)
    {
        return setComment(comment);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSWriteReturnRequest positions(List<OMSWriteReturnRequestPosition> positions)
    {
        return setPositions(positions);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSWriteReturnRequest pickupAddress(OMSWritePickupAddress pickupAddress)
    {
        return setPickupAddress(pickupAddress);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSWriteReturnRequest customAttributes(List<OMSWriteCustomAttribute> customAttributes)
    {
        return setCustomAttributes(customAttributes);
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