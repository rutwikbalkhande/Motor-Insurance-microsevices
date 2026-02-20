package com.icwd.gateway.ApiGateway.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

import org.springframework.stereotype.Component;

@Component
public class GatewayMetricsService {

    private final MeterRegistry registry;

    public GatewayMetricsService(MeterRegistry registry) {
        this.registry = registry;
    }

    public void record(String userId, String api, String method) {

        Counter.builder("gateway_api_requests_total")
                .description("Total API requests through Gateway")
                .tag("user", userId)
                .tag("api", api)
                .tag("method", method)
                .register(registry)
                .increment();
    }
}