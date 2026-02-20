package com.icwd.gateway.ApiGateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class ApiHitGlobalFilter implements GlobalFilter, Ordered {

    private final GatewayMetricsService metricsService;

    public ApiHitGlobalFilter(GatewayMetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,
                             GatewayFilterChain chain) {

        String user =
                exchange.getRequest().getHeaders().getFirst("X-USER-NAME");

        if (user == null) {
            user = "anonymous";
        }

        String api = exchange.getRequest().getURI().getPath();
        String method = exchange.getRequest().getMethod().name();

        metricsService.record(user, api, method);

        return chain.filter(exchange);
    }

    // ✅ Run AFTER JWT filter
    @Override
    public int getOrder() {
        return 1;
    }
}