package com.intershop.oms.test.businessobject;

import java.util.Map;

import com.intershop.oms.test.businessobject.prices.OMSTax;
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

    // Seems we cannot cache this - if tests create new assignments than this must not be static!
    public Map<String, OMSTax> getShopTaxes()
    {
        return ServiceHandlerFactory.getDbHandler().getShopTaxes(this);
    }
}
