package com.olympics.ticketing.service;

import com.olympics.ticketing.dto.EventDTO;
import com.olympics.ticketing.exception.CustomException;
import com.olympics.ticketing.model.Event;
import com.olympics.ticketing.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Transactional
    public Event createEvent(EventDTO eventDTO) {
        Event event = new Event();
        event.setName(eventDTO.getName());
        event.setDate(eventDTO.getDate());
        event.setLocation(eventDTO.getLocation());
        event.setOpen(false); // By default, events are not open for ticket purchase

        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new CustomException("Event not found"));
    }

    @Transactional
    public Event updateEvent(Long id, EventDTO eventDTO) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new CustomException("Event not found"));

        event.setName(eventDTO.getName());
        event.setDate(eventDTO.getDate());
        event.setLocation(eventDTO.getLocation());

        return eventRepository.save(event);
    }

    @Transactional
    public void deleteEvent(Long id) {
        // Vérifier si l'événement existe
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new CustomException("Event not found"));

        // Supprimer l'événement de la base de données
        eventRepository.delete(event);
    }

    @Transactional
    public Event openEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new CustomException("Event not found"));

        event.setOpen(true); // Ouvrir l'événement pour l'achat de billets

        return eventRepository.save(event);
    }

    @Transactional
    public Event closeEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new CustomException("Event not found"));

        event.setOpen(false); // Fermer l'événement pour l'achat de billets

        return eventRepository.save(event);
    }

    @Transactional
    public Event postponeEvent(Long id, EventDTO eventDTO) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new CustomException("Event not found"));

        event.setDate(eventDTO.getDate()); // Mettre à jour la date de l'événement

        return eventRepository.save(event);
    }

    @Transactional
    public Event cancelEvent(Long id) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new CustomException("Event not found"));

        eventRepository.delete(event); // Supprimer l'événement de la base de données

        return event;
    }
}