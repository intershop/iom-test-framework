package com.intershop.oms.test.businessobject.rma;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSLink;

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
public class OMSReadReturnRequest extends OMSBusinessObject
{
    private List<OMSLink> links = null;

    public enum TypeEnum
    {
        RETURN("RETURN"),

        PICKUP("PICKUP");

        private String value;

        TypeEnum(String value)
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

        public static TypeEnum fromValue(String value)
        {
            for (TypeEnum b : TypeEnum.values())
            {
                if (b.value.equals(value))
                {
                    return b;
                }
            }
            throw new IllegalArgumentException("Unexpected value '" + value + "'");
        }
    }

    private TypeEnum type;

    private String rmaNumber;

    private String comment;

    private Long id;

    private Date creationDate;

    private String shopOrderNumber;

    private String shopName;

    private String supplierOrderNumber;

    private String supplierName;

    /**
     * The technical status of the return request.
     */
    public enum StatusEnum
    {
        ACCEPTED("ACCEPTED"),

        CLOSED("CLOSED"),

        DO_APPROVE("DO_APPROVE"),

        DO_CLOSE("DO_CLOSE"),

        INITIAL("INITIAL"),

        REJECTED("REJECTED");

        private String value;

        StatusEnum(String value)
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
    }

    private StatusEnum status;

    /**
     * The business status of the return request.
     */
    public enum BusinessStatusEnum
    {
        ACCEPTED("ACCEPTED"),

        IN_APPROVAL("IN_APPROVAL"),

        READY_TO_APPROVE("READY_TO_APPROVE"),

        REJECTED("REJECTED"),

        UNKNOWN("UNKNOWN");

        private String value;

        BusinessStatusEnum(String value)
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
    }

    private BusinessStatusEnum businessStatus;

    public OMSReadReturnRequest addLinksItem(OMSLink linksItem)
    {
        if (links == null)
        {
            links = new ArrayList<>();
        }
        links.add(linksItem);
        return this;
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSReadReturnRequest links(List<OMSLink> links)
    {
        return setLinks(links);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSReadReturnRequest type(TypeEnum type)
    {
        return setType(type);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSReadReturnRequest rmaNumber(String rmaNumber)
    {
        return setRmaNumber(rmaNumber);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSReadReturnRequest comment(String comment)
    {
        return setComment(comment);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSReadReturnRequest id(Long id)
    {
        return setId(id);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSReadReturnRequest creationDate(Date creationDate)
    {
        return setCreationDate(creationDate);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSReadReturnRequest shopOrderNumber(String shopOrderNumber)
    {
        return setShopOrderNumber(shopOrderNumber);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSReadReturnRequest shopName(String shopName)
    {
        return setShopName(shopName);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSReadReturnRequest supplierOrderNumber(String supplierOrderNumber)
    {
        return setSupplierOrderNumber(supplierOrderNumber);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSReadReturnRequest supplierName(String supplierName)
    {
        return setSupplierName(supplierName);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSReadReturnRequest status(StatusEnum status)
    {
        return setStatus(status);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSReadReturnRequest businessStatus(BusinessStatusEnum businessStatus)
    {
        return setBusinessStatus(businessStatus);
    }
}
