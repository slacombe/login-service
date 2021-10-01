package com.demo.loginservice;

import com.demo.loginservice.model.AuthenticationRequest;
import com.demo.loginservice.model.AuthenticationResponse;
import com.demo.loginservice.model.RegisterResponse;
import com.demo.loginservice.model.User;
import com.demo.loginservice.services.MyUserDetailsService;
import com.demo.loginservice.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class LoginController {
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	public JwtUtil jwtTokenUtil;

	@RequestMapping("/hello")
	public String hello() {
		return "Hello, World!";
	}

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticateToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
			);

			final UserDetails userDetails = userDetailsService
					.loadUserByUsername(authenticationRequest.getEmail());

			final String jwt = jwtTokenUtil.generateToken(userDetails);

			return ResponseEntity.ok(new AuthenticationResponse(jwt));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password");
		}
	}

	@PostMapping("/register")
	public RegisterResponse register(@RequestBody String body) throws JsonProcessingException {
		var user = mapper.readValue(body, User.class);

		return new RegisterResponse(user.getEmail(), UUID.randomUUID());
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Malformed json")
	@ExceptionHandler(JsonProcessingException.class)
	public void parseError() {
	}
}
