package com.intershop.oms.test.businessobject.orderstate;

import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSLatestOrderStateMetaData extends OMSBusinessObject
{
    private Long totalCount = null;
    private Integer pageSize;
    private Long minCursor;
    private Long maxCursor;

    public OMSLatestOrderStateMetaData totalCount(Long totalCount)
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
        OMSLatestOrderStateMetaData collectionMetaData = (OMSLatestOrderStateMetaData) o;
        return Objects.equals(this.totalCount, collectionMetaData.totalCount) &&
                        Objects.equals(this.pageSize, collectionMetaData.pageSize) &&
                        Objects.equals(this.minCursor, collectionMetaData.minCursor) &&
                        Objects.equals(this.maxCursor, collectionMetaData.maxCursor);
    }

    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(totalCount, pageSize, minCursor, maxCursor);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class CollectionMetaData {\n");
        sb.append("    totalCount: ").append(toIndentedString(totalCount)).append("\n");
        sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    minCursor: ").append(toIndentedString(minCursor)).append("\n");
        sb.append("    maxCursor: ").append(toIndentedString(maxCursor)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
