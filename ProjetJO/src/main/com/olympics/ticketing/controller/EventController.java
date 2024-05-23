package com.olympics.ticketing.controller;

import com.olympics.ticketing.dto.EventDTO;
import com.olympics.ticketing.model.Event;
import com.olympics.ticketing.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Event> createEvent(@RequestBody EventDTO eventDTO) {
        Event event = eventService.createEvent(eventDTO);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        Event updatedEvent = eventService.updateEvent(id, eventDTO);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEvent(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PutMapping("/{id}/open")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Event> openEvent(@PathVariable Long id) {
        Event event = eventService.openEvent(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PutMapping("/{id}/close")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Event> closeEvent(@PathVariable Long id) {
        Event event = eventService.closeEvent(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PutMapping("/{id}/postpone")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Event> postponeEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        Event event = eventService.postponeEvent(id, eventDTO);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PutMapping("/{id}/cancel")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Event> cancelEvent(@PathVariable Long id) {
        Event event = eventService.cancelEvent(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }
}
