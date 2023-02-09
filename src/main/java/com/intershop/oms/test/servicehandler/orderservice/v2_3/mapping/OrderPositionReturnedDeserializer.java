package com.intershop.oms.test.servicehandler.orderservice.v2_3.mapping;

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
import com.intershop.oms.rest.order.v2_3.model.OrderPositionReturned;
import com.intershop.oms.rest.order.v2_3.model.OrderPositionReturnedQuantities;
import com.intershop.oms.rest.order.v2_3.model.OrderPositionReturnedUnits;

/**
 * a slightly changed deserializer that can handle the differences between OrderPositionsReturned
 * using quantities or units without the need to specify a type discriminator
 */
public class OrderPositionReturnedDeserializer extends StdDeserializer<OrderPositionReturned>
{
    private static final long serialVersionUID = -315148574189477454L;
    private static final Logger log = Logger.getLogger(OrderPositionReturnedDeserializer.class.getName());

    public OrderPositionReturnedDeserializer()
    {
        this(OrderPositionReturned.class);
    }

    public OrderPositionReturnedDeserializer(Class<?> vc)
    {
        super(vc);
    }

    @Override
    public OrderPositionReturned deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException
    {
        
        log.log(Level.FINER, "Custom OrderPositionReturnedDeserializer called");
        JsonNode tree = jp.readValueAsTree();
        Object deserialized = null;
        boolean typeCoercion = ctxt.isEnabled(MapperFeature.ALLOW_COERCION_OF_SCALARS);
        int match = 0;
        JsonToken token = tree.traverse(jp.getCodec()).nextToken();
        // deserialize OrderPositionReturnedQuantities
        try
        {
            boolean attemptParsing = true;
            // ensure that we respect type coercion as set on the client ObjectMapper
            if (OrderPositionReturnedQuantities.class.equals(Integer.class) || OrderPositionReturnedQuantities.class.equals(Long.class) || OrderPositionReturnedQuantities.class.equals(Float.class) || OrderPositionReturnedQuantities.class.equals(Double.class) || OrderPositionReturnedQuantities.class.equals(Boolean.class) || OrderPositionReturnedQuantities.class.equals(String.class))
            {
                attemptParsing = typeCoercion;
                if (!attemptParsing)
                {
                    attemptParsing |= ((OrderPositionReturnedQuantities.class.equals(Integer.class) || OrderPositionReturnedQuantities.class.equals(Long.class)) && token == JsonToken.VALUE_NUMBER_INT);
                    attemptParsing |= ((OrderPositionReturnedQuantities.class.equals(Float.class) || OrderPositionReturnedQuantities.class.equals(Double.class)) && token == JsonToken.VALUE_NUMBER_FLOAT);
                    attemptParsing |= (OrderPositionReturnedQuantities.class.equals(Boolean.class) && (token == JsonToken.VALUE_FALSE || token == JsonToken.VALUE_TRUE));
                    attemptParsing |= (OrderPositionReturnedQuantities.class.equals(String.class) && token == JsonToken.VALUE_STRING);
                }
            }
            if (attemptParsing)
            {
                OrderPositionReturnedQuantities orderPositionReturnedQuantities = tree.traverse(jp.getCodec()).readValueAs(OrderPositionReturnedQuantities.class);
                if (orderPositionReturnedQuantities.getQuantity() != null && orderPositionReturnedQuantities.getQuantity().intValue() != 0)
                {
                    deserialized = orderPositionReturnedQuantities;
                    match++;
                    log.log(Level.FINER, "Input data matches schema 'OrderPositionReturnedQuantities'");
                    OrderPositionReturned ret = new OrderPositionReturned();
                    ret.setActualInstance(deserialized);
                    return ret;
                }
            }
        }
        catch (Exception e)
        {
            // deserialization failed, continue
            log.log(Level.FINER, "Input data does not match schema 'OrderPositionReturnedQuantities'", e);
        }

        // deserialize OrderPositionReturnedUnits
        try {
            boolean attemptParsing = false;
            // ensure that we respect type coercion as set on the client ObjectMapper
            if (OrderPositionReturnedUnits.class.equals(Integer.class) || 
                            OrderPositionReturnedUnits.class.equals(Long.class) || 
                            OrderPositionReturnedUnits.class.equals(Float.class) || 
                            OrderPositionReturnedUnits.class.equals(Double.class) || 
                            OrderPositionReturnedUnits.class.equals(Boolean.class) || 
                            OrderPositionReturnedUnits.class.equals(String.class))
            {
                attemptParsing = typeCoercion;
                if (!attemptParsing)
                {
                    attemptParsing |= ((OrderPositionReturnedUnits.class.equals(Integer.class) || OrderPositionReturnedUnits.class.equals(Long.class)) && token == JsonToken.VALUE_NUMBER_INT);
                    attemptParsing |= ((OrderPositionReturnedUnits.class.equals(Float.class) || OrderPositionReturnedUnits.class.equals(Double.class)) && token == JsonToken.VALUE_NUMBER_FLOAT);
                    attemptParsing |= (OrderPositionReturnedUnits.class.equals(Boolean.class) && (token == JsonToken.VALUE_FALSE || token == JsonToken.VALUE_TRUE));
                    attemptParsing |= (OrderPositionReturnedUnits.class.equals(String.class) && token == JsonToken.VALUE_STRING);
                }
            }
            if (attemptParsing)
            {
                OrderPositionReturnedUnits orderPositionReturnedUnits = tree.traverse(jp.getCodec()).readValueAs(OrderPositionReturnedUnits.class);
                if (orderPositionReturnedUnits.getUnits() != null && !orderPositionReturnedUnits.getUnits().isEmpty())
                {
                    deserialized = orderPositionReturnedUnits;
                    // TODO: there is no validation against JSON schema constraints
                    // (min, max, enum, pattern...), this does not perform a strict JSON
                    // validation, which means the 'match' count may be higher than it should be.
                    match++;
                    log.log(Level.FINER, "Input data matches schema 'OrderPositionReturnedUnits'");
                }
            }
        }
        catch (Exception e)
        {
            // deserialization failed, continue
            log.log(Level.FINER, "Input data does not match schema 'OrderPositionReturnedUnits'", e);
        }

        if (match == 1)
        {
            log.log(Level.FINER, "Deserialized OrderPositionReturned as:\n "+ deserialized);

            OrderPositionReturned ret = new OrderPositionReturned();
            ret.setActualInstance(deserialized);
            return ret;
        }
        throw new IOException(String.format("Failed deserialization for OrderPositionReturned: %d classes match result, expected 1. last deserialized: %s", match, deserialized.toString()));
    }

    /**
     * Handle deserialization of the 'null' value.
     */
    @Override
    public OrderPositionReturned getNullValue(DeserializationContext ctxt) throws JsonMappingException
    {
        throw new JsonMappingException(ctxt.getParser(), "OrderPositionReturned cannot be null");
    }
}
