package com.intershop.oms.test.businessobject.rma;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSLink;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

        public static class Adapter extends TypeAdapter<TypeEnum>
        {
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

        public static class Adapter extends TypeAdapter<StatusEnum>
        {
            @Override
            public void write(final JsonWriter jsonWriter, final StatusEnum enumeration) throws IOException
            {
                jsonWriter.value(enumeration.getValue());
            }

            @Override
            public StatusEnum read(final JsonReader jsonReader) throws IOException
            {
                String value = jsonReader.nextString();
                return StatusEnum.fromValue(value);
            }
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

        public static class Adapter extends TypeAdapter<BusinessStatusEnum>
        {
            @Override
            public void write(final JsonWriter jsonWriter, final BusinessStatusEnum enumeration) throws IOException
            {
                jsonWriter.value(enumeration.getValue());
            }

            @Override
            public BusinessStatusEnum read(final JsonReader jsonReader) throws IOException
            {
                String value = jsonReader.nextString();
                return BusinessStatusEnum.fromValue(value);
            }
        }
    }

    private BusinessStatusEnum businessStatus;

    public OMSReadReturnRequest links(List<OMSLink> links)
    {
        this.links = links;
        return this;
    }

    public OMSReadReturnRequest addLinksItem(OMSLink linksItem)
    {
        if (this.links == null)
        {
            this.links = new ArrayList<>();
        }
        this.links.add(linksItem);
        return this;
    }

    public OMSReadReturnRequest type(TypeEnum type)
    {
        this.type = type;
        return this;
    }

    public OMSReadReturnRequest rmaNumber(String rmaNumber)
    {
        this.rmaNumber = rmaNumber;
        return this;
    }

    public OMSReadReturnRequest comment(String comment)
    {
        this.comment = comment;
        return this;
    }

    public OMSReadReturnRequest id(Long id)
    {
        this.id = id;
        return this;
    }

    public OMSReadReturnRequest creationDate(Date creationDate)
    {
        this.creationDate = creationDate;
        return this;
    }

    public OMSReadReturnRequest shopOrderNumber(String shopOrderNumber)
    {
        this.shopOrderNumber = shopOrderNumber;
        return this;
    }

    public OMSReadReturnRequest shopName(String shopName)
    {
        this.shopName = shopName;
        return this;
    }

    public OMSReadReturnRequest supplierOrderNumber(String supplierOrderNumber)
    {
        this.supplierOrderNumber = supplierOrderNumber;
        return this;
    }

    public OMSReadReturnRequest supplierName(String supplierName)
    {
        this.supplierName = supplierName;
        return this;
    }

    public OMSReadReturnRequest status(StatusEnum status)
    {
        this.status = status;
        return this;
    }

    public OMSReadReturnRequest businessStatus(BusinessStatusEnum businessStatus)
    {
        this.businessStatus = businessStatus;
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
        OMSReadReturnRequest readReturnRequest = (OMSReadReturnRequest) o;
        return Objects.equals(this.links, readReturnRequest.links) &&
                        Objects.equals(this.type, readReturnRequest.type) &&
                        Objects.equals(this.rmaNumber, readReturnRequest.rmaNumber) &&
                        Objects.equals(this.comment, readReturnRequest.comment) &&
                        Objects.equals(this.id, readReturnRequest.id) &&
                        Objects.equals(this.creationDate, readReturnRequest.creationDate) &&
                        Objects.equals(this.shopOrderNumber, readReturnRequest.shopOrderNumber) &&
                        Objects.equals(this.shopName, readReturnRequest.shopName) &&
                        Objects.equals(this.supplierOrderNumber, readReturnRequest.supplierOrderNumber) &&
                        Objects.equals(this.supplierName, readReturnRequest.supplierName) &&
                        Objects.equals(this.status, readReturnRequest.status) &&
                        Objects.equals(this.businessStatus, readReturnRequest.businessStatus);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(links, type, rmaNumber, comment, id, creationDate, shopOrderNumber, shopName, supplierOrderNumber, supplierName, status, businessStatus);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OMSReadReturnRequest {\n");
        sb.append("    links: ").append(toIndentedString(links)).append("\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    rmaNumber: ").append(toIndentedString(rmaNumber)).append("\n");
        sb.append("    comment: ").append(toIndentedString(comment)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
        sb.append("    shopOrderNumber: ").append(toIndentedString(shopOrderNumber)).append("\n");
        sb.append("    shopName: ").append(toIndentedString(shopName)).append("\n");
        sb.append("    supplierOrderNumber: ").append(toIndentedString(supplierOrderNumber)).append("\n");
        sb.append("    supplierName: ").append(toIndentedString(supplierName)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    businessStatus: ").append(toIndentedString(businessStatus)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
