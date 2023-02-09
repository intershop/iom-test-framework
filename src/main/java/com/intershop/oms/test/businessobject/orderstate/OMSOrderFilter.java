package com.intershop.oms.test.businessobject.orderstate;


import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

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
public class OMSOrderFilter
{

    private List<String> shopOrderNumbers = new ArrayList<>();
    private List<String> shopCustomerNumbers = new ArrayList<>();
    private List<String> productNumbers = new ArrayList<>();    // shop
    private List<String> emails = new ArrayList<>();          // order-level
    private List<String> statuses = new ArrayList<>();          // order-level

    private LocalDate shopOrderCreationDateFrom;
    private LocalDate shopOrderCreationDateTo;
    private OffsetDateTime lastModificationTimeFrom;

}
