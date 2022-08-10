package com.jdc.spring.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ConfigAboutSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/resources/**", "/home", "/login", "/signup", "/cart/add/*")
					.permitAll()
				.antMatchers("/member/**").hasAnyRole("CUSTOMER", "OWNER")
				.antMatchers("/owner/**").hasRole("OWNER")
			.and()
			.formLogin()
				.loginPage("/login")
				.usernameParameter("email")
				.passwordParameter("password")
			.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/")
				.deleteCookies("JSESSIONID");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(passwordEncoder)
			.usersByUsernameQuery("select email, password, enable from account where email = ?")
			.authoritiesByUsernameQuery("select email, role from account where email = ?");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public static void main(String[] args) {
		BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
		String pass = enc.encode("11111");
		System.out.println(pass);
	}
}
