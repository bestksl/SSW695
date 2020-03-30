package com.hobbymatcher.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hobbymatcher.dao.HobbyDao;
import com.hobbymatcher.entity.Hobby;
import com.hobbymatcher.service.HobbyService;

@Service
public class HobbyServiceImpl implements HobbyService {

	@Autowired
	private HobbyDao hobbyDao;

	@Override
	public List<Hobby> listHobby() {
		return hobbyDao.listHobby();
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
}