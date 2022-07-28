package com.intershop.oms.test.businessobject;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Accessors(chain = true)
public class OMSObjectReference
{
    private String referenceName;
    private Long id;
}
