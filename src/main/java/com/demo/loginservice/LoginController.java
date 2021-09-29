package com.demo.loginservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLSession;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.UUID;

@RestController
public class LoginController {
	private ObjectMapper mapper = new ObjectMapper();
	@PostMapping("/login")
	public LoginResponse login(@RequestBody String body) throws JsonProcessingException {
		var user = mapper.readValue(body, User.class);

		return new LoginResponse(user.getEmail(), "1234");
	}

	@PostMapping("/register")
	public RegisterResponse register(@RequestBody String body) throws JsonProcessingException {
		var user = mapper.readValue(body, User.class);

		return new RegisterResponse(user.getEmail(), UUID.randomUUID());
	}

	@PostMapping("/validate")
	public ValidateResponse validate(@RequestBody String body) throws JsonProcessingException {
		var validateInfo = mapper.readValue(body, ValidateInfo.class);

		return new ValidateResponse(validateInfo.getEmail(), "newToken");
	}

	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Malformed json")
	@ExceptionHandler(JsonProcessingException.class)
	public void parseError()
	{}
}
