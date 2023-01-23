package com.intershop.oms.test.servicehandler.inventoryservice.v2_0.mapping;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.inventory.v2_0.model.Atp;
import com.intershop.oms.test.businessobject.OMSInventory;

@Mapper
public interface InventoryMapper
{
    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    List<OMSInventory> fromApiInventory(List<Atp> list);

    default OffsetDateTime map(Date date)
    {
        if (null == date)
        {
            return null;
        }
        else
        {
            return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toOffsetDateTime();
        }
    }
}