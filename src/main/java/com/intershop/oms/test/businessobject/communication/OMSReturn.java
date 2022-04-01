package com.intershop.oms.test.businessobject.communication;

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

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Accessors(chain = true)
public class OMSReturn extends OMSBusinessObject implements OMSPropertyGroupOwner
{
    private String messageId;

    private OMSShop shop;

    private OMSSupplier supplier;

    private String shopOrderNumber;

    private String supplierOrderNumber;

    private String reason;

    private OffsetDateTime entryDate;

    private List<OMSReturnPosition> positions = new ArrayList<>();

    private Long id;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Map<String, Map<String, String>> propertyGroups = new HashMap<>();

    @Override
    public Map<String, Map<String, String>> getPropertyGroups()
    {
        return propertyGroups;
    }
}