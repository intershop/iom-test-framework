package com.intershop.oms.test.businessobject;

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

    /**
     * @deprecated use the LOMBOK-generated methods 
     */
    @Deprecated(since="7.0.0", forRemoval = true)
    public OMSMultiStatusCollectionMetaData successCount(Long successCount)
    {
        return setSuccessCount(successCount);
    }

    /**
     * @deprecated use the LOMBOK-generated methods 
     */
    @Deprecated(since="7.0.0", forRemoval = true)
    public OMSMultiStatusCollectionMetaData failureCount(Long failureCount)
    {
        return setFailureCount(failureCount);
    }
}
