package com.savelife.mvc.configuration.security;

import com.savelife.mvc.configuration.security.CustomBasicAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.savedrequest.NullRequestCache;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static String REALM="MY_TEST_REALM";

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("1").password("1").roles("ADMIN").and()
				.withUser("2").password("2").roles("USER")
		;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
                .authorizeRequests()
					.anyRequest().authenticated()
//                    .antMatchers(HttpMethod.GET, "/**").hasRole("ADMIN")
//                    .antMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
//                    .antMatchers(HttpMethod.PATCH, "/**").hasRole("ADMIN")
					.and()
				.httpBasic()
					.realmName(REALM)
					.authenticationEntryPoint(getBasicAuthEntryPoint())
					.and()
				.requestCache()
					.requestCache(new NullRequestCache())
					.and();
	}

	@Bean
	public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
		return new CustomBasicAuthenticationEntryPoint();
	}

}