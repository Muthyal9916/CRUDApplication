package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.entiry.User;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class UserController {

	
	@Autowired
	private UserService service;
	
	@PostMapping(value = "/save")
	public User saveUser(@RequestBody User user) {
		return service.addUser(user);
	}
	
	@GetMapping("/getUsers")
	public List<User> findAllUsers() {
		return service.getUsers();
	}
	
	@GetMapping("/getUser")
	public List<User> getUsers() {
		List<User> user = new ArrayList<User>();
		user.add(new User(376, "Danile", 31, "USA"));
		user.add(new User(378, "Muthu", 34, "UK"));
		return user;
	}
	

	@GetMapping("/getUserByAddress/{address}")
	public List<User> findUserByAddress(@PathVariable String address) {
		return service.getUserbyAddress(address);
	}

	@DeleteMapping(value="/remove")
	public User removeUser(@RequestBody User user) {
		service.deleteUser(user);
		return user;
	}
}
