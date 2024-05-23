package com.olympics.ticketing.controller;

import com.olympics.ticketing.dto.EventDTO;
import com.olympics.ticketing.dto.UserDTO;
import com.olympics.ticketing.model.Event;
import com.olympics.ticketing.model.User;
import com.olympics.ticketing.service.EventService;
import com.olympics.ticketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;


    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User user = userService.registerUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User updatedUser = userService.updateUser(id, userDTO);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/events")
    public ResponseEntity<Event> createEvent(@RequestBody EventDTO eventDTO) {
        Event event = eventService.createEvent(eventDTO);
        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        Event updatedEvent = eventService.updateEvent(id, eventDTO);
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    @DeleteMapping("/events/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/events/{id}/open")
    public ResponseEntity<Event> openEvent(@PathVariable Long id) {
        Event event = eventService.openEvent(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PutMapping("/events/{id}/close")
    public ResponseEntity<Event> closeEvent(@PathVariable Long id) {
        Event event = eventService.closeEvent(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PutMapping("/events/{id}/postpone")
    public ResponseEntity<Event> postponeEvent(@PathVariable Long id, @RequestBody EventDTO eventDTO) {
        Event event = eventService.postponeEvent(id, eventDTO);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PutMapping("/events/{id}/cancel")
    public ResponseEntity<Event> cancelEvent(@PathVariable Long id) {
        Event event = eventService.cancelEvent(id);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }
}
