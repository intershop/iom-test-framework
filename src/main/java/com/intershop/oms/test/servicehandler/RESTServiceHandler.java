package com.intershop.oms.test.servicehandler;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;

import com.intershop.oms.rest.shared.ApiClient;
import com.intershop.oms.rest.shared.auth.HttpBasicAuth;
import com.intershop.oms.rest.shared.auth.HttpBearerAuth;
import com.intershop.oms.test.configuration.ConfigBuilder;
import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.configuration.ServiceEndpoint;
import com.intershop.oms.test.util.AuthTokenUtil;

public abstract class RESTServiceHandler implements OMSServiceHandler
{
    private static final String BEARER_AUTH = "bearerAuth";
    private static final String BASIC_AUTH = "basicAuth";
    protected String defaultProtocol = null;
    protected String defaultHost = null;
    protected Integer defaultPort = null;
    protected ApiClient apiClient;
    protected String defaultBasePath = null;

    protected static final String HTTP_HEADER_LOCATION = "Location";

    /**
     * @param logger
     *            <b>NOTE:</b> this is not being used yet - the intention is to
     *            pass this on to the ApiClient for request/response logging -
     *            still to be implemented
     */
    protected RESTServiceHandler(ServiceConfiguration serviceConfig, String basePath, Logger logger)
    {
        Optional<ServiceConfiguration> defaultEndpoint = ConfigBuilder.getDefault().defaultEndpoint();

        ServiceConfiguration credentialsConfig = serviceConfig.bearerAuthToken().isPresent()
                        || serviceConfig.username().isPresent() ? serviceConfig
                                        : defaultEndpoint.orElseThrow(() -> new RuntimeException(
                                                        "no credentials are set, default-endpoint not configured"));
        if (credentialsConfig.bearerAuthToken().isPresent())
        {
            this.apiClient = getBearerAuthApiClient(credentialsConfig.bearerAuthToken().get());
        }
        else
        {
            this.apiClient = getBasicAuthApiClient(credentialsConfig.username().get(), credentialsConfig.password().get());
        }

        ServiceEndpoint endpoint = serviceConfig.serviceEndpoint().orElseGet(
                        () -> defaultEndpoint.isPresent() && defaultEndpoint.get().serviceEndpoint().isPresent()
                                        ? defaultEndpoint.get().serviceEndpoint().get()
                                        : null);

        if(endpoint == null)
        {
            throw new RuntimeException("neither service config nor default-endpoint are configured");
        }

        defaultProtocol = endpoint.protocol().orElse("http");
        defaultHost = endpoint.host();
        defaultPort = endpoint.port().orElse(80);
        defaultBasePath = basePath;

        if (ConfigBuilder.getDefault().clientLogging())
        {
            this.apiClient.setDebugging(true);
        }

        apiClient.setBasePath(defaultProtocol + "://" + defaultHost + ":" + defaultPort + defaultBasePath);

    }

    @Override
    public ApiClient getApiClient()
    {
        return apiClient;
    }

    @Override
    public void setBasicAuth(String user, String password)
    {
        HttpBasicAuth basicAuth = (HttpBasicAuth)apiClient.getAuthentication(BASIC_AUTH);
        basicAuth.setUsername(user);
        basicAuth.setPassword(password);

        HttpBearerAuth tokenAuth = (HttpBearerAuth)apiClient.getAuthentication(BEARER_AUTH);
        tokenAuth.setBearerToken(null);
    }

    @Override
    public void setTokenAuth(String token)
    {
        HttpBasicAuth basicAuth = (HttpBasicAuth)apiClient.getAuthentication(BASIC_AUTH);
        basicAuth.setUsername(null);
        basicAuth.setPassword(null);

        HttpBearerAuth tokenAuth = (HttpBearerAuth)apiClient.getAuthentication(BEARER_AUTH);
        tokenAuth.setBearerToken(token);
    }

    @Override
    public void setTokenAuthForUser(String username)
    {
        setTokenAuth(AuthTokenUtil.getDefaultJWTForUser(username));
    }

    protected abstract Collection<Object> unwrapApiClient();

    @Override
    @SuppressWarnings("unchecked")
    public <T> T unwrapApiClient(Class<T> clazz)
    {
        Collection<Object> specificApiClients = unwrapApiClient();
        if (specificApiClients == null || specificApiClients.size() == 0)
        {
            throw new IllegalArgumentException("ServiceHandler doesn't expose an API Client");
        }
        Optional<Object> wrappedClient = specificApiClients.stream()
                        .filter(client -> clazz.isAssignableFrom(client.getClass())).findAny();
        if (wrappedClient.isPresent())
        {
            return (T)wrappedClient.get();
        }

        throw new IllegalArgumentException(String.format(
                        "Requested class '%s' doesn't match actual class(es) of the api client(s): '%s'",
                        clazz.getName(),
                        specificApiClients.stream().map(c -> c.getClass().getName()).collect(Collectors.joining(","))));

    }

    /**
     * Returns a {@link ApiClient} for Basic Auth.
     *
     * @param wsUser
     * @param wsPassword
     * @return a {@link ApiClient} for Basic Auth
     */
    protected static ApiClient getBasicAuthApiClient(String wsUser, String wsPassword)
    {

        ApiClient apiClient = new ApiClient();
        apiClient.setConnectTimeout(5000);
        apiClient.setReadTimeout(30000);
        apiClient.setUsername(wsUser);
        apiClient.setPassword(wsPassword);

        return apiClient;
    }

    /**
     * Returns an {@link ApiClient} for Bearer Token Auth.
     *
     * @param wsUser
     * @return an ApiClient for Bearer Token Auth
     */
    protected static ApiClient getBearerAuthApiClient(String wsUser)
    {

        ApiClient apiClient = new ApiClient();
        apiClient.setConnectTimeout(5000);
        apiClient.setReadTimeout(30000);

        // create JWT

        String jwt = AuthTokenUtil.getDefaultJWTForUser(wsUser);

        HttpBearerAuth bearerAuth = (HttpBearerAuth)apiClient.getAuthentication(BEARER_AUTH);
        bearerAuth.setBearerToken(jwt);

        return apiClient;
    }

    protected Long parseIdFromLocation(String location)
    {
        return Long.parseLong(location.substring(location.lastIndexOf('/') + 1));
    }

}
