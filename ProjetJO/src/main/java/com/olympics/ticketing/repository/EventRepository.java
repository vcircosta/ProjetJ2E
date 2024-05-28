package com.olympics.ticketing.repository;

import com.olympics.ticketing.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByDate(LocalDateTime date);

    List<Event> findByLocation(String location);

    List<Event> findByOpen(boolean open);
}
