package com.intershop.oms.test.businessobject.rma;

import java.util.ArrayList;
import java.util.List;

import com.intershop.oms.test.businessobject.OMSBusinessObject;
import com.intershop.oms.test.businessobject.OMSLink;

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
public class OMSReturnableData extends OMSBusinessObject
{
    private List<OMSLink> links = null;

    private List<OMSReturnableDataPosition> positions = new ArrayList<>();

    public OMSReturnableData addLinksItem(OMSLink linksItem)
    {
        if (this.links == null)
        {
            this.links = new ArrayList<>();
        }
        this.links.add(linksItem);
        return this;
    }

    public OMSReturnableData addPositionsItem(OMSReturnableDataPosition positionsItem)
    {
        this.positions.add(positionsItem);
        return this;
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSReturnableData links(List<OMSLink> links)
    {
        return setLinks(links);
    }

    @Deprecated(since = "7.0.0", forRemoval = true)
    public OMSReturnableData positions(List<OMSReturnableDataPosition> positions)
    {
        return setPositions(positions);
    }
}
