package pl.szewczyk.ssecurityjwt;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;




@Configuration
public class WebSecurityConfig extends  WebSecurityConfigurerAdapter {   //do definiowania zalezności

	
//	public PasswordEncoder getPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {  //symuluje baze danych
//		auth.inMemoryAuthentication()
//		.withUser("test")
//			//	.password(getPasswordEncoder().encode("test"))
//		.password("test")
//				.roles("ADMIN");
//
//	
//	} 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/test2").authenticated()
		//trzeba pamiętać, że rola w tokenie to musi miec przedrostek  "role":"ROLE_ADMIN" 
		.antMatchers("/test3").hasRole("ADMIN")
		.and()
	.addFilter(new JwtFilter(authenticationManager()));
//		.formLogin().permitAll();  //dostęp do logowania dla wszystkich 
	}
	
}
