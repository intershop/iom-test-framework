package com.intershop.oms.test.businessobject;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSError extends OMSBusinessObject
{
    private String code;

    private String message;

    private Object value;

    public OMSError code(String code)
    {
        this.code = code;
        return this;
    }

    public OMSError message(String message)
    {
        this.message = message;
        return this;
    }

    public OMSError value(Object value)
    {
        this.value = value;
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
        OMSError error = (OMSError) o;
        return Objects.equals(this.code, error.code) && Objects.equals(this.message, error.message) && Objects.equals(this.value, error.value);
    }

    @Override
    public int hashCode(){
        return Objects.hash(code, message, value);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("class OMSError {\n");
        sb.append("    code: ").append(toIndentedString(code)).append("\n");
        sb.append("    message: ").append(toIndentedString(message)).append("\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
        sb.append("}");
        return sb.toString();
    }
}
