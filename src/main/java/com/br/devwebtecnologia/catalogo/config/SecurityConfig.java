package com.br.devwebtecnologia.catalogo.config;

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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Autenticação direto na classe de configuração sem uso de base de dados
   	/*	auth.inMemoryAuthentication()
			.withUser("usuario").password("usuario123").roles("PESQUISAR").and()
			.withUser("administrador").password("administrador123").roles("CADASTRAR", "PESQUISAR").and()
			.withUser("thais").password("nutrimaisbelemthais").roles("CADASTRAR","PESQUISAR"); */

		// Autenticação com uso de LDAP		
	/*  auth.ldapAuthentication()  */
		
		// Autenticação com uso de base de dados		
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(bCryptPasswordEncoder);
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/esqueceu-a-senha").permitAll()
				.antMatchers("/registration").permitAll()
				//utilizar quando a autorização é atraves da classe sem banco de dados
//				.antMatchers("/alimento").hasRole("ADMIN")
				.antMatchers("/alimento").permitAll()
				.antMatchers("/alimento/**").hasAuthority("ADMIN")
				.anyRequest().authenticated()
				.and()
				.csrf().disable()
			.formLogin()
				.loginPage("/login").failureUrl("/login?error=true")
				.permitAll()
				.defaultSuccessUrl("/alimento")
				.usernameParameter("email")
				.passwordParameter("password")
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").and().exceptionHandling()
				.accessDeniedPage("/error/403");;

	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/layout/**", "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

}
