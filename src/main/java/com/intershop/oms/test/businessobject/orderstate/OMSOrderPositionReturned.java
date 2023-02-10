package com.intershop.oms.test.businessobject.orderstate;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

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
public class OMSOrderPositionReturned extends OMSBusinessObject
{

    private String status;
    private OffsetDateTime supplierEntryDate;

    // OMSOrderPositionReturnedQuantities
    private Integer quantity;

    // OMSOrderPositionReturnedUnits
    private List<OMSOrderPositionReturnedUnitsUnit> units = new ArrayList<>();

}
