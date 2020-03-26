package com.hobbymatcher.service;

import java.util.List;

import com.hobbymatcher.entity.Hobby;

public interface HobbyService {

    List<Hobby> listHobby();

    boolean insertHobby(Hobby hobby);

    Hobby findHobbyById(int id);

    Hobby findHobbyByName(String name);

    boolean updateHobby(Hobby hobby);

    boolean checkHobby(Hobby hobby);
}
