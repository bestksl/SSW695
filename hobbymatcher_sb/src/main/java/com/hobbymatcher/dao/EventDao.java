package com.hobbymatcher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hobbymatcher.entity.Event;

@Repository
public interface EventDao {

	List<Event> listEvent();

	int insertEvent(Event event);

	Event findEventById(@Param("id") int id);

	Event findEventByTitle(@Param("title") String title);

	List<Event> listEventByHobbyId(@Param("hobbyId") int id);

	List<Event> listPastJoinEvents(@Param("userId") int userId);

	List<Event> listJoinedFutureEvents(@Param("userId") int userId);

	List<Event> listPastHoldEvents(@Param("userId") int userId);
}