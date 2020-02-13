package com.hobbymatcher.dao;

import com.hobbymatcher.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest{

	@Autowired
	private UserDao userDao;

	@Test
	public void testQueryUser() {
		List<User> userList = userDao.queryUser();
		assertEquals(1, userList.size());
	}


	@Test
	public void testInsertUser() {
		User user = new User();
		user.setEmail("kslup766@gmail.com");
		user.setFirstName("fi");
		user.setLastName("la");
		user.setNickName("ksl");
		user.setPassWord("123456");
		int result = userDao.insertUser(user);
		assertEquals(1, result);
	}

	// @Test
	public void testFindUserByEmailAndPwd() {
		User user = userDao.findUserByEmailAndPwd("kslup7@gmail.com", "123456");
		assertNotNull(user);
	}

	// @Test
	public void testFindUserById() {
		User user = userDao.findUserById("1");
		assertNotNull(user);
	}

	// @Test
	public void testUpdateUser() {
		User user = userDao.findUserByEmailAndPwd("kslup7@gmail.com", "123456");
		user.setPassWord("123");
		userDao.updateUser(user);
		user = userDao.findUserById(user.getId());
		assertEquals("123", user.getPassWord());
	}
}