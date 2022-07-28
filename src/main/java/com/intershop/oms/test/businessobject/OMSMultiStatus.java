package com.intershop.oms.test.businessobject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.intershop.oms.rest.transmission.v2_0.model.Link;

/**
 * Contains multiple responses &#x60;MultiStatusResponse&#x60; for different requests, that was done within one single request.
 */
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSMultiStatus extends OMSBusinessObject
{
    private Link link = null;

    private Integer status = null;

    private List<OMSError> errors = null;

    public OMSMultiStatus link(Link link)
    {
        this.link = link;
        return this;
    }

    public OMSMultiStatus status(Integer status)
    {
        this.status = status;
        return this;
    }

    public OMSMultiStatus errors(List<OMSError> errors)
    {
        this.errors = errors;
        return this;
    }

    public OMSMultiStatus addErrorsItem(OMSError errorsItem)
    {
        if (this.errors == null)
        {
            this.errors = new ArrayList<>();
        }
        this.errors.add(errorsItem);
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
        OMSMultiStatus multiStatus = (OMSMultiStatus) o;
        return Objects.equals(this.link, multiStatus.link) && Objects.equals(this.status, multiStatus.status) && Objects.equals(this.errors, multiStatus.errors);
    }

    @Override
    public int hashCode()
    {
        return java.util.Objects.hash(link, status, errors);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class MultiStatus {\n");
        sb.append("    link: ").append(toIndentedString(link)).append("\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
