package com.mercury.pm.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.mercury.pm.security.handlers.AccessDeniedHandlerImpl;
import com.mercury.pm.security.handlers.AuthenticationEntryPointImpl;
import com.mercury.pm.security.handlers.AuthenticationFailureHandlerImpl;
import com.mercury.pm.security.handlers.AuthenticationSuccessHandlerImpl;
import com.mercury.pm.security.handlers.LogoutSuccessHandlerImpl;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthenticationEntryPointImpl authenticationEntryPointImpl;
	
	@Autowired
	AccessDeniedHandlerImpl accessDeniedHandlerImpl;
	
	@Autowired
	AuthenticationSuccessHandlerImpl authenticationSuccessHandlerImpl;
	
	@Autowired
	AuthenticationFailureHandlerImpl authenticationFailureHandlerImpl;
	
	@Autowired
	LogoutSuccessHandlerImpl logoutSuccessHandlerImpl;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().and().authorizeRequests().antMatchers("/**").permitAll().and().authorizeRequests()
				.anyRequest().permitAll().and()
	            .formLogin()
					.permitAll()
					.loginProcessingUrl("/login")
					.successHandler(authenticationSuccessHandlerImpl)
				    .failureHandler(authenticationFailureHandlerImpl)
					.usernameParameter("username").passwordParameter("password")
					.and()
				.logout()
					.permitAll()
					.logoutUrl("/logout")
					.logoutSuccessHandler(logoutSuccessHandlerImpl)
					.and()
				.rememberMe();;
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "http://localhost:4201"));
//        configuration.addAllowedOrigin("*"); // You should only set trusted site here. e.g. http://localhost:4200 means only this site can access.
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS"));
		configuration.addAllowedHeader("*");
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(11);
	}

	@Bean
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(null).passwordEncoder(passwordEncoder());
	}
}
