package com.hobbymatcher.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hobbymatcher.entity.Event;
import com.hobbymatcher.entity.Participation;

@Repository
public interface EventDao {

    List<Event> listEvent();

    List<Event> searchEvent(@Param("justCount") Boolean justCount, //
                            @Param("searchScope") String searchScope, // TODO - implement
                            @Param("searchPhrase") String searchPhrase, @Param("categoryIds") String[] categoryIds,
                            @Param("feeStart") Double feeStart, @Param("feeEnd") Double feeEnd, //
                            @Param("sortBy") String sortBy, // TODO - implement
                            @Param("offset") Integer offset, @Param("perpage") Integer perpage);

    int insertEvent(Event event);

    Event findEventById(@Param("id") int id);

    Event findEventByTitle(@Param("title") String title);

    List<Event> listEventByHobbyId(@Param("hobbyId") int id);

    List<Event> listPastJoinEvents(@Param("userId") int userId);

    List<Event> listJoinedFutureEvents(@Param("userId") int userId);

    List<Event> listPastHoldEvents(@Param("userId") int userId);

    int updateEvent(Event event);

    Participation getParticipation(@Param("userId") int userId, @Param("eventId") int eventId);

    void requestToParticipateEvent(@Param("userId") int userId, @Param("eventId") int eventId);

    void cancelEventParticipationRequest(@Param("userId") int userId, @Param("eventId") int eventId);

    void markEventAsParticipated(@Param("userId") int userId, @Param("eventId") int eventId);

    void approveUser(@Param("theUserId") int theUserId, @Param("eventId") int eventId);

    List<Event> listRecentEvents();
}