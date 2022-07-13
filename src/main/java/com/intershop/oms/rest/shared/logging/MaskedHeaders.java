package com.intershop.oms.rest.shared.logging;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import jakarta.ws.rs.core.MultivaluedHashMap;
import jakarta.ws.rs.core.MultivaluedMap;

public class MaskedHeaders<K, V> extends MultivaluedHashMap<K, V>
{
    private static final long serialVersionUID = 1L;

    // disabled for use in testframework
    private static final List<String> SENSITIVE_HEADERS = new ArrayList<>();
    //private static final List<String> SENSITIVE_HEADERS = Arrays.asList("authorization", "token", "private-key");

    private MaskedHeaders(MultivaluedMap<K, V> map)
    {
        super(map);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (Entry<K, List<V>> entry : entrySet())
        {
            sb.append("  ");
            sb.append(StringUtils.rightPad(String.join("", StringUtils.left(entry.getKey().toString(), 20), ":    "), 25));
            if (isSensitiveInformation(entry.getKey().toString()))
            {
                sb.append("******");
            }
            else
            {
                sb.append(StringUtils.join(entry.getValue(), ','));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static boolean isSensitiveInformation(String headerName)
    {
        return SENSITIVE_HEADERS.contains(headerName);

    }

    public static <K, V> MaskedHeaders<K, V> of(MultivaluedMap<K, V> map)
    {
        return new MaskedHeaders<K, V>(map);
    }
}
