package com.intershop.oms.test.businessobject.rma.mapper;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.test.businessobject.rma.OMSReturnRequestItem;
import com.intershop.oms.test.businessobject.rma.OMSWriteReturnRequestItem;

@Mapper
public interface OMSReturnRequestItemMapper  
{
    OMSReturnRequestItemMapper INSTANCE = Mappers.getMapper(OMSReturnRequestItemMapper.class);

    @Mapping(target="id", ignore = true)
    @Mapping(target="links", ignore = true)
    OMSReturnRequestItem fromWriteReturnRequestItem(OMSWriteReturnRequestItem item);
    List<OMSReturnRequestItem> fromWriteReturnRequestItems(Collection<OMSWriteReturnRequestItem> items);
    
    List<OMSWriteReturnRequestItem> toWriteReturnRequestItems(Collection<OMSReturnRequestItem> items);
}