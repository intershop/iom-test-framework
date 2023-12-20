package com.intershop.oms.test.servicehandler.orderservice.v2_4.mapping;

import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.intershop.oms.rest.order.v2_4.model.ChangeRequestView;
import com.intershop.oms.test.businessobject.order.OMSChangeRequest;

@Mapper(uses= {OrderMapper.class})
public interface ChangeRequestViewMapper {
    ChangeRequestViewMapper INSTANCE = Mappers.getMapper(ChangeRequestViewMapper.class);

    // @Mapping(target = "changeRequest.shippingBuckets[].positions[].status", ignore = true)
    // @Mapping(target = "changeRequest.shippingBuckets[].positions[].ordered", ignore = true)
    // @Mapping(target = "changeRequest.shippingBuckets[].positions[].commissioned", ignore = true)
    // @Mapping(target = "changeRequest.shippingBuckets[].positions[].confirmed", ignore = true)
    // @Mapping(target = "changeRequest.shippingBuckets[].positions[].dispatched", ignore = true)
    // @Mapping(target = "changeRequest.shippingBuckets[].positions[].returned", ignore = true)
    @Mapping(target = "id", ignore = true)
    OMSChangeRequest fromApiChangeRequestView(ChangeRequestView changeRequestView);

    @InheritInverseConfiguration
    public abstract ChangeRequestView toApiChangeRequestView(OMSChangeRequest omsChangeRequest);

    @AfterMapping
    public default void fromApiChangeRequestViewList(final List<ChangeRequestView> changeRequestsViews, @MappingTarget final List<OMSChangeRequest> omsChangeRequests)
    {
        changeRequestsViews.stream().forEach(changeRequestView -> omsChangeRequests.add(fromApiChangeRequestView(changeRequestView)));
    }
}
