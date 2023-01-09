package com.intershop.oms.test.businessobject;

import java.util.Date;

import lombok.Data;

@Data
public class OMSInventory
{
    private String id;

    private String location;

    private Integer stock;

    private Integer reserved;

    private Integer blocked;

    private Integer atp;

    private Date modificationDate;

    private String state;

    public OMSInventory() {}
}
