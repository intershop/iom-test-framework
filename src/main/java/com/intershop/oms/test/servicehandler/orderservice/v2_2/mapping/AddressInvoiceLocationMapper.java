package com.intershop.oms.test.servicehandler.orderservice.v2_2.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.order.v2_2.model.AddressInvoiceLocation;
import com.intershop.oms.rest.order.v2_2.model.AddressLocationPOBox;
import com.intershop.oms.rest.order.v2_2.model.AddressLocationPackstation;
import com.intershop.oms.rest.order.v2_2.model.AddressLocationStreet;
import com.intershop.oms.test.businessobject.address.OMSAddressLocation;
import com.intershop.oms.test.businessobject.address.OMSAddressLocationPOBox;
import com.intershop.oms.test.businessobject.address.OMSAddressLocationStreet;

@Mapper
public interface AddressInvoiceLocationMapper
{
    AddressInvoiceLocationMapper INSTANCE = Mappers.getMapper(AddressInvoiceLocationMapper.class);

    @SuppressWarnings("unchecked")
    default <T extends OMSAddressLocation> T fromApiAddressInvoiceLocation(AddressInvoiceLocation addressLocation)
    {
        if (addressLocation == null)
        {
            return null;
        }

        Object actualInstance = addressLocation.getActualInstance();
        if (actualInstance instanceof AddressLocationStreet)
        {
            return (T)AddressLocationStreetMapper.INSTANCE
                            .fromApiAddressLocationStreet((AddressLocationStreet)actualInstance);
        }
        else if (actualInstance instanceof AddressLocationPackstation)
        {
            return (T)AddressLocationPackstationMapper.INSTANCE
                            .fromApiAddressLocationPackstation((AddressLocationPackstation)actualInstance);
        }

        throw new RuntimeException(
                        "Unexpected address location type on shipping address: " + actualInstance.getClass());

    }

    default public <S extends OMSAddressLocation> AddressInvoiceLocation toApiAddressInvoiceLocation(
                    S omsAddressLocation)
    {
        if (omsAddressLocation == null)
        {
            return null;
        }

        if (omsAddressLocation instanceof OMSAddressLocationStreet)
        {
            AddressLocationStreet apiAddressLocationStreet = Mappers.getMapper(AddressLocationStreetMapper.class)
                            .toApiAddressLocationStreet((OMSAddressLocationStreet)omsAddressLocation);
            return new AddressInvoiceLocation(apiAddressLocationStreet);
        }
        else if (omsAddressLocation instanceof OMSAddressLocationPOBox)
        {
            AddressLocationPOBox apiAddressLocationPOBox = Mappers.getMapper(AddressLocationPOBoxMapper.class)
                            .toApiAddressLocationPOBox((OMSAddressLocationPOBox)omsAddressLocation);
            return new AddressInvoiceLocation(apiAddressLocationPOBox);
        }
        else
        {
            throw new RuntimeException("Unknown source type " + omsAddressLocation.getClass().getSimpleName());
        }

    }
}
