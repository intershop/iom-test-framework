package com.intershop.oms.test.util;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * a helper class to be able to use this in OpenApi generated classes
 *
 * Instead of [element1, element2, ...] it just prints the elements string representations as a comma separated list in toString()
 *
 * @param <K>
 */
public class CommaSeparatedList<K> extends ArrayList<K>
{
    private static final long serialVersionUID = 1L;

    @Override
    public String toString()
    {
        return stream().map(Object::toString).collect(Collectors.joining(","));
    }
}
