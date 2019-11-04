package com.example.demo.configuration;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

import javax.security.sasl.AuthorizeCallback;

public class AouthService extends AuthorizationServerConfigurerAdapter {
    final AuthenticationManager authenticationManager;

    public AouthService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        super.configure(clients);
clients.inMemory();

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        super.configure(endpoints);
        endpoints.authenticationManager(this.authenticationManager);
    }
}
