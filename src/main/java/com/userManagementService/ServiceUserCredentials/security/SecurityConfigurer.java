package com.userManagementService.ServiceUserCredentials.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.userManagementService.ServiceUserCredentials.services.MyUserDetailsService;
import com.userManagementService.ServiceUserCredentials.services.UserOperationService;
@Configuration
@EnableWebSecurity
@ComponentScan("com.userManagementService.ServiceUserCredentials.*")
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{
	@Autowired
	public UserOperationService userOperationService;
	@Autowired
	public MyUserDetailsService myUserDetailsService;
		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception{
		
		http
	    .csrf().disable()
	     // make sure we use stateless session; session won't be used to store user's state.
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            // handle an authorized attempts 
            .exceptionHandling().authenticationEntryPoint((req, rsp, e) -> rsp.sendError(HttpServletResponse.SC_UNAUTHORIZED))
        .and()
	    // Add a filter to validate user credentials and add token in the response header
		
	    // What's the authenticationManager()? 
	    // An object provided by WebSecurityConfigurerAdapter, used to authenticate the user passing user's credentials
	    // The filter needs this auth manager to authenticate the user.
        .addFilter(new JwtAuthenticationFilter(authenticationManager()))
        .authorizeRequests()
	    // allow all POST requests 
	    .antMatchers(HttpMethod.POST, "/auth").permitAll()
	    // any other requests must be authenticated
	    .antMatchers("/authapi/user/**").hasRole("ROLE_ADMIN")
	    .anyRequest().authenticated();
    }
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	@Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(myUserDetailsService);
        return daoAuthenticationProvider;
    }
	

}
