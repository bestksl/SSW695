package com.hobbymatcher.service;

import java.util.List;

import com.hobbymatcher.entity.Hobby;

public interface HobbyService {
    List<Hobby> listHobby();

    boolean deleteHobby(int id);

    boolean updateHobby(Hobby hobby);

    Hobby findHobbyByName(String name);

    Hobby findHobbyById(int id);

    boolean insertHobby(Hobby hobby);
}
