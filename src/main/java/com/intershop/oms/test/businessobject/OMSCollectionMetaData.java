package com.intershop.oms.test.businessobject;

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
public class OMSCollectionMetaData extends OMSBusinessObject
{
    private Long totalCount = null;
}
