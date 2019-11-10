package com.chi.twitter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*
    See more on Java Config for Spring Security: https://spring.io/blog/2013/07/03/spring-security-java-config-preview-web-security/
    This class is now using multiple HTTP setup, see: https://docs.spring.io/spring-security/site/docs/4.2.x/reference/htmlsingle/#multiple-httpsecurity
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    prePostEnabled = true // for the use of @PreAuthorize and @PostAuthorize
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Configuration
    @Order(1) // since /h2-console/** is more specific, we want to configure it first before hitting DefaultSecurityConfig below
    public static class H2ConsoleSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            /*
                To enable access to the H2 database console under Spring Security you need to change three things:

                1. Allow all access to the url path /h2-console/**.
                2. Disable CRSF (Cross-Site Request Forgery). By default, Spring Security will protect against CRSF attacks.
                3. Since the H2 database console runs inside a frame, you need to enable this in in Spring Security.

                See: https://springframework.guru/using-the-h2-database-console-in-spring-boot-with-spring-security/
             */
            http.antMatcher("/h2-console/**").authorizeRequests().anyRequest().hasRole("ADMIN")
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();
        }
    }

    @Configuration
    @Order(2)
    public static class DefaultSecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        protected UserDetailsService userDetailsService;

        /*
            In Spring, one must provide a password encoder for normal password. Otherwise, you will get:
            There is no PasswordEncoder mapped for the id "null"
         */
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // https://docs.spring.io/spring-security/site/docs/4.2.12.RELEASE/apidocs/org/springframework/security/config/annotation/web/builders/HttpSecurity.html
            http
                // Note that the matchers are considered in order. The more specific url access rule should be stated first
                .authorizeRequests().antMatchers("/static/**", "/index").permitAll()
                .and()
                .formLogin()
                    /*
                        Default login page is /login with an HTTP get
                        Default login error url is login/?error
                     */
                    .loginPage("/login").defaultSuccessUrl("/tweets").permitAll()
                .and()
                // Default logout url is login/?logout but it is better to not use GET parameter
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/");
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth
                .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
                .and()
                .inMemoryAuthentication()
                .withUser("chi").password(passwordEncoder().encode("1234")).roles("USER", "ADMIN")
                .and()
                .withUser("terryleong").password(passwordEncoder().encode("1234")).roles("USER");
                /*
                    With Spring Security 5 you can prefix password with id of selected PasswordEncoder. If you want to
                    use plain password, then simply use {noop} prefix, this will delegate password encoder to
                    NoOpPasswordEncoder. For example:

                    .withUser("chi").password("{noop}1234").roles("USER")

                    '{noop}1234' is known as Password Storage Format which, in general, has the following structure:
                    '{id}encodedPassword'

                    Another way to configure this is to use InMemoryUserDetailsManager as UserDetailService.
                    See: https://www.mkyong.com/spring-boot/spring-security-there-is-no-passwordencoder-mapped-for-the-id-null/
                 */
        }
    }
}
