package evgenyt.tacos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

/**
 * Security configurator
 * @author EUTyrin
 *
 */

@Slf4j
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	@Autowired
    PasswordEncoder passwordEncoder;
    */
	
	@Autowired
	private UserRepositoryUserDetailsService userRepositoryUserDetailsService;
		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("YYYYYYYYYYAAAAAAYYYY!");
		auth
		.userDetailsService(userRepositoryUserDetailsService)
		.passwordEncoder(passwordEncoder());
	}
						
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/design", "/orders").hasRole("USER")
			.antMatchers("/", "/**").permitAll()
			.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/design", true)
			.and()
			.logout()
			.logoutSuccessUrl("/");
	}
	
	/*
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	*/
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		// return new StandardPasswordEncoder("53cr3t");
		return new BCryptPasswordEncoder();
	}	
		
}
