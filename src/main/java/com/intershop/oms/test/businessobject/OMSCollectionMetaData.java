package com.intershop.oms.test.businessobject;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSCollectionMetaData extends OMSBusinessObject
{
    private Long totalCount = null;

    public OMSCollectionMetaData totalCount(Long totalCount)
    {
        this.totalCount = totalCount;
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
        OMSCollectionMetaData collectionMetaData = (OMSCollectionMetaData) o;
        return Objects.equals(this.totalCount, collectionMetaData.totalCount);
    }

    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(totalCount);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class CollectionMetaData {\n");
        sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
