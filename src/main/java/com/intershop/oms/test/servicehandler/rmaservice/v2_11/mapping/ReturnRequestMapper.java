package com.intershop.oms.test.servicehandler.rmaservice.v2_11.mapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.rma.v2_11.model.ContactPerson;
import com.intershop.oms.rest.rma.v2_11.model.ReadCustomAttribute;
import com.intershop.oms.rest.rma.v2_11.model.ReadPickupAddress;
import com.intershop.oms.rest.rma.v2_11.model.ReadReturnRequest;
import com.intershop.oms.rest.rma.v2_11.model.ReadReturnRequestItem;
import com.intershop.oms.rest.rma.v2_11.model.ReadReturnRequestPosition;
import com.intershop.oms.rest.rma.v2_11.model.WriteCustomAttribute;
import com.intershop.oms.rest.rma.v2_11.model.WriteReturnRequest;
import com.intershop.oms.test.businessobject.rma.OMSReturnRequest;

@Mapper(uses = { ReturnRequestPositionMapper.class }, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ReturnRequestMapper
{
    ReturnRequestMapper INSTANCE = Mappers.getMapper(ReturnRequestMapper.class);

    @Mapping(source = "readReturnRequest.id", target = "id")
    @Mapping(source = "readReturnRequest.links", target = "links")
    @Mapping(source = "positionToItemMap", target = "positions")
    @Mapping(target = "customAttributesAsMap", ignore = true)
    OMSReturnRequest fromApiReturnRequest(ReadReturnRequest readReturnRequest,
                    Map<ReadReturnRequestPosition, List<ReadReturnRequestItem>> positionToItemMap,
                    List<ContactPerson> contactPersons, List<ReadCustomAttribute> customAttributes,
                    ReadPickupAddress pickupAddress);

    default Map<String, String> mapCustomAttributes(List<ReadCustomAttribute> customAttributes)
    {
        return customAttributes.stream().collect(Collectors.toMap(ReadCustomAttribute::getKey,
                        ReadCustomAttribute::getValue, (t, u) -> t, HashMap::new));
    }

    WriteReturnRequest toWriteReturnRequest(OMSReturnRequest returnRequest);

    default List<WriteCustomAttribute> mapWriteCustomAttribute(Map<String, String> value)
    {
        return value.entrySet().stream()
                        .map(entry -> new WriteCustomAttribute().key(entry.getKey()).value(entry.getValue()))
                        .collect(Collectors.toCollection(ArrayList::new));
    }
}