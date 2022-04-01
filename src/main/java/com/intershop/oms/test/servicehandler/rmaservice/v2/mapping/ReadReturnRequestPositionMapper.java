package com.intershop.oms.test.servicehandler.rmaservice.v2.mapping;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.rma.v2.model.ReadReturnRequestPosition;
import com.intershop.oms.test.businessobject.rma.OMSReadReturnRequestPosition;

@Mapper
public interface ReadReturnRequestPositionMapper
{
    ReadReturnRequestPositionMapper INSTANCE = Mappers.getMapper(ReadReturnRequestPositionMapper.class);

    OMSReadReturnRequestPosition fromApiReadReturnRequestPosition(ReadReturnRequestPosition readReturnRequestPosition);

    @InheritInverseConfiguration
    public abstract ReadReturnRequestPosition toApiReadReturnRequestPosition(OMSReadReturnRequestPosition omsReadReturnRequestPosition);

    @AfterMapping
    public default void fromApiReadReturnRequestPositionList(final List<ReadReturnRequestPosition> readReturnRequestPositions, @MappingTarget final List<OMSReadReturnRequestPosition> omsReadReturnRequestPositions)
    {
        readReturnRequestPositions.stream().forEach(readReturnRequestPosition -> omsReadReturnRequestPositions.add(fromApiReadReturnRequestPosition(readReturnRequestPosition)));
    }
}