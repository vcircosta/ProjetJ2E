package com.olympics.ticketing.service;

import com.olympics.ticketing.dto.TicketDTO;
import com.olympics.ticketing.exception.CustomException;
import com.olympics.ticketing.model.Event;
import com.olympics.ticketing.model.Ticket;
import com.olympics.ticketing.model.User;
import com.olympics.ticketing.repository.EventRepository;
import com.olympics.ticketing.repository.TicketRepository;
import com.olympics.ticketing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Ticket purchaseTicket(TicketDTO ticketDTO) {
        Event event = eventRepository.findById(ticketDTO.getEventId())
                .orElseThrow(() -> new CustomException("Event not found"));
        User user = userRepository.findById(ticketDTO.getUserId())
                .orElseThrow(() -> new CustomException("User not found"));

        if (!event.isOpen()) {
            throw new CustomException("Event is not open for ticket purchase");
        }

        Ticket ticket = new Ticket();
        ticket.setEvent(event);
        ticket.setUser(user);
        ticket.setQuantity(ticketDTO.getQuantity());
        ticket.setGroupPurchase(ticketDTO.isGroupPurchase());

        if (ticketDTO.isGroupPurchase()) {
        }

        return ticketRepository.save(ticket);
    }

    public List<Ticket> getTicketsByUser(User user) {
        return ticketRepository.findByUser(user);
    }

    public List<Ticket> getTicketsByEvent(Event event) {
        return ticketRepository.findByEvent(event);
    }


}
