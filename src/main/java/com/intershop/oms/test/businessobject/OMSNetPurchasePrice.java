package com.intershop.oms.test.businessobject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSNetPurchasePrice extends OMSBusinessObject
{
    private Double amount;
    private String currency;
}