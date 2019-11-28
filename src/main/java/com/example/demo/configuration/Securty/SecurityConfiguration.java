package com.example.demo.configuration.Securty;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


		@Autowired
	private UserDetailsService userDetailsService;
		@Autowired
    private UserDetailsService myUserDetailsService;

    public SecurityConfiguration() {
    }

    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService);
			
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
                .authorizeRequests()
			    .antMatchers("/department/**").hasRole("ADMIN")
			/*.antMatchers("/people").hasAnyRole("ADMIN","USER")
			.antMatchers(HttpMethod.POST,"/pople").hasRole("ADMIN")*/
			    .antMatchers("/").permitAll()
                .antMatchers("/home/register").permitAll()
                .antMatchers("user/contacts/**").authenticated()
                .antMatchers("/api/public/test2").hasAuthority("ACCESS_TEST2")
                .and().formLogin().loginPage("/home/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/home/login");


    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.myUserDetailsService);

        return daoAuthenticationProvider;
    }


	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
