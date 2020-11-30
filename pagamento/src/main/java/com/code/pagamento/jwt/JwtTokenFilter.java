package com.code.pagamento.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class JwtTokenFilter extends GenericFilterBean {

	private final JwtTokenProvider jJwtTokenProvider;

	@Autowired
	public JwtTokenFilter(JwtTokenProvider jJwtTokenProvider) {
		this.jJwtTokenProvider = jJwtTokenProvider;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String token = jJwtTokenProvider.resolveToken((HttpServletRequest) request);
		if (token != null && jJwtTokenProvider.validateToken(token)) {
			Authentication authentication = jJwtTokenProvider.getAuthentication(token);
			if (authentication != null) {
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		chain.doFilter(request, response);
	}

}
