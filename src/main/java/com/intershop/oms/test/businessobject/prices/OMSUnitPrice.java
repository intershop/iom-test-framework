package com.intershop.oms.test.businessobject.prices;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSUnitPrice extends OMSBusinessObject
{
    private OMSListPrice net;

    private OMSListPrice gross;
}