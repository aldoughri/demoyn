package com.example.demo.configuration;

import com.example.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

@Autowired
UserService appUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserDetailsService);
    }

    // This method is for overriding some configuration of the WebSecurity
    // If you want to ignore some request or request patterns then you can
    // specify that inside this method
    @Override
    public void configure(WebSecurity web)  {
        super.configure(web);
    }

    // detect user_role for every URL i have
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // starts authorizing configurations
                .authorizeRequests()
                // ignoring the "/", "/index.html", "/app/**", "/register",
                // "/favicon.ico"
                .antMatchers("/", "/index.html", "/app/**", "/register", "/favicon.ico").permitAll()
                // authenticate all remaining URLS
                .anyRequest().fullyAuthenticated().and()
                // enabling the basic authentication
                .httpBasic().and()
                // configuring the session as state less. Which means there is
                // no session in the server
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // disabling the CSRF - Cross Site Request Forgery
                .csrf().disable();
    }


    // set up users and roles for them
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER")
                .and()
                .withUser("admin").password("password").roles("ADMIN");
    }

}
