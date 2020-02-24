package com.hobbymatcher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hobbymatcher.entity.Permission;
import com.hobbymatcher.entity.User;

@Repository
public interface UserDao {
	/**
	 * 列出user列表
	 *
	 * @return userList
	 */
	List<User> queryUser();

	int insertUser(User user);

	User findUserByEmailAndPwd(@Param("email") String email, @Param("passWord") String passWord);

	User findUserById(@Param("id") String id);

	int updateUser(User user);

	int deleteUser(@Param("id") String id);

	User findUserByEmail(@Param("email") String email);

	List<Permission> findPermissionByEmail(String email);
}