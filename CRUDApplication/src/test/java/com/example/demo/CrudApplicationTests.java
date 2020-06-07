package com.example.demo;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.entiry.User;
import com.example.demo.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CrudApplicationTests {

	@Autowired
	private UserService service;
	
	@MockBean
	private UserRepository repository;
	
	@Test
	public void getUsersTest() {
		when(repository.findAll()).thenReturn(Stream.of(new User(376, "Muthu", 34, "Bangalore"),new User(386, "Seetha", 36, "Bangalore")).collect(Collectors.toList()));
		assertEquals(2, service.getUsers().size());
		
	}
	
	@Test
	public void getUserAddressByTest() {
		String address="Bangalore";
		when(repository.findByAddress(address)).thenReturn(Stream.of(new User(376, "Muthu", 34, "Bangalore")).collect(Collectors.toList()));
		assertEquals(1, service.getUserbyAddress(address).size());
	}
	
	@Test
	public void saveUerTest() {
		User user = new User(999,"Priya", 32,"Chennai");
		when(repository.save(user)).thenReturn(user);
		assertEquals(user, service.addUser(user));
	}
	
	@Test
	public void deleteUserTest() {
		User user = new User(999,"Priya", 32,"Chennai");
		service.deleteUser(user);
		verify(repository,times(1)).delete(user);
	}
}
