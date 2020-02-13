package com.hobbymatcher.dao;

import com.hobbymatcher.entity.Events;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventsDaoTest  {

	@Autowired
	private EventsDao eventsDao;

	@Test
	public void testQueryEvents() {
		List<Events> eventsList = eventsDao.queryEvents();
		assertEquals(1, eventsList.size());
	}

	@Test
	public void testInsertEvents() {
		Events events = new Events();
		events.setEventsTitle("Play Basketball!");
		LocalDateTime date = LocalDateTime.now();
		//events.setEventsTime(date);
		events.setDescription("NBA");
		events.setLocation("howe");
		events.setFee("100");
		events.setHolder("Zhe");
		int result = eventsDao.addEvents(events);
		assertEquals(0, result);
	}
}
