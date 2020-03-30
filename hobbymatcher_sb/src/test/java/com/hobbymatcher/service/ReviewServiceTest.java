package com.hobbymatcher.service;

import com.hobbymatcher.entity.Review;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.JVM)
@Ignore
public class ReviewServiceTest {

    @Autowired
    ReviewService reviewService;

    @Test
    public void testReviewAdd() {

        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            Review review = new Review();
            review.setContent(UUID.randomUUID().toString());
            review.setOwnerType(random.nextInt(20) > 10 ? "event" : "blog");
            review.setOwnerId(10);
            review.setByUserId(1);
            review.setOnDateTime(new Date());
            reviewService.addReview(review);
        }
    }

    @Test
    public void testListReviewByTypeAndId() {
        assertEquals(20, reviewService.listReviewByTypeAndId("event", 10).size() + reviewService.listReviewByTypeAndId("blog", 10).size());
    }

    @Test
    public void testReviewDelete() {
        for (int i = 1; i <= 20; i++) {
            reviewService.deleteReview(i);
        }
        assertNull(reviewService.listReviewByTypeAndId("event", 10));

    }
}
