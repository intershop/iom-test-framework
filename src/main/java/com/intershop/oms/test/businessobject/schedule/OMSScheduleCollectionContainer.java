package com.intershop.oms.test.businessobject.schedule;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSCollectionMetaData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSScheduleCollectionContainer extends OMSBusinessObject
{
    private OMSCollectionMetaData meta;

    private List<OMSSchedule> data = null;

    public OMSScheduleCollectionContainer meta(OMSCollectionMetaData meta)
    {
        this.meta = meta;
        return this;
    }

    public OMSScheduleCollectionContainer data(List<OMSSchedule> data)
    {
        this.data = data;
        return this;
    }

    public OMSScheduleCollectionContainer addDataItem(OMSSchedule dataItem)
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
        OMSScheduleCollectionContainer scheduleCollectionContainer = (OMSScheduleCollectionContainer) o;
        return Objects.equals(this.meta, scheduleCollectionContainer.meta) && Objects.equals(this.data, scheduleCollectionContainer.data);
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
        sb.append("class ScheduleCollectionContainer {\n");
        sb.append("    meta: ").append(toIndentedString(meta)).append("\n");
        sb.append("    data: ").append(toIndentedString(data)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
