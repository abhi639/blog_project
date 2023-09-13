package com.app.blog.Configuration;

import java.io.IOException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import org.springframework.web.filter.OncePerRequestFilter;

import com.app.blog.services.JwtService;
import com.app.blog.services.UserService;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	private final JwtService jwtService;
	private final UserService userService;
	
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
     final String authHeader=request.getHeader("Authorization");
     final String jwt;
     final String userEmail;
     if(StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer")) {
     filterChain.doFilter(request, response);
     return;
     }
		jwt =authHeader.substring(7);
		userEmail=jwtService.extractUserName(jwt);
		if(StringUtils.isNotEmpty(userEmail)&& SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDtails=userService.userDetailsService().loadUserByUsername(userEmail);
			if(jwtService.isTokenValid(jwt, userDtails)) {
				SecurityContext context=SecurityContextHolder.createEmptyContext();
				UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDtails, null,userDtails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				context.setAuthentication(authToken);
				SecurityContextHolder.setContext(context);
			}
			
		}
		filterChain.doFilter(request, response);
	}

}
