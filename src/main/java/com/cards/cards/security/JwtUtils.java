package com.cards.cards.security;

import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.cards.cards.configs.Constants;
import com.cards.cards.exceptions.CustomException;
import com.cards.cards.repos.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtils {

	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
		Date expiryDate = new Date(new Date().getTime() + Constants.Jwt.expiry * 1000);
		return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, Constants.Jwt.secret).compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(Constants.Jwt.secret).parseClaimsJws(token).getBody().getSubject();
	}

	@SneakyThrows
	public boolean validateJwtToken(String authToken) throws SignatureException {
		try {
			Jwts.parser().setSigningKey(Constants.Jwt.secret).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token: {}", e.getMessage());
			throw new CustomException("Sorry, the provided token is invalid.");
		} catch (ExpiredJwtException e) {
			String tokenExpiry = StringUtils.substringBetween(e.getMessage(), "JWT expired at", ". Current time");
			String tokenExpiryFormatted = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a")
					.format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").parse(tokenExpiry.trim()));
			throw new CustomException(
					String.format("Sorry, your token expired on %s. Please, login again to get a valid token.",
							tokenExpiryFormatted));
		} catch (UnsupportedJwtException e) {
			log.error("JWT token is unsupported: {}", e.getMessage());
			throw new CustomException("Sorry, the provided token is unsupported.");
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
			throw new CustomException("Sorry, the provided token has no claims.");
		}
	}

}
