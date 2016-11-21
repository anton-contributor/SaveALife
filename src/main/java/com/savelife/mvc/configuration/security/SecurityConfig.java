package com.savelife.mvc.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
				.usersByUsernameQuery("SELECT phone_number,password,enable FROM user WHERE phone_number=?;" )
				.authoritiesByUsernameQuery("SELECT phone_number, user_role FROM user "
						+ "JOIN user_role ON user.user_role_id = user_role.id "
						+ "WHERE phone_number=?;");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
                .authorizeRequests()
					.antMatchers("/signUp").permitAll()
//                  .antMatchers(HttpMethod.GET, "/**").hasAuthority("driver")
//            	    .antMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
//                  .antMatchers(HttpMethod.PATCH, "/**").hasRole("ADMIN")
					.anyRequest().authenticated()
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