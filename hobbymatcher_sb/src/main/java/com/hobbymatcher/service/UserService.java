package com.hobbymatcher.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.hobbymatcher.entity.User;

public interface UserService extends UserDetailsService {

	List<User> listUser();

	Boolean register(User user);

	User findUserById(Integer id);

	User findUserByEmail(String email);

	Boolean login(String email, String password);

	Boolean updateUser(User user);

	Boolean deleteUser(Integer id);
}
