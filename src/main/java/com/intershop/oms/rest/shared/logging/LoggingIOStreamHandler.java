package com.intershop.oms.rest.shared.logging;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientResponseContext;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.WriterInterceptorContext;

public class LoggingIOStreamHandler
{
    public static String readEntity(ClientResponseContext responseContext) throws IOException
    {

        InputStream inputStream = responseContext.getEntityStream();

        byte[] bytes = new byte[1024];

        int read = -1;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((read = inputStream.read(bytes)) != -1)
        {
            bos.write(bytes, 0, read);
        }

        String responseContent;

        if (StringUtils.equals("gzip", responseContext.getHeaderString(HttpHeaders.CONTENT_ENCODING)))
        {
            try (InputStream isCompressed = new GZIPInputStream(new ByteArrayInputStream(bos.toByteArray())))
            {
                ByteArrayOutputStream bosUncompressed = new ByteArrayOutputStream();
                while((read = isCompressed.read(bytes)) != -1)
                {
                    bosUncompressed.write(bytes, 0, read);
                }

                responseContent = bosUncompressed.toString("UTF-8");

            }

        }
        else
        {
            responseContent = bos.toString("UTF-8");
        }

        responseContent = tryFormatJson(responseContent);

        responseContext.setEntityStream(new ByteArrayInputStream(bos.toByteArray()));
        return responseContent;
    }

    private static String tryFormatJson(String responseContent)
    {
        if (isBlank(responseContent) || !(responseContent.startsWith("{") || responseContent.startsWith("[")))
        {
            return responseContent;
        }

        ObjectMapper om = new ObjectMapper();
        om.enable(SerializationFeature.INDENT_OUTPUT);
        try
        {
            JsonNode jsonNode = om.readTree(responseContent);
            return om.writeValueAsString(jsonNode);
        }
        catch(JsonProcessingException e)
        {
            return responseContent;
        }
    }

    public static String readEntity(WriterInterceptorContext writerInterceptorContext) throws IOException
    {
        OutputStream outputStream = writerInterceptorContext.getOutputStream();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        writerInterceptorContext.setOutputStream(byteArrayOutputStream);

        writerInterceptorContext.proceed();

        byte[] byteArray = byteArrayOutputStream.toByteArray();
        outputStream.write(byteArray);
        writerInterceptorContext.setOutputStream(outputStream);

        String payloadBody = new String(byteArray);
        payloadBody = tryFormatJson(payloadBody);

        return payloadBody;

    }

}
