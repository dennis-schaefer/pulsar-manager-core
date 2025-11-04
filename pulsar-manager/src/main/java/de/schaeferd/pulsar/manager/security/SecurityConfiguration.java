package de.schaeferd.pulsar.manager.security;

import com.vaadin.flow.spring.security.VaadinAwareSecurityContextHolderStrategyConfiguration;
import com.vaadin.flow.spring.security.VaadinSecurityConfigurer;
import com.vaadin.hilla.route.RouteUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Import(VaadinAwareSecurityContextHolderStrategyConfiguration.class)
class SecurityConfiguration
{
    @Bean
    @Order(2)
    SecurityFilterChain appSecurityFilterChain(HttpSecurity http, RouteUtil routeUtil,
                                               @Value("${server.port}") Integer serverPort) throws Exception
    {
        http
                .securityMatcher(request -> request.getLocalPort() == serverPort)
                .authorizeHttpRequests(registry ->
                        registry.requestMatchers(routeUtil::isRouteAllowed).permitAll())
                .with(VaadinSecurityConfigurer.vaadin(), configurer ->
                        configurer.loginView("/login", "/"));

        return http.build();
    }

    @Bean
    @Order(1)
    SecurityFilterChain actuatorSecurityFilterChain(HttpSecurity http) throws Exception
    {
        http
                .securityMatcher(EndpointRequest.toAnyEndpoint())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
