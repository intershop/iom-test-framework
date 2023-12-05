package com.intershop.oms.test.businessobject.orderstate;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSCollectionMetaData;

import lombok.EqualsAndHashCode;

/**
 * OrderStateCollectionContainer
 */

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Accessors(chain = true)
public class OMSOrderStateCollectionContainer extends OMSBusinessObject
{
    private List<OMSOrderState> data = null;
    private OMSCollectionMetaData meta;
    private Map<String, URI> links = null;

    public OMSOrderStateCollectionContainer addDataItem(OMSOrderState dataItem)
    {
        if (data == null)
        {
            data = new ArrayList<>();
        }
        data.add(dataItem);
        return this;
    }

    public OMSOrderStateCollectionContainer putLinksItem(String key, URI linksItem)
    {
        if (links == null)
        {
            links = new HashMap<>();
        }
        links.put(key, linksItem);
        return this;
    }
}
