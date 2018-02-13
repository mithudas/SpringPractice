package com.springmvc.config;

import com.springmvc.handle.LogoutSuccessHandler;
import com.springmvc.handle.SuccessHandler;
import com.springmvc.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;


/**
 * Created by mithu on 20/1/18.
 */

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SuccessHandler successHandler;

    @Autowired
    LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    CustomUserDetailService customUserDetailService;

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
               .userDetailsService(customUserDetailService)
        ;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/home").hasRole("ADMIN")
                .antMatchers("/register").permitAll()
                .antMatchers("/aop*").permitAll()
                .antMatchers("/registerUser").permitAll()
                .antMatchers("/registrationConfirmation").permitAll()
                .anyRequest().authenticated()
                .and()
                .rememberMe().rememberMeCookieName("my-cookie").tokenValiditySeconds(3000)
                .tokenRepository(persistentTokenRepository())
                .and()
                .formLogin()
                .loginPage("/login").permitAll().successHandler(successHandler)
                .loginProcessingUrl("/myLoginUrl")
                .and()
                .logout().permitAll().
                logoutSuccessHandler(logoutSuccessHandler)
                .logoutRequestMatcher(new AntPathRequestMatcher("/myLogOut", "GET"))

        ;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

}
