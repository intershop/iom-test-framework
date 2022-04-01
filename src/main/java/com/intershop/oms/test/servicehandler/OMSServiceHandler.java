package com.intershop.oms.test.servicehandler;

import com.intershop.oms.rest.shared.ApiClient;
import com.intershop.oms.test.util.Experimental;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface OMSServiceHandler
{
    final class LogHolder
    {
        static final Logger log = LoggerFactory.getLogger(RESTServiceHandler.class);
    }


    // FIXME ApiClient should be handled via abstraction
    @Experimental("Handling/Exposure of API clients is not stable and might be replaced / hidden")
    public ApiClient getApiClient();
    public void setBasicAuth(String user, String password);
    public void setTokenAuth(String token);
    public void setTokenAuthForUser(String username);
    public <T> T unwrapApiClient(Class<T> clazz);

}
