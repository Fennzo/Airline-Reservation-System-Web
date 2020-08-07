package com.Alex.Security;
	

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

	// Definite valid users
	@Configuration
	@EnableWebSecurity
	public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
		
		// To access the h2 embedded database
		@Autowired
		DataSource dataSource;
		
		@Autowired
	    Securityhandler successHandler;
		
		@Autowired
		public void configure(AuthenticationManagerBuilder auth)
				throws Exception {
			auth.jdbcAuthentication()
				// To find logins in the h2 database
				.dataSource(dataSource)
				.usersByUsernameQuery("select email, password, 'true' as enabled from User where email = ?")		
				.authoritiesByUsernameQuery("select email, role " +
								"from User " +
								"where email =?");
			
		}
		
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
					.antMatchers("/*", "/h2-console/**").permitAll()
					.antMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
					.antMatchers("/admin/**").hasAuthority("ADMIN")
					// formLogin redirect to login page
					.and().formLogin()
					.successHandler(successHandler)
					.and().csrf().disable();
				
			
			// In order to work with spring security csrf protection needs to be disabled
			http.csrf().disable();
	        http.headers().frameOptions().disable();

		}
		
		// To encrypt password
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
		}
	}
