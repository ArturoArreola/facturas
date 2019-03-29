package com.transportesarreola.facturas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/","/css/**","/js/**","/images/**").permitAll()
                .antMatchers("/listar/**").hasAnyRole("USER")
                .antMatchers("/ver/**").hasAnyRole("USER")
                .antMatchers("/uploads/**").hasAnyRole("USER")
                .antMatchers("/form/**").hasAnyRole("USER")
                .antMatchers("/eliminar/**").hasAnyRole("USER")
                .antMatchers("/factura/**").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login")
                    .permitAll()
                .and()
                    .logout().permitAll();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder build) throws Exception{
        UserBuilder users = User.withDefaultPasswordEncoder();
        
        build.inMemoryAuthentication()
                .withUser(users.username("admin").password("123456").roles("ADMIN","USER"))
                .withUser(users.username("user").password("123456").roles("USER"));
    }
}