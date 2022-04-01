package com.intershop.oms.test.servicehandler.orderservice.v2_1.mapping;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.order.v2_1.model.AddressLocation;
import com.intershop.oms.rest.order.v2_1.model.AddressLocationPOBox;
import com.intershop.oms.rest.order.v2_1.model.AddressLocationPackstation;
import com.intershop.oms.rest.order.v2_1.model.AddressLocationStreet;
import com.intershop.oms.test.businessobject.address.OMSAddressLocation;
import com.intershop.oms.test.businessobject.address.OMSAddressLocationPOBox;
import com.intershop.oms.test.businessobject.address.OMSAddressLocationPackstation;
import com.intershop.oms.test.businessobject.address.OMSAddressLocationStreet;

@Mapper(uses = {AddressLocationStreetMapper.class, AddressLocationPOBoxMapper.class, AddressLocationPackstationMapper.class})
public interface AddressLocationMapper
{
    AddressLocationMapper INSTANCE = Mappers.getMapper(AddressLocationMapper.class);

//    public <T extends OMSAddressLocation, S extends AddressLocation> T fromApiAddressLocation(AddressLocation addressLocation);
//
//    @InheritInverseConfiguration
//    public <T extends AddressLocation, S extends OMSAddressLocation> T toApiAddressLocation(S omsAddressLocation);

    default <T extends OMSAddressLocation, S extends AddressLocation> T fromApiAddressLocation(AddressLocation addressLocation)
    {
        if (addressLocation == null)
        {
            return null;
        }

        T target = null;
        if (addressLocation instanceof AddressLocationStreet)
        {
            target = (T)Mappers.getMapper(AddressLocationStreetMapper.class).fromApiAddressLocationStreet((AddressLocationStreet)addressLocation);
        }
        else if (addressLocation instanceof AddressLocationPOBox)
        {
            target = (T)Mappers.getMapper(AddressLocationPOBoxMapper.class).fromApiAddressLocationPOBox((AddressLocationPOBox)addressLocation);
        }
        else if (addressLocation instanceof AddressLocationPackstation)
        {
            target = (T)Mappers.getMapper(AddressLocationPackstationMapper.class).fromApiAddressLocationPackstation((AddressLocationPackstation)addressLocation);
        }
        else
        {
            throw new RuntimeException("Unknown source type " + addressLocation.getClass().getSimpleName());
        }

        return target;
    }

    @InheritInverseConfiguration
    default public <T extends AddressLocation, S extends OMSAddressLocation> T toApiAddressLocation(S omsAddressLocation)
    {
        if (omsAddressLocation == null)
        {
            return null;
        }

        T target = null;
        if (omsAddressLocation instanceof OMSAddressLocationStreet)
        {
            target = (T)Mappers.getMapper(AddressLocationStreetMapper.class).toApiAddressLocationStreet((OMSAddressLocationStreet)omsAddressLocation);
        }
        else if (omsAddressLocation instanceof OMSAddressLocationPOBox)
        {
            target = (T)Mappers.getMapper(AddressLocationPOBoxMapper.class).toApiAddressLocationPOBox((OMSAddressLocationPOBox)omsAddressLocation);
        }
        else if (omsAddressLocation instanceof OMSAddressLocationPackstation)
        {
            target = (T)Mappers.getMapper(AddressLocationPackstationMapper.class).toApiAddressLocationPackstation((OMSAddressLocationPackstation)omsAddressLocation);
        }
        else
        {
            throw new RuntimeException("Unknown source type " + omsAddressLocation.getClass().getSimpleName());
        }

        return target;
    };
}
