package com.intershop.oms.test.businessobject.orderstate;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSCollectionMetaData;

/**
 * OrderStateCollectionContainer
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSOrderStateCollectionContainer extends OMSBusinessObject
{
    private List<OMSOrderState> data = null;

    private OMSCollectionMetaData meta;

    private Map<String, URI> links = null;

    public OMSOrderStateCollectionContainer data(List<OMSOrderState> data)
    {
        this.data = data;
        return this;
    }

    public OMSOrderStateCollectionContainer addDataItem(OMSOrderState dataItem)
    {
        if (this.data == null)
        {
            this.data = new ArrayList<>();
        }
        this.data.add(dataItem);
        return this;
    }

    public OMSOrderStateCollectionContainer meta(OMSCollectionMetaData meta)
    {
        this.meta = meta;
        return this;
    }

    public OMSOrderStateCollectionContainer links(Map<String, URI> links)
    {
        this.links = links;
        return this;
    }

    public OMSOrderStateCollectionContainer putLinksItem(String key, URI linksItem)
    {
        if (this.links == null)
        {
            this.links = new HashMap<>();
        }
        this.links.put(key, linksItem);
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
        OMSOrderStateCollectionContainer orderStateCollectionContainer = (OMSOrderStateCollectionContainer) o;
        return Objects.equals(this.data, orderStateCollectionContainer.data) &&
                        Objects.equals(this.meta, orderStateCollectionContainer.meta) &&
                        Objects.equals(this.links, orderStateCollectionContainer.links);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(data, meta, links);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrderStateCollectionContainer {\n");
        sb.append("    data: ").append(toIndentedString(data)).append("\n");
        sb.append("    meta: ").append(toIndentedString(meta)).append("\n");
        sb.append("    links: ").append(toIndentedString(links)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
