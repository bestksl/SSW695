package com.hobbymatcher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hobbymatcher.entity.Followship;
import com.hobbymatcher.entity.Hobby;

@Repository
public interface HobbyDao {

	List<Hobby> listHobby();

	List<Hobby> searchHobby(@Param("offset") Integer offset, @Param("perpage") Integer perpage,
			@Param("categoryIds") String[] categoryIds);

	int insertHobby(Hobby hobby);

	Hobby findHobbyByName(@Param("name") String name);

	Hobby findHobbyById(@Param("id") int id);

	int updateHobby(Hobby hobby);

	int deleteHobby(@Param("id") int id);

	List<Hobby> listHobbyByCreatedById(@Param("createdById") int createdById);

	List<Hobby> listHobbyByFollowingUserId(@Param("userId") int userId);

	Followship getFollowship(@Param("userId") int userId, @Param("hobbyId") int hobbyId);

	void followHobby(@Param("userId") int userId, @Param("hobbyId") int hobbyId);

	void unfollowHobby(@Param("userId") int userId, @Param("hobbyId") int hobbyId);

	Integer countHobby();
}
