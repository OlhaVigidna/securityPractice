package com.example.demo.configs;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter{


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("user").password("{noop}user").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/cars").permitAll()
                .antMatchers(HttpMethod.POST, "/create", "/car{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/car{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/car{id}").hasAnyRole("ADMIN", "USER")
                .and()
                .addFilterBefore(new LoginationFilterForAdmins("/login",authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new CheckTokenForAllUsers(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable();
    }
}
