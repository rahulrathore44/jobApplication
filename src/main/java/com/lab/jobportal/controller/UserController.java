package com.lab.jobportal.controller;

import static com.lab.jobportal.filter.SecurityConstants.USER_CONTEXT_PATH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lab.jobportal.users.ApplicationUserRepository;
import com.lab.jobportal.users.ApplicationUsers;

import io.swagger.annotations.Api;
@Api(value = "Sign up", description = "End Point for User Registeration")
@RestController
@RequestMapping(path = { USER_CONTEXT_PATH })
public class UserController {

	@Autowired
	private ApplicationUserRepository applicationUserRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserController(ApplicationUserRepository applicationUserRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.applicationUserRepository = applicationUserRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@RequestMapping(path = { "/sign-up" }, method = { RequestMethod.POST },produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, consumes = {
					MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<Object> signUp(@RequestBody ApplicationUsers user) {
		if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
			return new ResponseEntity<Object>(
					String.format("Invalid User-Name %s and Password %s", user.getUsername(), user.getPassword()),
					HttpStatus.BAD_REQUEST);
		}
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		applicationUserRepository.save(user);
		return new ResponseEntity<Object>(String.format("%s register successfully", user.getUsername()), HttpStatus.OK);
	}
}
