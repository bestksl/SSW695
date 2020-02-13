package com.hobbymatcher.service;

import com.hobbymatcher.entity.Events;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventsServiceTest {
	@Autowired
	private EventsService eventsService;

	@Test
	public void testGetEventsList() {
		List<Events> eventsList = eventsService.getEventsList();
		assertEquals("wxs", eventsList.get(0).getHolder());
	}
}
