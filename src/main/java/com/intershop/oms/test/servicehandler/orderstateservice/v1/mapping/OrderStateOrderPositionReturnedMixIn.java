package com.intershop.oms.test.servicehandler.orderstateservice.v1.mapping;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = OrderStateOrderPositionReturnedDeserializer.class)
public class OrderStateOrderPositionReturnedMixIn
{

}