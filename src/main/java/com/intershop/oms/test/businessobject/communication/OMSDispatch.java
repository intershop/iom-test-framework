package com.intershop.oms.test.businessobject.communication;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSPropertyGroupOwner;
import com.intershop.oms.test.businessobject.OMSShop;
import com.intershop.oms.test.businessobject.OMSSupplier;

import lombok.AccessLevel;
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
public class OMSDispatch extends OMSBusinessObject implements OMSPropertyGroupOwner
{
    private String messageId;

    private String shopOrderNumber;

    private String supplierOrderNumber;

    private OffsetDateTime dispatchDate;

    private String deliveryNoteNumber;

    private OMSCarrier carrier;

    private OMSShop shop;

    private OMSSupplier supplier;

    private Long id;

    @Setter(AccessLevel.NONE)
    private List<OMSDispatchPosition> positions = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Map<String, Map<String, String>> propertyGroups = new HashMap<>();

    @Override
    public Map<String, Map<String, String>> getPropertyGroups()
    {
        return propertyGroups;
    }
}