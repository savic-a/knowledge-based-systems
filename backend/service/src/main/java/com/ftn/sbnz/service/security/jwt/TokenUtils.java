package com.ftn.sbnz.service.security.jwt;

import com.ftn.sbnz.model.Client;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

// Utility class for working with JSON Web Tokens
@Component
public class TokenUtils {

	@Value("sbnz")
	private String APP_NAME;

	@Value("somesecret")
	public String SECRET;

	// 30 minutes
	@Value("1800000")
	private int EXPIRES_IN;
	
	// header name
	@Value("Authorization")
	private String AUTH_HEADER;
	
	private static final String AUDIENCE_WEB = "web";

	// JWT signing algorithm
	private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
	

	public String generateToken(String username) {
		return Jwts.builder()
				.setIssuer(APP_NAME)
				.setSubject(username)
				.setAudience(generateAudience())
				.setIssuedAt(new Date())
				.setExpiration(generateExpirationDate())
				.signWith(SIGNATURE_ALGORITHM, SECRET).compact();
	}

	private String generateAudience() {
		return AUDIENCE_WEB;
	}

	private Date generateExpirationDate() {
		return new Date(new Date().getTime() + EXPIRES_IN);
	}


	public String getToken(HttpServletRequest request) {
		String authHeader = getAuthHeaderFromHeader(request);

		// The JWT is passed through the 'Authorization' header in the format
		// Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
		
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7); // preuzimamo samo token (vrednost tokena je nakon "Bearer " prefiksa)
		}

		return null;
	}

	public String getUsernameFromToken(String token) {
		String username;
		
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			username = claims.getSubject();
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception e) {
			username = null;
		}
		
		return username;
	}

	public Date getIssuedAtDateFromToken(String token) {
		Date issueAt;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			issueAt = claims.getIssuedAt();
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception e) {
			issueAt = null;
		}
		return issueAt;
	}

	public String getAudienceFromToken(String token) {
		String audience;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			audience = claims.getAudience();
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception e) {
			audience = null;
		}
		return audience;
	}

	public Date getExpirationDateFromToken(String token) {
		Date expiration;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			expiration = claims.getExpiration();
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception e) {
			expiration = null;
		}
		
		return expiration;
	}

	private Claims getAllClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token)
					.getBody();
		} catch (ExpiredJwtException ex) {
			throw ex;
		} catch (Exception e) {
			claims = null;
		}
		
		return claims;
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		Client user = (Client) userDetails;
		final String username = getUsernameFromToken(token);
		final Date created = getIssuedAtDateFromToken(token);
		
		// The token is valid when:
		return (username != null // email is not null
			&& username.equals(userDetails.getUsername())); // email from the token is equal with email in database
	}

	private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
		return (lastPasswordReset != null && created.before(lastPasswordReset));
	}

	public int getExpiredIn() {
		return EXPIRES_IN;
	}

	public String getAuthHeaderFromHeader(HttpServletRequest request) {
		return request.getHeader(AUTH_HEADER);
	}
	
}
