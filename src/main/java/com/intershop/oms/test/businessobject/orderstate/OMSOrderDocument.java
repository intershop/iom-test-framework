package com.intershop.oms.test.businessobject.orderstate;

import java.net.URI;
import java.time.OffsetDateTime;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSOrderDocument extends OMSBusinessObject
{
    private String type;

    private URI downloadLink;

    private OffsetDateTime creationDate;

    private String mimeType;

    public OMSOrderDocument type(String type)
    {
        this.type = type;
        return this;
    }

    public OMSOrderDocument downloadLink(URI downloadLink)
    {
        this.downloadLink = downloadLink;
        return this;
    }

    public OMSOrderDocument creationDate(OffsetDateTime creationDate)
    {
        this.creationDate = creationDate;
        return this;
    }

    public OMSOrderDocument mimeType(String mimeType)
    {
        this.mimeType = mimeType;
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
        OMSOrderDocument orderDocument = (OMSOrderDocument) o;
        return Objects.equals(this.type, orderDocument.type) &&
                        Objects.equals(this.downloadLink, orderDocument.downloadLink) &&
                        Objects.equals(this.creationDate, orderDocument.creationDate) &&
                        Objects.equals(this.mimeType, orderDocument.mimeType);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(type, downloadLink, creationDate, mimeType);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrderDocument {\n");
        sb.append("    type: ").append(toIndentedString(type)).append("\n");
        sb.append("    downloadLink: ").append(toIndentedString(downloadLink)).append("\n");
        sb.append("    creationDate: ").append(toIndentedString(creationDate)).append("\n");
        sb.append("    mimeType: ").append(toIndentedString(mimeType)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
