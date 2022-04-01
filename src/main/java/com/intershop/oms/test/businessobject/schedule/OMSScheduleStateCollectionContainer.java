package com.intershop.oms.test.businessobject.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSCollectionMetaData;

/**
 * A collection of &#x60;ScheduleState&#x60;.
 */
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSScheduleStateCollectionContainer extends OMSBusinessObject
{
    private OMSCollectionMetaData meta;

    private List<OMSScheduleState> data = null;

    public OMSScheduleStateCollectionContainer meta(OMSCollectionMetaData meta)
    {
        this.meta = meta;
        return this;
    }

    public OMSScheduleStateCollectionContainer data(List<OMSScheduleState> data)
    {
        this.data = data;
        return this;
    }

    public OMSScheduleStateCollectionContainer addDataItem(OMSScheduleState dataItem)
    {
        if (this.data == null)
        {
            this.data = new ArrayList<>();
        }
        this.data.add(dataItem);
        return this;
    }

    @Override
    public boolean equals(java.lang.Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        OMSScheduleStateCollectionContainer scheduleStatesCollectionContainer = (OMSScheduleStateCollectionContainer) o;
        return Objects.equals(this.meta, scheduleStatesCollectionContainer.meta) && Objects.equals(this.data, scheduleStatesCollectionContainer.data);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(meta, data);
    }


    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class ScheduleStatesCollectionContainer {\n");
        sb.append("    meta: ").append(toIndentedString(meta)).append("\n");
        sb.append("    data: ").append(toIndentedString(data)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}