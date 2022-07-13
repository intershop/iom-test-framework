package com.intershop.oms.test.servicehandler.orderstateservice.v2_0.mapping;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = OrderPositionReturnedDeserializer.class)
public abstract class OrderPositionReturnedMixIn
{

}