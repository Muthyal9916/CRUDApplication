package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entiry.User;

public interface UserRepository  extends JpaRepository<User, Integer>{

	List<User> findByAddress(String address);
}
