/*
 * package com.lab.jobportal.filter;
 * 
 * import java.io.IOException; import java.util.ArrayList;
 * 
 * import javax.servlet.FilterChain; import javax.servlet.ServletException;
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import org.springframework.security.authentication.AuthenticationManager;
 * import org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken; import
 * org.springframework.security.core.context.SecurityContextHolder; import
 * org.springframework.security.web.authentication.www.
 * BasicAuthenticationFilter; import static
 * com.lab.jobportal.filter.SecurityConstants.HEADER_STRING; import static
 * com.lab.jobportal.filter.SecurityConstants.TOKEN_PREFIX; import static
 * com.lab.jobportal.filter.SecurityConstants.SECRET; import com.auth0.jwt.JWT;
 * import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
 * 
 * public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
 * 
 * public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
 * super(authenticationManager); }
 * 
 * @Override protected void doFilterInternal(HttpServletRequest request,
 * HttpServletResponse response, FilterChain chain) throws IOException,
 * ServletException { String header = request.getHeader(HEADER_STRING);
 * 
 * if (header == null || !header.startsWith(TOKEN_PREFIX)) {
 * chain.doFilter(request, response); return; }
 * 
 * UsernamePasswordAuthenticationToken authentication =
 * getAuthentication(request);
 * 
 * SecurityContextHolder.getContext().setAuthentication(authentication);
 * chain.doFilter(request, response); }
 * 
 * private UsernamePasswordAuthenticationToken
 * getAuthentication(HttpServletRequest request) { String token =
 * request.getHeader(HEADER_STRING); if (token != null) { // parse the token.
 * String user =
 * JWT.require(HMAC512(SECRET.getBytes())).build().verify(token.replace(
 * TOKEN_PREFIX, "")) .getSubject();
 * 
 * if (user != null) { return new UsernamePasswordAuthenticationToken(user,
 * null, new ArrayList<>()); } return null; } return null; }
 * 
 * }
 */