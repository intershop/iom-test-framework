package com.intershop.oms.test.businessobject.transmission;

import java.util.ArrayList;
import java.util.List;

import com.intershop.oms.test.businessobject.OMSCollectionMetaData;

/**
 * TransmissionCollectionContainer
 */

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSTransmissionCollectionContainer
{
    private OMSCollectionMetaData meta = null;

    private List<OMSTransmission> data = null;

    public OMSTransmissionCollectionContainer meta(OMSCollectionMetaData meta)
    {
        this.meta = meta;
        return this;
    }

    public OMSTransmissionCollectionContainer data(List<OMSTransmission> data)
    {
        this.data = data;
        return this;
    }

    public OMSTransmissionCollectionContainer addDataItem(OMSTransmission dataItem)
    {
        if (this.data == null)
        {
            this.data = new ArrayList<>();
        }
        this.data.add(dataItem);
        return this;
    }
}
