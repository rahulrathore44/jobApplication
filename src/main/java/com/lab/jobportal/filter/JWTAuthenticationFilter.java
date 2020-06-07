package com.lab.jobportal.filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.core.userdetails.User;
import java.util.Date;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lab.jobportal.users.ApplicationUsers;
import static com.lab.jobportal.filter.SecurityConstants.EXPIRATION_TIME;
import static com.lab.jobportal.filter.SecurityConstants.HEADER_STRING;
import static com.lab.jobportal.filter.SecurityConstants.TOKEN_PREFIX;
import static com.lab.jobportal.filter.SecurityConstants.SECRET;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	/*
	 * private AuthenticationManager authenticationManager; public
	 * JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
	 * this.authenticationManager = authenticationManager; }
	 * 
	 * @Override public Authentication attemptAuthentication(HttpServletRequest
	 * request, HttpServletResponse response) throws AuthenticationException { try {
	 * ApplicationUsers creds = new ObjectMapper()
	 * .readValue(request.getInputStream(), ApplicationUsers.class); return
	 * authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
	 * creds.getUsername(), creds.getPassword(), new ArrayList<>()) ); } catch
	 * (IOException e) { throw new RuntimeException(e); } }
	 * 
	 * @Override protected void successfulAuthentication(HttpServletRequest request,
	 * HttpServletResponse response, FilterChain chain, Authentication authResult)
	 * throws IOException, ServletException { String token = JWT.create()
	 * .withSubject(((User) authResult.getPrincipal()).getUsername())
	 * .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
	 * .sign(HMAC512(SECRET.getBytes())); response.addHeader(HEADER_STRING,
	 * TOKEN_PREFIX + token); }
	 * 
	 * public static String getToken(String username,String password) { String token
	 * = JWT.create() .withSubject(username) .withExpiresAt(new
	 * Date(System.currentTimeMillis() + EXPIRATION_TIME))
	 * .sign(HMAC512(SECRET.getBytes())); return TOKEN_PREFIX + token; }
	 */
}
