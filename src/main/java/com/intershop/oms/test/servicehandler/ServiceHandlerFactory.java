package com.intershop.oms.test.servicehandler;

import com.intershop.oms.test.configuration.ConfigBuilder;
import com.intershop.oms.test.configuration.Configuration;
import com.intershop.oms.test.configuration.ServiceConfiguration;
import com.intershop.oms.test.servicehandler.omsdb.OMSDbHandler;
import com.intershop.oms.test.servicehandler.omsdb.v1.OMSDbHandlerProviderV1;
import com.intershop.oms.test.servicehandler.orderservice.OMSOrderServiceHandler;
import com.intershop.oms.test.servicehandler.orderservice.v2_0.OMSOrderServiceHandlerProviderV2;
import com.intershop.oms.test.servicehandler.orderservice.v2_1.OMSOrderServiceHandlerProviderV2_1;
import com.intershop.oms.test.servicehandler.orderservice.v2_2.OMSOrderServiceHandlerProviderV2_2;
import com.intershop.oms.test.servicehandler.orderstateservice.OMSOrderStateServiceHandler;
import com.intershop.oms.test.servicehandler.orderstateservice.v1.OMSOrderStateServiceHandlerProviderV1;
import com.intershop.oms.test.servicehandler.orderstateservice.v2_0.OMSOrderStateServiceHandlerProviderV2_0;
import com.intershop.oms.test.servicehandler.rmaservice.OMSReturnRequestServiceHandler;
import com.intershop.oms.test.servicehandler.rmaservice.v2.OMSReturnRequestServiceHandlerProviderV2;
import com.intershop.oms.test.servicehandler.rmaservice.v2_10.OMSReturnRequestServiceHandlerProviderV2_10;
import com.intershop.oms.test.servicehandler.scheduleservice.OMSScheduleServiceHandler;
import com.intershop.oms.test.servicehandler.scheduleservice.v1.OMSScheduleServiceHandlerProviderV1;
import com.intershop.oms.test.servicehandler.supplierservice.OMSSupplierServiceHandler;
import com.intershop.oms.test.servicehandler.supplierservice.v1.OMSSupplierServiceHandlerProviderV1;
import com.intershop.oms.test.servicehandler.supplierservice.v2_10.OMSSupplierServiceHandlerProviderV2_10;
import com.intershop.oms.test.servicehandler.supplierservice.v2_11.OMSSupplierServiceHandlerProviderV2_11;
import com.intershop.oms.test.servicehandler.transmissionservice.OMSTransmissionServiceHandler;
import com.intershop.oms.test.servicehandler.transmissionservice.v2_0.OMSTransmissionServiceHandlerProviderV2;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@SuppressWarnings("rawtypes")
public class ServiceHandlerFactory
{
    public static final String DEFAULT_ID = "default";
    private static final ConcurrentHashMap<Class, Map<String, ServiceProvider>> registeredImpls =
                    new ConcurrentHashMap<>();
    private static final Configuration defaultConfig = ConfigBuilder.getDefault();

    static
    {
        // db service
        registerServiceHandler(OMSDbHandler.class, new OMSDbHandlerProviderV1());

        // order service
        registerServiceHandler(OMSOrderServiceHandler.class, new OMSOrderServiceHandlerProviderV2());
        registerServiceHandler(OMSOrderServiceHandler.class, new OMSOrderServiceHandlerProviderV2_1());
        registerServiceHandler(OMSOrderServiceHandler.class, new OMSOrderServiceHandlerProviderV2_2());

        // order state service
        registerServiceHandler(OMSOrderStateServiceHandler.class, new OMSOrderStateServiceHandlerProviderV1());
        registerServiceHandler(OMSOrderStateServiceHandler.class, new OMSOrderStateServiceHandlerProviderV2_0());

        // RMA
        registerServiceHandler(OMSReturnRequestServiceHandler.class, new OMSReturnRequestServiceHandlerProviderV2());
        registerServiceHandler(OMSReturnRequestServiceHandler.class, new OMSReturnRequestServiceHandlerProviderV2_10());

        // Schedule
        registerServiceHandler(OMSScheduleServiceHandler.class, new OMSScheduleServiceHandlerProviderV1());

        // supplier service / "communication"
        registerServiceHandler(OMSSupplierServiceHandler.class, new OMSSupplierServiceHandlerProviderV1());
        registerServiceHandler(OMSSupplierServiceHandler.class, new OMSSupplierServiceHandlerProviderV2_10());
        registerServiceHandler(OMSSupplierServiceHandler.class, new OMSSupplierServiceHandlerProviderV2_11());

        // transmission service
        registerServiceHandler(OMSTransmissionServiceHandler.class, new OMSTransmissionServiceHandlerProviderV2());

    }

