package com.hobbymatcher.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hobbymatcher.entity.Events;

@Repository
public interface EventsDao {
	/**
	 * @return eventsList
	 */
	List<Events> queryEvents();

	int addEvents(Events event);

	int joinEvents(@Param("id") String id, @Param("eventsId") String eventsId);

	Events findEventsByTitle(@Param("eventsTitle") String title);

	Events findEventsById(@Param("events_id") int id);

	List<Events> findPastEvents(@Param("id") String id, @Param("currentTime") LocalDateTime currentTime);

	List<Events> findUpcomingEvents(@Param("id") String id, @Param("currentTime") LocalDateTime currentTime);

	List<Events> getEventsForUser(@Param("id") String id);

	int deleteEvents(@Param("id") String id);

	int updateEvents(Events events);

	int getNumber(int id);

	List<Events> listEventsByHobbyId(@Param("hobbyId") int id);
}