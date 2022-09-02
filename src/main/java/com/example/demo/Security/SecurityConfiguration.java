package com.example.demo.Security;

import com.example.demo.Services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConf) throws Exception {
        return authConf.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();

        httpSecurity.authorizeRequests()

                .mvcMatchers(HttpMethod.POST, "/new-user").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST,"/new-accountHolder").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST,"/new-thirdPartUser").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST,"/new-admin").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/createRole-admin-user").hasRole( "ADMIN")
                .mvcMatchers(HttpMethod.POST, "/createRole-user").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/createRole-thirdPartUser").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/createRole-accountHolder").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/add-Account").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/add-SavingAccount").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/add-CreditCardAccount").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/get-AllBalance/{id}").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/set-balance/{id}").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/delete-account/{id}").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/get-balance/{id}").hasAnyRole("ADMIN","ACCOUNT_HOLDER")
                .mvcMatchers(HttpMethod.PUT,"/account-transfer/{id}/{recId}").hasAnyRole("ADMIN","ACCOUNT_HOLDER")
                .mvcMatchers(HttpMethod.PATCH, "/modify-password").hasAnyRole("ADMIN","USER","ACCOUNT_HOLDER","THIRD_PART_USER")
                .mvcMatchers(HttpMethod.POST,"/transfer-send/{key}").hasRole("THIRD_PART_USER")
                .mvcMatchers(HttpMethod.POST,"/transfer-receive/{key}").hasRole("THIRD_PART_USER")

                .anyRequest().permitAll();

        httpSecurity.csrf().disable();

        return httpSecurity.build();

    }

}
