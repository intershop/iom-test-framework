package com.intershop.oms.test.businessobject.rma.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.test.businessobject.rma.OMSReadReturnRequestPosition;
import com.intershop.oms.test.businessobject.rma.OMSReturnRequestPosition;
import com.intershop.oms.test.businessobject.rma.OMSWriteReturnRequestPosition;

/**
 * DBHandler prepares only WriteReturnRequestPosition
 */
@Mapper(uses= {OMSCustomAttributeMapper.class, OMSReturnRequestItemMapper.class} )
public interface OMSReturnRequestPositionMapper
{
    OMSReturnRequestPositionMapper INSTANCE = Mappers.getMapper(OMSReturnRequestPositionMapper.class);

    @Mapping(target = "items", ignore = true)
    OMSReturnRequestPosition fromReadReturnRequestPosition(OMSReadReturnRequestPosition readReturnRequestPosition);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "links", ignore = true)
    @Mapping(target = "productName", ignore = true)
    @Mapping(target = "supplierProductNumber", ignore = true)
    OMSReturnRequestPosition fromOMSWriteReturnRequestPosition(OMSWriteReturnRequestPosition writePosition);
    List<OMSReturnRequestPosition> fromOMSWriteReturnRequestPositions(Collection<OMSWriteReturnRequestPosition> writePositions);
}