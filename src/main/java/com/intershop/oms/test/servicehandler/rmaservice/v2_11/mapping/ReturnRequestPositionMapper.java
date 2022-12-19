package com.intershop.oms.test.servicehandler.rmaservice.v2_11.mapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.rma.v2_11.model.ReadReturnRequestItem;
import com.intershop.oms.rest.rma.v2_11.model.ReadReturnRequestPosition;
import com.intershop.oms.test.businessobject.rma.OMSReturnRequestPosition;

@Mapper
public interface ReturnRequestPositionMapper
{
    ReturnRequestPositionMapper INSTANCE = Mappers.getMapper(ReturnRequestPositionMapper.class);

    OMSReturnRequestPosition fromApiReadReturnRequestPosition(ReadReturnRequestPosition readReturnRequestPosition,
                    List<ReadReturnRequestItem> items);

    default List<OMSReturnRequestPosition> fromApiPositionItemMapping(
                    Map<ReadReturnRequestPosition, List<ReadReturnRequestItem>> positionToItemMap)
    {
        return positionToItemMap.entrySet().stream()
                        .map(entry -> fromApiReadReturnRequestPosition(entry.getKey(), entry.getValue()))
                        .collect(Collectors.toCollection(ArrayList::new));
    }
}