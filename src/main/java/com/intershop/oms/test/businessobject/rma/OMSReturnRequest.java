package com.intershop.oms.test.businessobject.rma;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSLink;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSReturnRequest extends OMSBusinessObject
{
    private TypeEnum type;
    private String rmaNumber;
    private String comment;
    private List<OMSReturnRequestPosition> positions = new ArrayList<>();
    private OMSPickupAddress pickupAddress;
    private List<OMSContactPerson> contactPersons = new ArrayList<>();
    private Map<String, String> customAttributes = new HashMap<>();
    private Long id;
    private Date creationDate;
    private String shopOrderNumber;
    private String shopName;
    private String supplierOrderNumber;
    private String supplierName;
    private BusinessStatusEnum businessStatus;
    private StatusEnum status;
    private List<OMSLink> links = new ArrayList<>();

    public enum TypeEnum {
        RETURN("RETURN"),

        PICKUP("PICKUP");

        private final String value;

        TypeEnum(String value) {
            this.value = value;
        }

        public static TypeEnum fromValue(String value) {
            for (TypeEnum b : TypeEnum.values()) {
                if (b.value.equals(value)) {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
    public enum BusinessStatusEnum
    {
        ACCEPTED("ACCEPTED"),

        IN_APPROVAL("IN_APPROVAL"),

        READY_TO_APPROVE("READY_TO_APPROVE"),

        REJECTED("REJECTED"),

        UNKNOWN("UNKNOWN");

        private final String value;

        BusinessStatusEnum(String value)
        {
            this.value = value;
        }

        public static BusinessStatusEnum fromValue(String value)
        {
            for (BusinessStatusEnum b : BusinessStatusEnum.values())
            {
                if (b.value.equals(value))
                {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
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
    }

    public enum StatusEnum
    {
        ACCEPTED("ACCEPTED"),

        CLOSED("CLOSED"),

        DO_APPROVE("DO_APPROVE"),

        DO_CLOSE("DO_CLOSE"),

        INITIAL("INITIAL"),

        REJECTED("REJECTED");

        private final String value;

        StatusEnum(String value)
        {
            this.value = value;
        }

        public static StatusEnum fromValue(String value)
        {
            for (StatusEnum b : StatusEnum.values())
            {
                if (b.value.equals(value))
                {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
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
    }

}
