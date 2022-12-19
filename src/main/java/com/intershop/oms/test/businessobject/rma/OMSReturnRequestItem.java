package com.intershop.oms.test.businessobject.rma;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSLink;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@ToString
@EqualsAndHashCode(callSuper = false)
public class OMSReturnRequestItem extends OMSBusinessObject
{
    private String productSerialNumber;
    private Long id;
    private List<OMSLink> links = new ArrayList<>();
}
