package com.hobbymatcher.service;

import java.util.List;

import com.hobbymatcher.entity.User;

public interface UserService {
    List<User> getUserList();

    boolean register(User user);

    boolean login(String email, String passWord);

    boolean deleteUser(String id);

    User findUserByEmail(String email);

    Boolean updateUser(User user);
}
