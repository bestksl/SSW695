package com.hobbymatcher.dao;

import com.hobbymatcher.entity.Event;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventDaoTest {

    @Autowired
    private EventDao eventsDao;

    @Test
    public void testQueryEvents() {
//		List<Event> eventsList = eventsDao.queryEvents();
//		assertEquals(1, eventsList.size());
    }

    @Test
    public void testInsertEvents() {
        Event events = new Event();
        LocalDateTime date = LocalDateTime.now();
        events.setOnDatetime(new Date());
        events.setDescription("NBA");
        events.setLocation("howe");
        events.setCapacity(20);
        events.setTitle("Zhe");
        int result = eventsDao.insertEvent(events);
        assertEquals(0, result);
    }
}
