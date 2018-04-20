package com.reservationapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.reservationapp.service.impl.CurrentUserDetailsService;


//ovde
@Configuration
@EnableWebSecurity
//@Order(SecurityProperties.BASIC_AUTH_ORDER-2)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
    private CurrentUserDetailsService userDetailsService;
// 
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth)
//			throws Exception {
//		auth
//			.userDetailsService(userDetailsService)
//			.passwordEncoder(bCryptPasswordEncoder);
//	}
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
   	
    	http.
		authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/h2-console", "/h2-console/**").permitAll()
			.antMatchers("/login", "/confirm").anonymous()
			.antMatchers("/registration*").anonymous()
			.antMatchers("/userProfile").authenticated()
			.antMatchers("/admin/**").hasAuthority("ADMIN")
			.and().csrf().disable();
//			.antMatchers("/login", "/confirm").anonymous()
//			.antMatchers("/registration*").anonymous();
//			.antMatchers("/admin/**").hasAuthority("ADMIN");
			//.anyRequest();
//			.authenticated().and().csrf().disable().formLogin()
//			.and().logout()
//			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//			.logoutSuccessUrl("/").and().exceptionHandling()
//			.accessDeniedPage("/access-denied");

//    	and().csrf().disable().formLogin()
//		.loginPage("/login").failureUrl("/login?error=true")
//		.defaultSuccessUrl("/admin/home")
//		.usernameParameter("email")
//		.passwordParameter("password")
    	
    	http.headers().frameOptions().disable();
    }
    
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

}
