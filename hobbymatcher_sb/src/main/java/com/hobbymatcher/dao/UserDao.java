package com.hobbymatcher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hobbymatcher.entity.User;

@Repository
public interface UserDao {

	List<User> listUser();

	int insertUser(User user);

	User findUserById(@Param("id") Integer id);

	User findUserByEmail(@Param("email") String email);

	User findUserByEmailAndPwd(@Param("email") String email, @Param("password") String password);

	int updateUser(User user);

	int deleteUser(@Param("id") Integer id);
}