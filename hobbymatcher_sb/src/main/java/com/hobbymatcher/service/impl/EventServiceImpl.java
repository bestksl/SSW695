package com.hobbymatcher.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hobbymatcher.dao.EventDao;
import com.hobbymatcher.entity.Event;
import com.hobbymatcher.service.EventService;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventDao eventDao;

	@Override
	public List<Event> listEvent() {
		return eventDao.listEvent();
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

//    @Override
//    public boolean checkEvent(Event event) {
//
//        if (event == null)
//            return false;
//
//        //check event important attr
//        if (event.getId() == null || event.getId() < 0)
//            return false;
//        else if (event.getTitle() == null || "".equals(event.getTitle()))
//            return false;
//        else return event.getDescription() != null && !"".equals(event.getDescription());
//    }

//    @Override
//    public boolean joinEvents(String id, String eventsId)
//    {
//        try {
//            eventsDao.joinEvents(id, eventsId);
//            return true;
//        } catch (Exception e) {
//            System.out.print(e.toString());
//            return false;
//        }
//    }
//
//    @Override
//    public Event findEventsByTitle(String title)
//    {
//        return null;
//    }
//
//    @Override
//    public Event findEventsById(int id)
//    {
//        return eventsDao.findEventsById(id);
//    }
//
//    @Override
//    public List<Event> findPastEvents(String id, LocalDateTime currentTime)
//    {
//        return eventsDao.findPastEvents(id, currentTime);
//    }
//
//    @Override
//    public List<Event> findUpcomingEvents(String id, LocalDateTime currentTime)
//    {
//        return eventsDao.findUpcomingEvents(id, currentTime);
//    }
//
//    @Override
//    public List<Event> getEventsForUser(String id)
//    {
//        return eventsDao.getEventsForUser(id);
//    }
//
//    @Override
//    public boolean deleteEvents(String id) {
//        return eventsDao.deleteEvents(id) != 0;
//    }
//
//    @Override
//    public boolean updateEvents(Event events) {
//        return eventsDao.updateEvents(events) == 1;
//    }
//
//    @Override
//    public int getNumber(int id)
//    {
//        return eventsDao.getNumber(id);
//    }
//
//    @Override
//    public List<Event> listEventsByHobbyId(int id) {
//        List<Event> events = eventsDao.listEventsByHobbyId(id);
//        if (events == null || events.size() == 0) {
//            return null;
//        }
//        return eventsDao.listEventsByHobbyId(id);
//    }
}
