package com.hobbymatcher.dao;

import com.hobbymatcher.entity.Review;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.JVM)
public class ReviewDaoTest {

    @Autowired
    ReviewDao reviewDao;

    @Test
    public void testAddReview() {
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            Review review = new Review();
            review.setContent(UUID.randomUUID().toString());
            review.setOwnerType(random.nextInt(20) > 10 ? "event" : "blog");
            review.setOwnerId(10);
            review.setByUserId(1);
            review.setOnDateTime(new Date());
            reviewDao.addReview(review);
        }
    }

    @Test
    public void testListReviewByTypeAndId() {
        assertEquals(20, reviewDao.listReviewByTypeAndId("event", 10).size() + reviewDao.listReviewByTypeAndId("blog", 10).size());


    }

    @Test
    public void testDeleteReview() {
        for (int i = 1; i <= 20; i++) {
            reviewDao.deleteReview(i);
        }
        assertEquals(0, reviewDao.listReviewByTypeAndId("event", 1).size());

    }
}
