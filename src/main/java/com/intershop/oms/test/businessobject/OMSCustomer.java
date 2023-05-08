package com.intershop.oms.test.businessobject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.intershop.oms.test.businessobject.order.OMSCompanyData;
import com.intershop.oms.test.businessobject.order.OMSCustomerData;

/**
 * business information for a supplier
 */
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OMSCustomer
{
    Date    creationDate    = Calendar.getInstance().getTime();
    String  customerShopNo  = "BB002";
    long    id;
    String  lastName        = "Buyer";
    String  firstName       = null;
    String  salutation      = null;
    String  company         = null;

    public OMSCustomer()
    {
    }

    public OMSCustomer(int testCaseId)
    {
        customerShopNo = getUniqueCustomerID(testCaseId);
    }

    public OMSCustomer(String customerPrefix)
    {
        customerShopNo = getUniqueCustomerID(customerPrefix);
    }

    public OMSCustomer(Date date)
    {
        creationDate = date;
    }

    public void setCustomerId(long anId)
    {
        id = anId;
    }

    public long getCustomerId()
    {
        return id;
    }

    public String getUniqueCustomerID(int testCaseId)
    {
        return getUniqueCustomerID("Customer_"+testCaseId);
    }

    public String getUniqueCustomerID(String prefix)
    {
        return prefix +'_' + new SimpleDateFormat("yy-MM-dd-HH:mm:ss.SSS").format(creationDate.getTime());
    }

    public OMSCustomerData getOMSCustomerData(String shopOrderNo)
    {
        OMSCustomerData customerData = new OMSCustomerData();
        if (null == company)
        {
            customerData = customerData.customerDataType(OMSCustomerData.OMSCustomerDataTypeEnum.PERSON);
        }
        else
        {
            OMSCompanyData omsCompanyData = new OMSCompanyData().setCompanyName(company);
            customerData = customerData.customerDataType(OMSCustomerData.OMSCustomerDataTypeEnum.COMPANY).companyData(omsCompanyData);
        }
        customerData.shopCustomerNumber(customerShopNo);
        customerData.orderNumber(shopOrderNo);

        return customerData;
    }
}
