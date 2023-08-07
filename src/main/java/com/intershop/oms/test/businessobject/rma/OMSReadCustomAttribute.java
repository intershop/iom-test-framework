package com.intershop.oms.test.businessobject.rma;

import java.util.ArrayList;
import java.util.List;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSLink;

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
public class OMSReadCustomAttribute extends OMSBusinessObject
{
    private Long id;
    private String key = "Example Test Key";
    private String value = "Example Test Value";
    private List<OMSLink> links = new ArrayList<>();
}