    @SuppressWarnings("unchecked")
    private static <T> T createServiceHandler(Class<T> clazz, OMSDbHandler dbHandler,
                    ServiceConfiguration serviceConfig)
    {
        Map<String, ServiceProvider> services = registeredImpls.get(clazz);
        if (services == null)
        {
            throw new RuntimeException("No services registered for class: " + clazz.getSimpleName());
        }

        ServiceProvider provider = services.get(serviceConfig.getVersion());
        if (provider == null)
        {
            throw new RuntimeException("Provider not registered for type: " + serviceConfig.getVersion());
        }

        return (T)provider.createServiceHandler(dbHandler, serviceConfig);
    }

    public static OMSOrderServiceHandler getOrderServiceHandler(String id)
    {
        return getServiceHandler(OMSOrderServiceHandler.class, id, defaultConfig::getOrderServiceById);
    }

    public static OMSReturnRequestServiceHandler getReturnRequestServiceHandler(String id)
    {
        return getServiceHandler(OMSReturnRequestServiceHandler.class, id, defaultConfig::getRMAServiceById);
    }

    public static OMSSupplierServiceHandler getSupplierServiceHandler(String id)
    {
        return getServiceHandler(OMSSupplierServiceHandler.class, id, defaultConfig::getSupplierServiceById);
    }

    public static OMSTransmissionServiceHandler getTransmissionServiceHandler(String id)
    {
        return getServiceHandler(OMSTransmissionServiceHandler.class, id, defaultConfig::getTransmissionServiceById);
    }

    public static OMSScheduleServiceHandler getScheduleServiceHandler(String id)
    {
        return getServiceHandler(OMSScheduleServiceHandler.class, id, defaultConfig::getScheduleServiceById);
    }

    public static OMSOrderStateServiceHandler getOrderStateServiceHandler(String id)
    {
        return getServiceHandler(OMSOrderStateServiceHandler.class, id, defaultConfig::getOrderStateServiceById);
    }

    private static <T> T getServiceHandler(Class<T> clazz, String serviceConfigurationId,
                    Function<String, ServiceConfiguration> configProviderFunc)
    {
        if (StringUtils.isBlank(serviceConfigurationId))
        {
            serviceConfigurationId = DEFAULT_ID;
        }
        return createServiceHandler(clazz, getDbHandler(), configProviderFunc.apply(serviceConfigurationId));
    }

    public static OMSDbHandler getDbHandler()
    {
        return createServiceHandler(OMSDbHandler.class, null, defaultConfig.getDbServiceById(DEFAULT_ID));
    }

    public static <T> void registerServiceHandler(Class<T> serviceType, ServiceProvider<T> serviceProvider)
    {
        ServiceProvider previousVal = registeredImpls.computeIfAbsent(serviceType, key -> new HashMap<>())
                        .putIfAbsent(serviceProvider.getVersion(), serviceProvider);
        if (previousVal != null)
        {
            throw new IllegalArgumentException(
                            String.format("A service handler of TYPE: %s - VERSION: %s - has already been registered."
                                                            + " Overwriting this is not allowed, please register "
                                                            + "custom implementations with a "
                                                            + "different version string or use some prefix.",
                                            serviceType.getName(),
                                            serviceProvider.getVersion()));
        }
    }

}
