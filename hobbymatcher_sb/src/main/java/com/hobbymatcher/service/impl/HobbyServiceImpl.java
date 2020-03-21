package com.hobbymatcher.service.impl;

import com.hobbymatcher.dao.HobbyDao;
import com.hobbymatcher.entity.Hobby;
import com.hobbymatcher.service.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HobbyServiceImpl implements HobbyService {

	private HobbyDao hobbyDao;

	@Autowired
	public HobbyServiceImpl(HobbyDao hobbyDao) {
		this.hobbyDao = hobbyDao;
	}

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
}