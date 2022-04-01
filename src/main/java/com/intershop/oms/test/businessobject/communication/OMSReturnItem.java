package com.intershop.oms.test.businessobject.communication;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSPropertyGroupOwner;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Accessors(chain = true)
public class OMSReturnItem extends OMSBusinessObject implements OMSPropertyGroupOwner
{
    private String serialNumber;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Map<String, Map<String, String>> propertyGroups = new HashMap<>();

    @Override
    public Map<String, Map<String, String>> getPropertyGroups()
    {
        return propertyGroups;
    }
}