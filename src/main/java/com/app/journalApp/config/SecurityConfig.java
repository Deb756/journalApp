package com.app.journalApp.config;

import com.app.journalApp.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        return  httpSecurity
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(req -> req.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();




//        else (way)
////        here we are disabling csrf ( cross site request forgery )
//        httpSecurity.csrf(customizer -> customizer.disable());
//        httpSecurity.authorizeHttpRequests(req -> req.anyRequest().authenticated());
//        httpSecurity.formLogin(Customizer.withDefaults()); // enabling to access the endpoints for form login in browser
//        httpSecurity.httpBasic(Customizer.withDefaults()); // enabling to access the endpoints at postman
////        here we are making it stateless
//        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        return httpSecurity.build();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(MyUserDetailsService myUserDetailsService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); // it's a database auth provider
        provider.setUserDetailsService(myUserDetailsService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;
    }

////    to avoid default authentication like username and password in app.properties
//    @Bean
//    public UserDetailsService userDetailsService(){
//        return new InMemoryUserDetailsManager();
//    }
}