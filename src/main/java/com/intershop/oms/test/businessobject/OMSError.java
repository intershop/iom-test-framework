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
public class OMSError extends OMSBusinessObject
{
    private String code;
    private String message;
    private Object value;
}
