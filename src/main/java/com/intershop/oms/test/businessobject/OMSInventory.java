package com.intershop.oms.test.businessobject;

import java.time.OffsetDateTime;

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
public class OMSInventory
{
    private String id;

    private String location;

    private Integer stock;

    private Integer reserved;

    private Integer blocked;

    private Integer atp;

    private OffsetDateTime modificationDate;

    private String state;
}
