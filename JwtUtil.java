package com.salahscheduler.salahscheduler;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

//managing and securing tokens that prove a user is logged in to your application
@Component
public class JwtUtil {
	// A secret password that only your server knows,
	// (like a secret signature) so nobody can fake them
	private String secret = "12ThisIsASecured12Token123";
	//For security - tokens shouldn't last forever
	private int expiration = 1000 * 60 * 10;

	//When a user logs in successfully,
	//you give them this token. 
	//They send it back with future requests to prove they're logged in.

	public String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact()
					;
	}
	//When a user sends a request with their token, you need to know WHO they are
	public String getUsernameFromToken(String token) {
		return ((Claims) Jwts.parser()
				.setSigningKey(secret)
				.parse(token)
				.getBody())
			//which is the username
				.getSubject()
				;
		
	}
	//were checking if the token is expired..
	//
	
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
