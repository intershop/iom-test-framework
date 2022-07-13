package com.intershop.oms.test.servicehandler.orderstateservice.v1.mapping;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.intershop.oms.rest.orderstate.v1.model.OrderStateOrderPositionReturned;
import com.intershop.oms.rest.orderstate.v1.model.OrderStateOrderPositionReturnedQuantities;
import com.intershop.oms.rest.orderstate.v1.model.OrderStateOrderPositionReturnedUnits;

public class OrderStateOrderPositionReturnedDeserializer extends StdDeserializer<OrderStateOrderPositionReturned>
{
    private static final long serialVersionUID = 6044487482537011757L;
    private static final Logger log = Logger.getLogger(OrderStateOrderPositionReturnedDeserializer.class.getName());

    public OrderStateOrderPositionReturnedDeserializer()
    {
        this(OrderStateOrderPositionReturned.class);
    }

    public OrderStateOrderPositionReturnedDeserializer(Class<?> vc)
    {
        super(vc);
    }

    @Override
    public OrderStateOrderPositionReturned deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException
    {
        JsonNode tree = jp.readValueAsTree();
        Object deserialized = null;
        boolean typeCoercion = ctxt.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS);
        int match = 0;
        JsonToken token = tree.traverse(jp.getCodec()).nextToken();
        // deserialize OrderStateOrderPositionReturnedQuantities
        try
        {
            boolean attemptParsing = true;
            // ensure that we respect type coercion as set on the client ObjectMapper
            if (OrderStateOrderPositionReturnedQuantities.class.equals(Integer.class) || OrderStateOrderPositionReturnedQuantities.class.equals(Long.class) || OrderStateOrderPositionReturnedQuantities.class.equals(Float.class) || OrderStateOrderPositionReturnedQuantities.class.equals(Double.class) || OrderStateOrderPositionReturnedQuantities.class.equals(Boolean.class) || OrderStateOrderPositionReturnedQuantities.class.equals(String.class))
            {
                attemptParsing = typeCoercion;
                if (!attemptParsing)
                {
                    attemptParsing |= ((OrderStateOrderPositionReturnedQuantities.class.equals(Integer.class) || OrderStateOrderPositionReturnedQuantities.class.equals(Long.class)) && token == JsonToken.VALUE_NUMBER_INT);
                    attemptParsing |= ((OrderStateOrderPositionReturnedQuantities.class.equals(Float.class) || OrderStateOrderPositionReturnedQuantities.class.equals(Double.class)) && token == JsonToken.VALUE_NUMBER_FLOAT);
                    attemptParsing |= (OrderStateOrderPositionReturnedQuantities.class.equals(Boolean.class) && (token == JsonToken.VALUE_FALSE || token == JsonToken.VALUE_TRUE));
                    attemptParsing |= (OrderStateOrderPositionReturnedQuantities.class.equals(String.class) && token == JsonToken.VALUE_STRING);
                }
            }
            if (attemptParsing)
            {
                OrderStateOrderPositionReturnedQuantities orderStateOrderPositionReturnedQuantities = tree.traverse(jp.getCodec()).readValueAs(OrderStateOrderPositionReturnedQuantities.class);
                if (orderStateOrderPositionReturnedQuantities.getQuantity() != null && orderStateOrderPositionReturnedQuantities.getQuantity().intValue() != 0)
                {
                    deserialized = orderStateOrderPositionReturnedQuantities;
                    match++;
                    log.log(Level.FINER, "Input data matches schema 'OrderStateOrderPositionReturnedQuantities'");
                }
            }
        }
        catch (Exception e)
        {
            // deserialization failed, continue
            log.log(Level.FINER, "Input data does not match schema 'OrderStateOrderPositionReturnedQuantities'", e);
        }

        // deserialize OrderStateOrderPositionReturnedUnits
        try
        {
            boolean attemptParsing = true;
            // ensure that we respect type coercion as set on the client ObjectMapper
            if (OrderStateOrderPositionReturnedUnits.class.equals(Integer.class) || OrderStateOrderPositionReturnedUnits.class.equals(Long.class) || OrderStateOrderPositionReturnedUnits.class.equals(Float.class) || OrderStateOrderPositionReturnedUnits.class.equals(Double.class) || OrderStateOrderPositionReturnedUnits.class.equals(Boolean.class) || OrderStateOrderPositionReturnedUnits.class.equals(String.class))
            {
                attemptParsing = typeCoercion;
                if (!attemptParsing)
                {
                    attemptParsing |= ((OrderStateOrderPositionReturnedUnits.class.equals(Integer.class) || OrderStateOrderPositionReturnedUnits.class.equals(Long.class)) && token == JsonToken.VALUE_NUMBER_INT);
                    attemptParsing |= ((OrderStateOrderPositionReturnedUnits.class.equals(Float.class) || OrderStateOrderPositionReturnedUnits.class.equals(Double.class)) && token == JsonToken.VALUE_NUMBER_FLOAT);
                    attemptParsing |= (OrderStateOrderPositionReturnedUnits.class.equals(Boolean.class) && (token == JsonToken.VALUE_FALSE || token == JsonToken.VALUE_TRUE));
                    attemptParsing |= (OrderStateOrderPositionReturnedUnits.class.equals(String.class) && token == JsonToken.VALUE_STRING);
                }
            }
            if (attemptParsing)
            {
                OrderStateOrderPositionReturnedUnits orderStateOrderPositionReturnedUnits = tree.traverse(jp.getCodec()).readValueAs(OrderStateOrderPositionReturnedUnits.class);
                if (orderStateOrderPositionReturnedUnits.getUnits() != null && !orderStateOrderPositionReturnedUnits.getUnits().isEmpty())
                {
                    deserialized = orderStateOrderPositionReturnedUnits;
                    match++;
                    log.log(Level.FINER, "Input data matches schema 'OrderStateOrderPositionReturnedUnits'");
                }
            }
        }
        catch (Exception e)
        {
            // deserialization failed, continue
            log.log(Level.FINER, "Input data does not match schema 'OrderStateOrderPositionReturnedUnits'", e);
        }

        if (match == 1)
        {
            OrderStateOrderPositionReturned ret = new OrderStateOrderPositionReturned();
            ret.setActualInstance(deserialized);
            return ret;
        }
        throw new IOException(String.format("Failed deserialization for OrderStateOrderPositionReturned: %d classes match result, expected 1", match));
    }

    /**
     * Handle deserialization of the 'null' value.
     */
    @Override
    public OrderStateOrderPositionReturned getNullValue(DeserializationContext ctxt) throws JsonMappingException
    {
        throw new JsonMappingException(ctxt.getParser(), "OrderStateOrderPositionReturned cannot be null");
    }
}