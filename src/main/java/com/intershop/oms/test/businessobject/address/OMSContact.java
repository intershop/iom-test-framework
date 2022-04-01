package com.intershop.oms.test.businessobject.address;

import java.util.Objects;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSContact extends OMSBusinessObject
{
    private String email;

    private String phone;

    private String mobile;

    private String fax;

    public OMSContact email(String email)
    {
        this.email = email;
        return this;
    }

    public OMSContact phone(String phone)
    {
        this.phone = phone;
        return this;
    }

    public OMSContact mobile(String mobile)
    {
        this.mobile = mobile;
        return this;
    }

    public OMSContact fax(String fax)
    {
        this.fax = fax;
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
        OMSContact contact = (OMSContact) o;
        return Objects.equals(this.email, contact.email) &&
                        Objects.equals(this.phone, contact.phone) &&
                        Objects.equals(this.mobile, contact.mobile) &&
                        Objects.equals(this.fax, contact.fax);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(email, phone, mobile, fax);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Contact {\n");
        sb.append("    email: ").append(toIndentedString(email)).append("\n");
        sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
        sb.append("    mobile: ").append(toIndentedString(mobile)).append("\n");
        sb.append("    fax: ").append(toIndentedString(fax)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * @TODO: remove after removing SOAPOrderServiceHandler
     *
     * @param prefix
     * @return
     */
    public String getSOAPRequestString(String prefix)
    {
        StringBuffer soapContent = new StringBuffer();
        soapContent.append("\n    <" +prefix+":Contact ");
        if (phone != null)
        {
            soapContent.append("telephone=\""+phone+"\" ");
        }
        if (mobile != null)
        {
            soapContent.append("mobile=\""+mobile+"\" ");
        }
        if (fax != null)
        {
            soapContent.append("fax=\""+fax+"\" ");
        }
        if (fax != null)
        {
            soapContent.append("email=\""+email+"\" ");
        }
        soapContent.append("/>");
        return soapContent.toString();
    }
}
