package pl.szewczyk.ssecurityjwt;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;


public class JwtFilter extends BasicAuthenticationFilter {



	public JwtFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) 	throws IOException, ServletException { 

	String header = request.getHeader("Authorization");
		UsernamePasswordAuthenticationToken authResult = getAuthenticationByToken(header);
		SecurityContextHolder.getContext().setAuthentication(authResult);
		chain.doFilter(request, response);
	
		
	}


	private UsernamePasswordAuthenticationToken getAuthenticationByToken(String header) {
		Jws<Claims> claimsJws = Jwts.parser().setSigningKey("aaa".getBytes()).parseClaimsJws(header.replace("Bearer ", ""));
		
		
		System.out.println(claimsJws);
		
		String role = claimsJws.getBody().get("role").toString();
	
		String username = claimsJws.getBody().get("name").toString();
	
		//jest kolekcja bo wiele ról może być
		
		Set<SimpleGrantedAuthority> simpleGrantedAuthorities = Collections.singleton(new SimpleGrantedAuthority(role)); 

		UsernamePasswordAuthenticationToken  usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities) ;    //credentials null bo przypadek bez hasła //przyjmuje info na te,mat użytkowniika, hasła, uprawnień
		
		return usernamePasswordAuthenticationToken;
	}
	
//    private UsernamePasswordAuthenticationToken getAuthenticationByToken(String header) {
//		
//		Jws<Claims> claimsJws = Jwts.parser().setSigningKey("aaa".getBytes()).parseClaimsJws(header.replace("Bearer ", ""));
//		System.out.println(claimsJws);
//		return null;
//		
//	}
}
