package com.intershop.oms.test.businessobject.communication;

import java.util.HashMap;
import java.util.Map;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSPropertyGroupOwner;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ToString
public class OMSDispatchItem extends OMSBusinessObject implements OMSPropertyGroupOwner
{
    private String serialNumber;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Map<String, Map<String, String>> propertyGroups = new HashMap<>();

    public OMSDispatchItem()
    {
        //FIXME should be empty
        setSerialNumber("DEMO-Serial-Number");
    }

    @Override
    public Map<String, Map<String, String>> getPropertyGroups()
    {
        return propertyGroups;
    }
}
