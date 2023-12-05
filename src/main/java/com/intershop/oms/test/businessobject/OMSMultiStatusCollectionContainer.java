package com.intershop.oms.test.businessobject;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
/**
 * OMSMultiStatusCollectionContainer
 */
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@Accessors(chain = true)
public class OMSMultiStatusCollectionContainer extends OMSBusinessObject
{
    private OMSMultiStatusCollectionMetaData meta = null;
    private List<OMSMultiStatus> data = null;

    public OMSMultiStatusCollectionContainer addDataItem(OMSMultiStatus dataItem)
    {
        if (data == null)
        {
            data = new ArrayList<>();
        }
        data.add(dataItem);
        return this;
    }
}
