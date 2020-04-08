package com.hobbymatcher.service;

import java.util.List;

import com.hobbymatcher.entity.Event;
import com.hobbymatcher.entity.Participation;

public interface EventService {

	List<Event> listEvent();

	List<Event> listEvent(Boolean justCount, //
			String searchScope, String searchPhrase, String[] categoryIds, Double feeStart, Double feeEnd,
			String sortBy, int offset, int perpage);

	boolean insertEvent(Event events);

	Event findEventById(int id);

	Event findEventByTitle(String title);

	List<Event> listEventByHobbyId(int id);

	List<Event> listPastJoinEvents(int userId);

	List<Event> listJoinedFutureEvents(int userId);

	List<Event> listPastHoldEvents(int userId);

	Boolean updateEvent(Event event);

	Participation getParticipation(int userId, Integer eventId);

	void requestToParticipateEvent(Integer userId, Integer eventId);

	void cancelEventParticipationRequest(int userId, Integer eventId);

	void markEventAsParticipated(int userId, Integer eventId);

	void approveUser(Integer theUserId, Integer eventId);

	List<Event> listRecentEvents();

//	boolean checkEvent(Event event);

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
