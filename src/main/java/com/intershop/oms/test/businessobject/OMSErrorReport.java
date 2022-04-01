package com.intershop.oms.test.businessobject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSErrorReport extends OMSBusinessObject
{
    private Integer status;

    private List<OMSError> errors = null;

    public OMSErrorReport status(Integer status)
    {
        this.status = status;
        return this;
    }

    public OMSErrorReport errors(List<OMSError> errors)
    {
        this.errors = errors;
        return this;
    }

    public OMSErrorReport addErrorsItem(OMSError errorsItem)
    {
        if (this.errors == null) {
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
        OMSErrorReport errorReport = (OMSErrorReport) o;
        return Objects.equals(this.status, errorReport.status) && Objects.equals(this.errors, errorReport.errors);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(status, errors);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OMSErrorReport {\n");
        sb.append("    status: ").append(toIndentedString(status)).append("\n");
        sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
