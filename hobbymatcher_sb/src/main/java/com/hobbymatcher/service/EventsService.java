package com.hobbymatcher.service;

import java.time.LocalDateTime;
import java.util.List;

import com.hobbymatcher.entity.Events;

public interface EventsService {
	List<Events> getEventsList();

	List<Events> listEventsByHobbyId(int id);

	boolean addEvents(Events events);

	Events findEventsByTitle(String name);

	Events findEventsById(int id);

	List<Events> findPastEvents(String id, LocalDateTime currentTime);

	List<Events> findUpcomingEvents(String id, LocalDateTime currentTime);

	List<Events> getEventsForUser(String id);

	int getNumber(int id);

	boolean joinEvents(String id, String eventsId);

	boolean deleteEvents(String id);

	boolean updateEvents(Events events);
}
