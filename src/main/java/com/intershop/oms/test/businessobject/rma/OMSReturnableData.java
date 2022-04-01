package com.intershop.oms.test.businessobject.rma;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSLink;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSReturnableData extends OMSBusinessObject
{
    private List<OMSLink> links = null;

    private List<OMSReturnableDataPosition> positions = new ArrayList<>();

    public OMSReturnableData links(List<OMSLink> links)
    {
        this.links = links;
        return this;
    }

    public OMSReturnableData addLinksItem(OMSLink linksItem)
    {
        if (this.links == null)
        {
            this.links = new ArrayList<>();
        }
        this.links.add(linksItem);
        return this;
    }

    public OMSReturnableData positions(List<OMSReturnableDataPosition> positions)
    {
        this.positions = positions;
        return this;
    }

    public OMSReturnableData addPositionsItem(OMSReturnableDataPosition positionsItem)
    {
        this.positions.add(positionsItem);
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
        OMSReturnableData returnableData = (OMSReturnableData) o;
        return Objects.equals(this.links, returnableData.links) && Objects.equals(this.positions, returnableData.positions);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(links, positions);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OMSReturnableData {\n");
        sb.append("    links: ").append(toIndentedString(links)).append("\n");
        sb.append("    positions: ").append(toIndentedString(positions)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
