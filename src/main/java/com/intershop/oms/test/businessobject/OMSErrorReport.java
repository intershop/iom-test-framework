package com.intershop.oms.test.businessobject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.intershop.oms.test.businessobject.orderstate.OMSLatestOrderStateMetaData;

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
public class OMSErrorReport extends OMSBusinessObject
{
    private Integer status;
    private List<OMSError> errors = null;

    public OMSErrorReport addErrorsItem(OMSError errorsItem)
    {
        if (errors == null)
        {
            errors = new ArrayList<>();
        }
        errors.add(errorsItem);
        return this;
    }
}
