package com.hobbymatcher.service;

import java.util.List;

import com.hobbymatcher.entity.Event;

public interface EventService {

	List<Event> listEvent();

	boolean insertEvent(Event events);

	Event findEventById(int id);

	Event findEventByTitle(String title);

	List<Event> listEventByHobbyId(int id);

//	List<Event> findPastEvents(String id, LocalDateTime currentTime);
//
//	List<Event> findUpcomingEvents(String id, LocalDateTime currentTime);
//
//	List<Event> getEventsForUser(String id);
//
//	int getNumber(int id);
//
//	boolean joinEvents(String id, String eventsId);
//
//	boolean deleteEvents(String id);
//
//	boolean updateEvents(Event events);
}
