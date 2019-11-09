package com.chi.twitter.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
 */

@EnableWebSecurity
@EnableGlobalMethodSecurity(
    prePostEnabled = true // for the use of @PreAuthorize and @PostAuthorize
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
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
            .authorizeRequests()
                .antMatchers("/static/**", "/index").permitAll()
        .and()
            .formLogin()
                /*
                    Default login page is /login with an HTTP get
                    Default login error url is login/?error
                 */
                .loginPage("/login").defaultSuccessUrl("/tweets").permitAll()
                .and()
                // Default logout url is login/?logout but it is better to not use GET parameter
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailsService).passwordEncoder(this.passwordEncoder())
        .and()
            .inMemoryAuthentication()
                .withUser("chi").password(passwordEncoder().encode("1234")).roles("USER")
                .and()
                .withUser("terryleong").password(passwordEncoder().encode("1234")).roles("USER")
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
                .and()
                .withUser("admin").password("admin").roles("ADMIN");
    }
}
