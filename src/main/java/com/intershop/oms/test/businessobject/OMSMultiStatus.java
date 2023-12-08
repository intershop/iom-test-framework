package com.intershop.oms.test.businessobject;

import java.util.ArrayList;
import java.util.List;

import com.intershop.oms.rest.transmission.v2_0.model.Link;

import lombok.EqualsAndHashCode;
/**
 * Contains multiple responses &#x60;MultiStatusResponse&#x60; for different requests, that was done within one single request.
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
public class OMSMultiStatus extends OMSBusinessObject
{
    private Link link = null;
    private Integer status = null;
    private List<OMSError> errors = null;

    public OMSMultiStatus addErrorsItem(OMSError errorsItem)
    {
        if (errors == null)
        {
            errors = new ArrayList<>();
        }
        errors.add(errorsItem);
        return this;
    }
}
