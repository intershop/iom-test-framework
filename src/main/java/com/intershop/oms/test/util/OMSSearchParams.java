package com.intershop.oms.test.util;

import com.intershop.oms.test.businessobject.OMSSortDirection;

import lombok.Data;

@Data
public class OMSSearchParams
{
    private Integer limit  = 1000;
    private Integer offset = 0;
    private OMSSortDirection sortDirection = OMSSortDirection.ASC;
    private String sortableAttribute = "id";

    /**
     * using the default values:
     *
     * limit = 50
     * offset= 0
     * sortDir = ASC
     * sortableAttribute = "id"
     */
    public OMSSearchParams() {};

    public OMSSearchParams(Integer limit, Integer offset, OMSSortDirection sortDirection, String sortableAttribute)
    {
        this.limit = limit;
        this.offset = offset;
        this.sortDirection = sortDirection;
        this.sortableAttribute = sortableAttribute;
    };
}
