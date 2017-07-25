package com.softserve.edu.lv251.config;

import com.softserve.edu.lv251.service.impl.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    public WebSecurityConfig() {
        super(false);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() { return new BCryptPasswordEncoder();}

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailsService);
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http.csrf()
                    .disable()
                    .authorizeRequests()
                    .antMatchers("/resources/**", "/**").permitAll()
                    .anyRequest().permitAll()
                    .and();

            http.formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/j_spring_security_check")
                    .failureUrl("/clinics")
                    .usernameParameter("j_username")
                    .passwordParameter("j_password")
                    .permitAll();

            http.logout()
                    .permitAll()
                    .logoutUrl("/index")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

}