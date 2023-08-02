package com.intershop.oms.test.servicehandler.rmaservice.v2_12.mapping;

import java.util.List;
import java.util.Map;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.rma.v2_12.model.ContactPerson;
import com.intershop.oms.rest.rma.v2_12.model.ReadCustomAttribute;
import com.intershop.oms.rest.rma.v2_12.model.ReadPickupAddress;
import com.intershop.oms.rest.rma.v2_12.model.ReadReturnRequestItem;
import com.intershop.oms.rest.rma.v2_12.model.ReadReturnRequestPosition;
import com.intershop.oms.rest.rma.v2_12.model.ReadReturnRequest;
import com.intershop.oms.rest.rma.v2_12.model.WriteReturnRequest;
import com.intershop.oms.test.businessobject.rma.OMSReturnRequest;

@Mapper(uses = { ReturnRequestPositionMapper.class, CustomAttributesMapper.class }, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ReturnRequestMapper
{
    ReturnRequestMapper INSTANCE = Mappers.getMapper(ReturnRequestMapper.class);

    @Mapping(source = "readReturnRequest.id", target = "id")
    @Mapping(source = "readReturnRequest.links", target = "links")
    @Mapping(source = "positionToItemMap", target = "positions")
    OMSReturnRequest fromApiReturnRequest(ReadReturnRequest readReturnRequest,
                    Map<ReadReturnRequestPosition, List<ReadReturnRequestItem>> positionToItemMap,
                    List<ContactPerson> contactPersons, List<ReadCustomAttribute> customAttributes,
                    ReadPickupAddress pickupAddress);


    WriteReturnRequest toWriteReturnRequest(OMSReturnRequest returnRequest);
}