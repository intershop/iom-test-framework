package com.intershop.oms.test.businessobject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSLink
{
    private String href = null;
    private String rel = null;

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

        OMSLink link = (OMSLink) o;

        return this.getHref().equals(link.getHref()) && this.getRel().equals(link.getRel());
    }
}
