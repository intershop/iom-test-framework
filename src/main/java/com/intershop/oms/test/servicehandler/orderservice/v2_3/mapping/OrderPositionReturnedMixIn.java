package com.intershop.oms.test.servicehandler.orderservice.v2_3.mapping;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.intershop.oms.rest.order.v2_3.model.OrderPositionReturned;
import com.intershop.oms.test.servicehandler.orderservice.v2_3.mapping.OrderPositionReturnedDeserializer;

/**
 * registering a module at the mapper did not override the local deserializer
 * annotation in OrderPositionReturned so this helper class can be mixed in to
 * force the usage of custom deserializers since the generated ones can't handle
 * the missing type discriminator...
 */
@JsonDeserialize(using = OrderPositionReturnedDeserializer.class)
public abstract class OrderPositionReturnedMixIn 
{

}