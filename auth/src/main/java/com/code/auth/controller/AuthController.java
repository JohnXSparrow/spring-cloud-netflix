package com.code.auth.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code.auth.jwt.JwtTokenProvider;
import com.code.auth.repository.UserRepository;
import com.code.auth.vo.UserVO;

@RestController
@RequestMapping("/login")
public class AuthController {
	
	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider jwtTokenProvider;
	private final UserRepository userRepository;
	
	@Autowired
	public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
			UserRepository userRepository) {
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userRepository = userRepository;
	}
	
	@RequestMapping("/teste-seguro")
	public String teste( ) {
		return "testado";
	}
	
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity<?> login(@RequestBody UserVO userVO) {
		try {			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userVO.getUsername(), userVO.getPassword()));
			
			var user = userRepository.findByUsername(userVO.getUsername());
			var token = "";
			
			if (user != null) {
				token = jwtTokenProvider.createToken(userVO.getUsername(), user.getRoles());
			} else {
				throw new UsernameNotFoundException("Usuário ou senha inválido.");
			}
			
			Map<Object, Object> model = new HashMap<>();
			model.put("username", userVO.getUsername());
			model.put("token", token);
			
			return ResponseEntity.ok(model);
			
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Usuário ou senha inválido.");
		}
	}
	

}
