package com.intershop.oms.test.businessobject;

import java.util.Objects;

import lombok.EqualsAndHashCode;
/**
 * The meta data of a multi-status collection.
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
public class OMSMultiStatusCollectionMetaData extends OMSBusinessObject
{
    private Long successCount = null;
    private Long failureCount = null;

    public OMSMultiStatusCollectionMetaData successCount(Long successCount)
    {
        this.successCount = successCount;
        return this;
    }

    public OMSMultiStatusCollectionMetaData failureCount(Long failureCount)
    {
        this.failureCount = failureCount;
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
        OMSMultiStatusCollectionMetaData multiStatusCollectionMetaData = (OMSMultiStatusCollectionMetaData) o;
        return Objects.equals(this.successCount, multiStatusCollectionMetaData.successCount) && Objects.equals(this.failureCount, multiStatusCollectionMetaData.failureCount);
    }

    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(successCount, failureCount);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class MultiStatusCollectionMetaData {\n");
        sb.append("    successCount: ").append(toIndentedString(successCount)).append("\n");
        sb.append("    failureCount: ").append(toIndentedString(failureCount)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
