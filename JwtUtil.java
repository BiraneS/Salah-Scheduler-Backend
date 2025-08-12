package com.salahscheduler.salahscheduler;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtUtil {
	private String secret = "12ThisIsASecured12Token123";
	private int expiration = 1000 * 60 * 10;

	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact()
					;
	}
	public String getUsernameFromToken(String token) {
		return ((Claims) Jwts.parser()
				.setSigningKey(secret)
				.parse(token)
				.getBody())
				.getSubject()
				;
		
	}
	
	public Boolean isTokenExpired(String token) {
		Date expiration = Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody()
				.getExpiration()
				;
		return expiration.before(new Date());

	}
public Boolean validateToken(String token, String username) {
	String tokenUsername = getUsernameFromToken(token);
	return(tokenUsername.equals(username) && !isTokenExpired(token));
}
}
