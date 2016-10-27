package com.savelife.mvc.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.SecurityExpressionOperations;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.access.expression.WebSecurityExpressionRoot;
import org.springframework.security.web.savedrequest.NullRequestCache;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static String REALM="MY_TEST_REALM";

	@Autowired
	DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("SELECT email,password,enable FROM user WHERE email=?" )
				.authoritiesByUsernameQuery("SELECT email, userRole FROM user "
						+ "JOIN user_role ON user.userRoleID = user_role.id "
						+ "WHERE email = ?;");

//		auth
//			.inMemoryAuthentication()
//				.withUser("1").password("1").roles("ADMIN").and()
//				.withUser("2").password("2").roles("USER")
//		;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
                .authorizeRequests()
					.anyRequest().permitAll()
					.antMatchers("/signUp").permitAll()
                    .antMatchers(HttpMethod.GET, "/**").hasAuthority("driver")
//            	    .antMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
//                  .antMatchers(HttpMethod.PATCH, "/**").hasRole("ADMIN")
//					.anyRequest().authenticated()
					.and()
//				.requiresChannel()
//					.antMatchers("/signUp")
//					.requiresSecure()
//					.and()
//				.sessionManagement()
//					.sessionFixation()
//					.none()
//					.and()
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