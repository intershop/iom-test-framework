package com.intershop.oms.test.servicehandler.orderstateservice.v1.mapping;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * registering a module at the mapper did not override the local deserializer
 * annotation in OrderPositionReturned so this helper class can be mixed in to
 * force the usage of custom deserializers since the generated ones can't handle
 * the missing type discriminator...
 */
@JsonDeserialize(using = OrderStateOrderPositionReturnedDeserializer.class)
public class OrderStateOrderPositionReturnedMixIn
{

}