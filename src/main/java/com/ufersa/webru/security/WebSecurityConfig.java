package com.ufersa.webru.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private ImplementsUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.GET, "/").permitAll()
		.antMatchers(HttpMethod.GET, "/listarGestores").hasAnyRole("ADMIN_UFERSA")
		.antMatchers(HttpMethod.GET, "/cadastrarParametroHorario").hasAnyRole("ADMIN_UFERSA")
		.antMatchers(HttpMethod.POST, "/cadastrarParametroHorario").hasAnyRole("ADMIN_UFERSA")
		.antMatchers(HttpMethod.GET, "/cadastrarParametroValorMonetario").hasAnyRole("ADMIN_UFERSA")
		.antMatchers(HttpMethod.POST, "/cadastrarParametroValorMonetario").hasAnyRole("ADMIN_UFERSA")
		.antMatchers(HttpMethod.POST, "/importarGestores").hasAnyRole("ADMIN_UFERSA")
		.antMatchers(HttpMethod.GET, "/listarParametros").hasAnyRole("ADMIN_UFERSA")
		.antMatchers(HttpMethod.GET, "/listarAlunos").hasAnyRole("ADMIN_UFERSA")
		.antMatchers(HttpMethod.POST, "/cadastrarAluno").hasAnyRole("ADMIN_UFERSA")
		.anyRequest().authenticated()
		.and().formLogin().permitAll()
		.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
}