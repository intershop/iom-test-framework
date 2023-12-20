package com.intershop.oms.test.businessobject.orderstate;

import java.net.URI;
import java.time.OffsetDateTime;

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
public class OMSOrderDocument extends OMSBusinessObject
{
    private String type;
    private URI downloadLink;
    private OffsetDateTime creationDate;
    private String mimeType;

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderDocument type(String type)
    {
        return setType(type);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderDocument downloadLink(URI downloadLink)
    {
        return setDownloadLink(downloadLink);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderDocument creationDate(OffsetDateTime creationDate)
    {
        return setCreationDate(creationDate);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSOrderDocument mimeType(String mimeType)
    {
        return setMimeType(mimeType);
    }
}
