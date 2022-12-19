package com.intershop.oms.test.businessobject.rma;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSLink;
import com.intershop.oms.test.businessobject.communication.OMSDispatchItem;
import com.intershop.oms.test.businessobject.communication.OMSDispatchPosition;

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
public class OMSReturnRequestPosition extends OMSBusinessObject
{
    private List<OMSLink> links = new ArrayList<>();
    private Integer positionNumber;
    private String productNumber;
    private String reason;
    private Integer quantity;
    private Long id;
    private String productName;
    private String supplierProductNumber;
    private List<OMSReturnRequestItem> items;

    public static OMSReturnRequestPosition fromDispatchPosition(OMSDispatchPosition dispatchPosition)
    {
        return new OMSReturnRequestPosition().setPositionNumber(dispatchPosition.getOrderPositionNumber())
                        .setQuantity(dispatchPosition.getDispatchedQuantity())
                        .setProductNumber(dispatchPosition.getProduct().getNumber())
                        .setProductName(dispatchPosition.getProduct().getName()).setReason("RET010")
                        .setItems(mapItems(dispatchPosition.getItems()));
    }

    private static List<OMSReturnRequestItem> mapItems(List<OMSDispatchItem> dispatchItems)
    {
        if (dispatchItems == null || dispatchItems.size() == 0)
        {
            return null;
        }
        return dispatchItems.stream()
                        .map(item -> new OMSReturnRequestItem().setProductSerialNumber(item.getSerialNumber()))
                        .collect(Collectors.toCollection(ArrayList::new));
    }
}
