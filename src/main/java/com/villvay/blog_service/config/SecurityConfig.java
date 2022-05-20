package com.villvay.blog_service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("manoj").password(passwordEncoder.encode("manoj123")).roles("USER")
                .and()
                .withUser("kasun").password(passwordEncoder.encode("kasun123")).roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/authors/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/authors").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/authors/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/authors/**").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/posts/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/posts").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/posts/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/posts/**").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/comments/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/comments").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/comments/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/comments/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}
