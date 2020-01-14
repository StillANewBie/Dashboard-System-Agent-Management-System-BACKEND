package com.mercury.pm.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.mercury.pm.security.handlers.AccessDeniedHandlerImpl;
import com.mercury.pm.security.handlers.AuthenticationEntryPointImpl;
import com.mercury.pm.security.handlers.AuthenticationFailureHandlerImpl;
import com.mercury.pm.security.handlers.AuthenticationSuccessHandlerImpl;
import com.mercury.pm.security.handlers.LogoutSuccessHandlerImpl;
import com.mercury.pm.security.jwt.JwtAuthenticationEntryPoint;
import com.mercury.pm.security.jwt.JwtRequestFilter;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
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
	
	@Autowired
	private JwtAuthenticationEntryPoint JwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtRequestFilter JwtRequestFilter;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
//	.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
			.authorizeRequests().antMatchers("/authenticate").permitAll()
			.anyRequest().authenticated().and().
			exceptionHandling().authenticationEntryPoint(JwtAuthenticationEntryPoint)
			.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(JwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
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
//	@Override
	public AuthenticationManager authenticationmanagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}
