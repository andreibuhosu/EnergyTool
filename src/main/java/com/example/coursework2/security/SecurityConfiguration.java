package com.example.coursework2.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;



    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }





@Override
protected void configure(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/login", "/resources/**", "/css/**", "/fonts/**", "/img/**").permitAll()
            .antMatchers("/login/press", "/resources/**", "/css/**", "/fonts/**", "/img/**").permitAll()
            .antMatchers("/igse/propertycount", "/resources/**", "/css/**", "/fonts/**", "/img/**", "/js/**").permitAll()
            .antMatchers("/igse/semi-detached/3", "/resources/**", "/css/**", "/fonts/**", "/img/**", "/js/**").permitAll()
            .antMatchers("/api").permitAll()
            .antMatchers("/api1").permitAll()
            .antMatchers("/register").permitAll()
            .antMatchers("/managePS", "/resources/**", "/css/**", "/fonts/**", "/img/**", "/js/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .exceptionHandling().accessDeniedPage("/accessDenied")
            .and()
            .formLogin()
            .loginPage("/login").permitAll().successHandler(new AuthenticationSuccessHandler() {
                @Override
                public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                    String role = authentication.getAuthorities().iterator().next().getAuthority();
                    if (role.equals("Admin")) {
                        response.sendRedirect("/indexAdmin/");
                    } else if (role.equals("Customer")) {
                        response.sendRedirect("/indexCustomer/");
                    } else {
                        response.sendRedirect("/home");
                    }
                }
            })
            .and()
            .logout().invalidateHttpSession(true)
            .clearAuthentication(true)
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login").permitAll();
}
}
