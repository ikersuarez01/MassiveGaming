package com.IkerLucia.MassiveGaming.Security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.IkerLucia.MassiveGaming.Services.RepositoryUserDetailsService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public RepositoryUserDetailsService userDetailService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom());
	}
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        //PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        //String encodedPassword = encoder.encode("Tapatapita123");

        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
      
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	//Autorizar css y las imagenes
    	http.authorizeRequests().antMatchers("/css/**", "/img/**").permitAll();
    	
        // Public pages
        http.authorizeRequests().antMatchers("/MassiveGaming").permitAll();
        http.authorizeRequests().antMatchers("/contacto").permitAll();
        http.authorizeRequests().antMatchers("/videojuegos").permitAll();
        http.authorizeRequests().antMatchers("/videojuegos/{nombre}").permitAll();
        http.authorizeRequests().antMatchers("/consolas").permitAll();
        http.authorizeRequests().antMatchers("/consolas/{nombre}").permitAll();
        http.authorizeRequests().antMatchers("/inicioSesionError").permitAll();
        http.authorizeRequests().antMatchers("/iniciarSesion").permitAll();
        http.authorizeRequests().antMatchers("/crearCuenta").permitAll();
        http.authorizeRequests().antMatchers("/cuentaCreada").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/loginerror").permitAll();
        // Private pages (all other pages)
        http.authorizeRequests().anyRequest().authenticated();
        
        

        // Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("username");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/MassiveGaming");
        http.formLogin().failureUrl("/loginerror");

        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/MassiveGaming");

    }

}