package com.hobbymatcher.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hobbymatcher.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void testGetUserList() {
//		List<User> userList = userService.getUserList();
//		assertEquals("ksl", userList.get(1).getNickName());
	}

	@Test
	public void testLogin() {
		assertTrue(userService.login("kslup7@gmail.com", "123"));
	}

	@Test
	public void testRegist() {
		User user = new User();
		user.setEmail("sunzheshiwoerzi@gmail.com");
		user.setFirstName("fi");
		user.setLastName("la");
		user.setNickName("ksl");
		user.setPassword("123456");
		// assertTrue(userService.register(user));
	}

	@Test
	public void testDelete() {
//		assertTrue(userService.deleteUser("12"));
		// assertTrue(userService.login("sunzheshiwoerzi@gmail.com", "123456"));
	}

	@Test
	public void testLoadUserByUsername() {
//        User user = userService.loadUserByUsername("kslup7@gmail.com");
		// System.out.println(user);
	}

	@Test
	public void testUpdate() {
//        User before = userService.loadUserByUsername("kslup7@gmail.com");
//        System.out.println(before);
//        before.setPassword("123456");
//        assertTrue(userService.updateUser(before));
//        User after = userService.loadUserByUsername("kslup7@gmail.com");
//        System.out.println(after);
	}
}