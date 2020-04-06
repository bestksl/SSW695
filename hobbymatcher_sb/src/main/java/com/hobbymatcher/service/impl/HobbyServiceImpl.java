package com.hobbymatcher.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hobbymatcher.dao.HobbyDao;
import com.hobbymatcher.entity.Followship;
import com.hobbymatcher.entity.Hobby;
import com.hobbymatcher.service.HobbyService;

import javax.validation.Valid;

@Valid
@Service
public class HobbyServiceImpl implements HobbyService {

	@Autowired
	private HobbyDao hobbyDao;

	@Override
	public List<Hobby> listHobby() {
		return hobbyDao.listHobby();
	}

	@Override
	public List<Hobby> listHobbyByCreatedById(int userId) {
		return hobbyDao.listHobbyByCreatedById(userId);
	}

	@Override
	public List<Hobby> listHobbyByFollowingUserId(int userId) {
		return hobbyDao.listHobbyByFollowingUserId(userId);
	}

	@Override
	public boolean insertHobby(Hobby hobby) {
		try {
			hobbyDao.insertHobby(hobby);
			return true;
		} catch (Exception exp) {
			exp.printStackTrace();
			return false;
		}
	}

	@Override
	public Hobby findHobbyById(int id) {
		return hobbyDao.findHobbyById(id);
	}

	@Override
	public Hobby findHobbyByName(String name) {
		return hobbyDao.findHobbyByName(name);
	}

	@Override
	public boolean updateHobby(Hobby hobby) {
		return hobbyDao.updateHobby(hobby) == 1;
	}

	@Override
	public boolean checkHobby(Hobby hobby) {
		if (hobby == null || hobby.getName() == null || hobby.getDescription() == null)
			return false;
		for (char s : hobby.getName().toCharArray()) {
			if (!Character.isAlphabetic(s) && !Character.isDigit(s))
				return false;
		}
		return true;
	}

	@Override
	public Followship getFollowship(int userId, Integer hobbyId) {
		return hobbyDao.getFollowship(userId, hobbyId);
	}

	@Override
	public void followHobby(int userId, Integer hobbyId) {
		hobbyDao.followHobby(userId, hobbyId);
	}

	@Override
	public void unfollowHobby(int userId, Integer hobbyId) {
		hobbyDao.unfollowHobby(userId, hobbyId);
	}
}