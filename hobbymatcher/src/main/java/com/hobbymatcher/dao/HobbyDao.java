package com.hobbymatcher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hobbymatcher.entity.Hobby;

public interface HobbyDao {
    List<Hobby> queryHobby();

    int insertHobby(Hobby hobby);

    Hobby findHobbyByName(@Param("name") String name);

    Hobby findHobbyById(@Param("id") int id);

    int updateHobby(Hobby hobby);

    int deleteHobby(@Param("id") int id);

}
