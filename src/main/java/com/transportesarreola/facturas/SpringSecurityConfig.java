package com.transportesarreola.facturas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests()
        http
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/js/**", "/images/**", "/products/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/products/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/products/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/products/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/products/**").permitAll()
                .antMatchers("/listar/**").hasAnyRole("USER")
                .antMatchers("/ver/**").hasAnyRole("USER")
                .antMatchers("/uploads/**").hasAnyRole("USER")
                .antMatchers("/form/**").hasAnyRole("USER")
                .antMatchers("/eliminar/**").hasAnyRole("USER")
                .antMatchers("/factura/**").hasAnyRole("USER")
                .anyRequest().permitAll()
                .and()
                    .formLogin().loginPage("/login")
                    .permitAll()
                .and()
                    .logout().permitAll()
                .and().csrf().ignoringAntMatchers("/api/products/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder build) throws Exception {
        UserBuilder users = User.withDefaultPasswordEncoder();

        build.inMemoryAuthentication()
                .withUser(users.username("admin").password("123456").roles("ADMIN", "USER"))
                .withUser(users.username("user").password("123456").roles("USER"));
    }
}
