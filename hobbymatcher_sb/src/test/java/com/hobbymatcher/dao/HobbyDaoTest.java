package com.hobbymatcher.dao;

import com.hobbymatcher.entity.Hobby;
import com.hobbymatcher.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HobbyDaoTest {

    @Autowired
    HobbyDao hobbyDao;


    @Test
    public void testInsertHobby() {

        Hobby hobby = new Hobby();
        hobby.setCreatedById(1);
        hobby.setDescription("first hobby");
        hobby.setName("CS");
        hobby.setCategoryId(2);
        hobby.setPhotoId("");
        int result = hobbyDao.insertHobby(hobby);
        assertEquals(1, result);
    }
}
