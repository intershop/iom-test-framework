package com.intershop.oms.test.servicehandler.transmissionservice.v1_1.mapping;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.transmission.v1_1.model.Link;
import com.intershop.oms.test.businessobject.OMSLink;

@Mapper
public interface LinkMapper
{
    LinkMapper INSTANCE = Mappers.getMapper(LinkMapper.class);

    OMSLink fromApiLink(Link link);

    @InheritInverseConfiguration
    public abstract Link toApiLink(OMSLink link);

    @AfterMapping
    public default void fromApiLinkList(final List<Link> links, @MappingTarget final List<OMSLink> omsLinks)
    {
        links.stream().forEach(Link -> omsLinks.add(fromApiLink(Link)));
    }

    @AfterMapping
    public default void toApiLinkList(final List<OMSLink> omsLinks, @MappingTarget final List<Link> links)
    {
        omsLinks.stream().forEach(omsLink -> links.add(toApiLink(omsLink)));
    }
}
