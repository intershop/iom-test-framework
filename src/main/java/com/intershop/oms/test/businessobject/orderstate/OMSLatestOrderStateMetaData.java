package com.intershop.oms.test.businessobject.orderstate;

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
public class OMSLatestOrderStateMetaData extends OMSBusinessObject
{
    private Long totalCount = null;
    private Integer pageSize;
    private Long minCursor;
    private Long maxCursor;
}
