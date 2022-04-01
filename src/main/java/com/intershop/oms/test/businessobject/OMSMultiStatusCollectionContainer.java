package com.intershop.oms.test.businessobject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * OMSMultiStatusCollectionContainer
 */
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSMultiStatusCollectionContainer extends OMSBusinessObject
{
    private OMSMultiStatusCollectionMetaData meta = null;

    private List<OMSMultiStatus> data = null;

    public OMSMultiStatusCollectionContainer meta(OMSMultiStatusCollectionMetaData meta)
    {
        this.meta = meta;
        return this;
    }

    /**
     * Get meta
     * @return meta
     **/
    public OMSMultiStatusCollectionMetaData getMeta()
    {
        return meta;
    }

    public OMSMultiStatusCollectionContainer data(List<OMSMultiStatus> data)
    {
        this.data = data;
        return this;
    }

    public OMSMultiStatusCollectionContainer addDataItem(OMSMultiStatus dataItem)
    {
        if (this.data == null)
        {
            this.data = new ArrayList<>();
        }
        this.data.add(dataItem);
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
        OMSMultiStatusCollectionContainer OMSMultiStatusCollectionContainer = (OMSMultiStatusCollectionContainer) o;
        return Objects.equals(this.meta, OMSMultiStatusCollectionContainer.meta) && Objects.equals(this.data, OMSMultiStatusCollectionContainer.data);
    }

    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(meta, data);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OMSMultiStatusCollectionContainer {\n");
        sb.append("    meta: ").append(toIndentedString(meta)).append("\n");
        sb.append("    data: ").append(toIndentedString(data)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
