package com.mycompany.springinactionproject.SpringInActionProject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(encoder());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/design/**", "/orders").permitAll()
//                .hasAnyRole("USER")
                .antMatchers("/", "/**").permitAll()
                .and()
//                .csrf().disable()
                .csrf()
                .disable()
//                .ignoringAntMatchers("/h2-console/**", "/api/**", "/api", "/ingredients/**", "/tacos/*")
//                .and().headers().frameOptions().sameOrigin()
//                .and()
                .formLogin()
                .loginPage("/login").successForwardUrl("/design")
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }

}
