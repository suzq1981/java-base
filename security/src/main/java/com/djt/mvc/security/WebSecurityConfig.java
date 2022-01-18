package com.djt.mvc.security;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource(name = "userDetailsService")
	private UserDetailsService userDetailsService;

	@Resource(name = "securityAuthenticationSuccessHandler")
	private SecurityAuthenticationSuccessHandler securityAuthenticationSuccessHandler;

	@Resource(name = "securityAuthenticationFailureHandler")
	private SecurityAuthenticationFailureHandler securityAuthenticationFailureHandler;

	@Resource(name = "imageCodeAuthenticationFilter")
	private ImageCodeAuthenticationFilter imageCodeAuthenticationFilter;

	@Autowired
	private DataSource dataSource;

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
		repository.setDataSource(dataSource);

		return repository;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// auth.inMemoryAuthentication().withUser("root").password("123")
		// .roles("ADMIN", "DBA", "USER").
		// // withUser("root").password("123").roles("DBA").
		// and().withUser("admin").password("123").roles("ADMIN", "USER").
		// // withUser("admin").password("123").roles("ADMIN").
		// and().withUser("suzq").password("123").roles("USER");
		auth.userDetailsService(userDetailsService);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(imageCodeAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers("/index")
				.permitAll()
				.antMatchers("/imageCode")
				.permitAll()
				// .access("hasAnyRole('ADMIN','USER')")
				// .antMatchers("/dba/**").hasRole("DBA")
				// .antMatchers("/dba/**").hasAnyRole("DBA")
				// .access("hasRole('ADMIN') and hasRole('DBA')")
				// .antMatchers("/admin/**").hasRole("ADMIN")
				// .antMatchers("/goods/**").hasRole("USER")
				.antMatchers("/**").access("@rbacService.hasPermission(request,authentication)").anyRequest()
				.authenticated()

				.and().formLogin().loginPage("/loginPage").loginProcessingUrl("/springLogin")
				.successHandler(securityAuthenticationSuccessHandler)
				.failureHandler(securityAuthenticationFailureHandler)
				// .defaultSuccessUrl("/index", true)
				.permitAll()

				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/loginPage").clearAuthentication(true)
				.invalidateHttpSession(true)

				.and().exceptionHandling().accessDeniedPage("/denied")

				.and().csrf().disable();
		
		//http.rememberMe().tokenValiditySeconds(3600).tokenRepository(tokenRepository());
	}

}
