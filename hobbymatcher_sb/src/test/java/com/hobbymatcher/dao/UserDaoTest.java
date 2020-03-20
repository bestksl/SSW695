package com.hobbymatcher.dao;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hobbymatcher.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {

	@Autowired
	private UserDao userDao;

	@Test
	public void testQueryUser() {
//        List<User> userList = userDao.queryUser();
//        assertEquals(1, userList.size());
	}

	@Test
	public void testInsertUser() {
		User user = new User();
		user.setemail("kslup76123136@gmail.com");
		user.setFirstName("fi");
		user.setLastName("la");
		user.setNickName("ksl");
		user.setPassword("123456");
		int result = userDao.insertUser(user);
		assertEquals(1, result);
	}

	@Test
	public void testFindUserByEmailAndPwd() {
		User user = userDao.findUserByEmailAndPwd("kslup7@gmail.com", "123456");
		assertNotNull(user);
	}

	@Test
	public void testFindUserById() {
		User user = userDao.findUserById("1");
		assertNotNull(user);
	}

	@Test
	public void testUpdateUser() {
		User user = userDao.findUserByEmailAndPwd("kslup7@gmail.com", "123456");
		user.setPassword("123");
		userDao.updateUser(user);
		user = userDao.findUserById(user.getId());
		assertEquals("123", user.getPassword());
	}

	@Test
	public void testFindPermissionByEmail() {
//		List<Permission> permissions = userDao.findPermissionByEmail("kslup7@gmail.com");
//		for (Permission permission : permissions) {
//			System.out.println(permission.getPermName() + " " + permission.getPermTag());
//		}
	}
}