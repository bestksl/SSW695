package com.hobbymatcher.dao;

import com.hobbymatcher.entity.Event;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventDao {

	List<Event> listEvent();

	int insertEvent(Event event);

	Event findEventById(@Param("id") int id);

	Event findEventByTitle(@Param("title") String title);

	List<Event> listEventByHobbyId(@Param("hobbyId") int id);

//    int joinEvents(@Param("id") String id, @Param("eventsId") String eventsId);
//
//    List<Event> findPastEvents(@Param("id") String id, @Param("currentTime") LocalDateTime currentTime);
//
//    List<Event> findUpcomingEvents(@Param("id") String id, @Param("currentTime") LocalDateTime currentTime);
//
//    List<Event> getEventsForUser(@Param("id") String id);
//
//    int deleteEvents(@Param("id") String id);
//
//    int updateEvents(Event events);
//
//    int getNumber(int id);
//
}