package com.intershop.oms.test.configuration;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import io.smallrye.config.WithName;

import java.util.Map;
import java.util.Optional;

@ConfigMapping(prefix = "testframework")
public interface Configuration {

    @WithName("default-endpoint")
    Optional<ServiceConfiguration> defaultEndpoint();

    @WithName("database")
    Map<String, ServiceConfiguration> databaseConfigs();

    @WithName("order-service")
    Map<String, ServiceConfiguration> orderServiceConfigs();

    @WithName("order-state-service")
    Map<String, ServiceConfiguration> orderStateServiceConfigs();

    @WithName("reservation-service")
    Map<String, ServiceConfiguration> reservationServiceConfigs();

    @WithName("inventory-service")
    Map<String, ServiceConfiguration> inventoryServiceConfigs();

    @WithName("rma-service")
    Map<String, ServiceConfiguration> rmaServiceConfigs();

    @WithName("schedule-service")
    Map<String, ServiceConfiguration> scheduleServiceConfigs();

    @WithName("supplier-service")
    Map<String, ServiceConfiguration> supplierServiceConfigs();

    @WithName("transmission-service")
    Map<String, ServiceConfiguration> transmissionServiceConfigs();

    @WithName("client-logging")
    @WithDefault("true")
    boolean clientLogging();

    @WithName("jwt-secret")
    Optional<String> jwtSecret();

    default ServiceConfiguration getDbServiceById(String id) {
        return Optional.ofNullable(databaseConfigs().get(id)).orElseThrow(() -> new IllegalArgumentException("db service config with name " + id + " not found!"));
    }

    default ServiceConfiguration getOrderServiceById(String id) {
        return Optional.ofNullable(orderServiceConfigs().get(id)).orElseThrow(() -> new IllegalArgumentException("order service config with name " + id + " not found!"));
    }

    default ServiceConfiguration getReservationServiceById(String id) {
        return Optional.ofNullable(reservationServiceConfigs().get(id)).orElseThrow(() -> new IllegalArgumentException("reservation service config with name " + id + " not found!"));
    }

    default ServiceConfiguration getInventoryServiceById(String id) {
        return Optional.ofNullable(inventoryServiceConfigs().get(id)).orElseThrow(() -> new IllegalArgumentException("inventory service config with name " + id + " not found!"));
    }

    default ServiceConfiguration getOrderStateServiceById(String id) {
        return Optional.ofNullable(orderStateServiceConfigs().get(id)).orElseThrow(() -> new IllegalArgumentException("order state service config with name " + id + " not found!"));
    }

    default ServiceConfiguration getRMAServiceById(String id) {
        return Optional.ofNullable(rmaServiceConfigs().get(id)).orElseThrow(() -> new IllegalArgumentException("RMA service config with name " + id + " not found!"));
    }

    default ServiceConfiguration getScheduleServiceById(String id) {
        return Optional.ofNullable(scheduleServiceConfigs().get(id)).orElseThrow(() -> new IllegalArgumentException("schedule service config with name " + id + " not found!"));
    }

    default ServiceConfiguration getSupplierServiceById(String id) {
        return Optional.ofNullable(supplierServiceConfigs().get(id)).orElseThrow(() -> new IllegalArgumentException("supplier service config with name " + id + " not found!"));
    }

    default ServiceConfiguration getTransmissionServiceById(String id) {
        return Optional.ofNullable(transmissionServiceConfigs().get(id)).orElseThrow(() -> new IllegalArgumentException("transmission service config with name " + id + " not found!"));
    }

}
