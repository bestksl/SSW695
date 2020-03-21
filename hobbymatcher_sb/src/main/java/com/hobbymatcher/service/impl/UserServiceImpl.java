package com.hobbymatcher.service.impl;

import com.hobbymatcher.dao.UserDao;
import com.hobbymatcher.entity.Permission;
import com.hobbymatcher.entity.User;
import com.hobbymatcher.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
	Logger logger = Logger.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public List<User> getUserList() {
		return userDao.queryUser();
	}

	@Override
	public boolean register(User user) {
		try {
			user.setPassword(encoder.encode(user.getPassword()));
			List<GrantedAuthority> authorities = (List<GrantedAuthority>) user.getAuthorities();
			authorities.add(new SimpleGrantedAuthority("ROLE_USER_BASIC"));
			user.setAuthorities(authorities);
			userDao.insertUser(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean login(String email, String passWord) {
		String encodePassword = encoder.encode(passWord);
		User user = userDao.findUserByEmailAndPwd(email, passWord);
		return user != null;
	}

	@Override
	public boolean deleteUser(String id) {
		try {
			userDao.deleteUser(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public User findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

	@Override
	public Boolean updateUser(User user) {
		try {
			user.setPassword(encoder.encode(user.getPassword()));
			return userDao.updateUser(user) == 1;
		} catch (Exception e) {
			// System.out.println(e);
			return false;
		}
	}

	@Override
	public User findUserById(String id) {
		return userDao.findUserById(id);
	}

	@Override
	public User loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.findUserByEmail(email);
		if (user != null) {
			List<Permission> permissionList = userDao.findPermissionByEmail(email);
			List<GrantedAuthority> authorities = new ArrayList<>();
			for (Permission permission : permissionList) {
				GrantedAuthority authority = new SimpleGrantedAuthority(permission.getPermTag());
				authorities.add(authority);
			}
			user.setAuthorities(authorities);
			return user;
		} else {
			return new User();
		}
	}

}
