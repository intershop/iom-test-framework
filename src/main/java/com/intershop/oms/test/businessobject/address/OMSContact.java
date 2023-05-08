package com.intershop.oms.test.businessobject.address;

import com.intershop.oms.test.businessobject.OMSBusinessObject;

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
public class OMSContact extends OMSBusinessObject
{
    private String email;

    private String phone;

    private String mobile;

    private String fax;

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSContact email(String email)
    {
        return setEmail(email);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSContact phone(String phone)
    {
        return setPhone(phone);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSContact mobile(String mobile)
    {
        return setMobile(mobile);
    }

    @Deprecated(since = "4.6.0", forRemoval = true)
    public OMSContact fax(String fax)
    {
        return setFax(fax);
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
