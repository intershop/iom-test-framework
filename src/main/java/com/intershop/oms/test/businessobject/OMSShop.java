package com.intershop.oms.test.businessobject;

import com.intershop.oms.test.servicehandler.ServiceHandlerFactory;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * business information for a shop
 */
@EqualsAndHashCode
@Getter
@Setter
@Accessors(chain = true)
@ToString
public class OMSShop
{
    private String name;
    private long id;

    public OMSShop()
    {
    }

    public OMSShop(String aShopName)
    {
        name = aShopName;
        id = ServiceHandlerFactory.getDbHandler().getShopId(name);
    }

    public OMSShop(long shopId)
    {
        name = ServiceHandlerFactory.getDbHandler().getShopName(shopId);
        id = shopId;
    }
}
