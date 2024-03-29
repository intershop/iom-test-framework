package com.intershop.oms.test.businessobject.orderstate;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Accessors(chain = true)
public class OMSLatestOrderStateCollectionContainer extends OMSBusinessObject
{
    private List<OMSOrderState> data = null;
    private OMSLatestOrderStateMetaData meta;
    private Map<String, URI> links = null;

    public OMSLatestOrderStateCollectionContainer addDataItem(OMSOrderState dataItem)
    {
        if (data == null)
        {
            data = new ArrayList<>();
        }
        data.add(dataItem);
        return this;
    }

    public OMSLatestOrderStateCollectionContainer putLinksItem(String key, URI linksItem)
    {
        if (links == null)
        {
            links = new HashMap<>();
        }
        links.put(key, linksItem);
        return this;
    }
}
