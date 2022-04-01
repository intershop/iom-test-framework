package com.intershop.oms.test.servicehandler.orderservice.v2_2.mapping;

import java.util.Map;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.order.v2_2.model.AddressLocation;
import com.intershop.oms.rest.shared.JSON;
import com.intershop.oms.test.businessobject.address.OMSAddressLocation;
import com.intershop.oms.test.businessobject.address.OMSAddressLocationPOBox;
import com.intershop.oms.test.businessobject.address.OMSAddressLocationPackstation;
import com.intershop.oms.test.businessobject.address.OMSAddressLocationStreet;

@Mapper(uses = { AddressLocationStreetMapper.class, AddressLocationPOBoxMapper.class,
                AddressLocationPackstationMapper.class })
public interface AddressLocationMapper
{
    AddressLocationMapper INSTANCE = Mappers.getMapper(AddressLocationMapper.class);

    @SuppressWarnings("unchecked")
    default <T extends OMSAddressLocation> T fromApiAddressLocation(Object addressLocation)
    {
        if (addressLocation == null)
        {
            return null;
        }

        if (addressLocation instanceof Map)
        {

            Map<?, ?> source = (Map<?, ?>)addressLocation;
            JSON json = new JSON();
            try
            {
                // FIXME no comment...
                return (T)json.getGson().fromJson(json.getGson().toJsonTree(source), Class
                                .forName("com.intershop.oms.test.businessobject.address.OMS" + source.get("type")));
            }
            catch(ClassNotFoundException e)
            {
                throw new RuntimeException("Unknown source type " + source.get("type"), e);
            }
        }
        else
        {
            throw new RuntimeException("Unknown source type " + addressLocation.getClass().getSimpleName());
        }
    }

    @SuppressWarnings("unchecked")
    @InheritInverseConfiguration
    default public <T extends AddressLocation, S extends OMSAddressLocation> T toApiAddressLocation(
                    S omsAddressLocation)
    {
        if (omsAddressLocation == null)
        {
            return null;
        }

        T target = null;
        if (omsAddressLocation instanceof OMSAddressLocationStreet)
        {
            target = (T)Mappers.getMapper(AddressLocationStreetMapper.class)
                            .toApiAddressLocationStreet((OMSAddressLocationStreet)omsAddressLocation);
        }
        else if (omsAddressLocation instanceof OMSAddressLocationPOBox)
        {
            target = (T)Mappers.getMapper(AddressLocationPOBoxMapper.class)
                            .toApiAddressLocationPOBox((OMSAddressLocationPOBox)omsAddressLocation);
        }
        else if (omsAddressLocation instanceof OMSAddressLocationPackstation)
        {
            target = (T)Mappers.getMapper(AddressLocationPackstationMapper.class)
                            .toApiAddressLocationPackstation((OMSAddressLocationPackstation)omsAddressLocation);
        }
        else
        {
            throw new RuntimeException("Unknown source type " + omsAddressLocation.getClass().getSimpleName());
        }

        return target;
    };
}
