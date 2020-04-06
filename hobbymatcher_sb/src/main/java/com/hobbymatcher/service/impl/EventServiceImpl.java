package com.hobbymatcher.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hobbymatcher.dao.EventDao;
import com.hobbymatcher.entity.Event;
import com.hobbymatcher.entity.Participation;
import com.hobbymatcher.service.EventService;

import javax.validation.Valid;

@Valid
@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventDao eventDao;

	@Override
	public List<Event> listEvent() {
		return eventDao.listEvent();
	}

	@Override
	public List<Event> listEvent(Boolean justCount, //
			String searchScope, String searchPhrase, String[] categoryIds, Double feeStart, Double feeEnd,
			String sortBy, int offset, int perpage) {
		return eventDao.searchEvent(justCount, //
				searchScope, searchPhrase, categoryIds, feeStart, feeEnd, sortBy, offset, perpage);
	}

	@Override
	public List<Event> listPastJoinEvents(int userId) {
		return eventDao.listPastJoinEvents(userId);
	}

	@Override
	public List<Event> listJoinedFutureEvents(int userId) {
		return eventDao.listJoinedFutureEvents(userId);
	}

	@Override
	public List<Event> listPastHoldEvents(int userId) {
		return eventDao.listPastHoldEvents(userId);
	}

	@Override
	public boolean insertEvent(Event event) {
		try {
			eventDao.insertEvent(event);
			return true;
		} catch (Exception exp) {
			exp.printStackTrace();
			return false;
		}
	}

	@Override
	public Event findEventById(int id) {
		return eventDao.findEventById(id);
	}

	@Override
	public Event findEventByTitle(String title) {
		return eventDao.findEventByTitle(title);
	}

	@Override
	public List<Event> listEventByHobbyId(int id) {
		return eventDao.listEventByHobbyId(id);
	}

	@Override
	public Boolean updateEvent(Event event) {
		return eventDao.updateEvent(event) == 1;
	}

	@Override
	public Participation getParticipation(int userId, Integer eventId) {
		return eventDao.getParticipation(userId, eventId);
	}

	@Override
	public void requestToParticipateEvent(Integer userId, Integer eventId) {
		eventDao.requestToParticipateEvent(userId, eventId);
	}

	@Override
	public void cancelEventParticipationRequest(int userId, Integer eventId) {
		eventDao.cancelEventParticipationRequest(userId, eventId);
	}

	@Override
	public void markEventAsParticipated(int userId, Integer eventId) {
		eventDao.markEventAsParticipated(userId, eventId);
	}

	@Override
	public void approveUser(Integer theUserId, Integer eventId) {
		eventDao.approveUser(theUserId, eventId);
	}
}
