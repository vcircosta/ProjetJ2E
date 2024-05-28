package com.olympics.ticketing.service;

import com.olympics.ticketing.dto.GroupTicketRequestDTO;
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
    public Ticket purchaseTicket(TicketDTO ticketDTO, User user) {
        Event event = eventRepository.findById(ticketDTO.getEventId())
                .orElseThrow(() -> new CustomException("Event not found"));

        if (!event.isOpen()) {
            throw new CustomException("Event is not open for ticket purchase");
        }

        Ticket ticket = new Ticket();
        ticket.setEvent(event);
        ticket.setUser(user);
        ticket.setQuantity(ticketDTO.getQuantity());
        ticket.setGroupPurchase(ticketDTO.isGroupPurchase());

        return ticketRepository.save(ticket);
    }

    public List<Ticket> getTicketsByUser(User user) {
        return ticketRepository.findByUser(user);
    }

    public List<Ticket> getTicketsByEvent(Event event) {
        return ticketRepository.findByEvent(event);
    }

    @Transactional
    public List<Ticket> purchaseGroupTickets(GroupTicketRequestDTO groupTicketRequestDTO, User user) {
        Event event = eventRepository.findById(groupTicketRequestDTO.getEventId())
                .orElseThrow(() -> new CustomException("Event not found"));

        if (!event.isOpen()) {
            throw new CustomException("Event is not open for ticket purchase");
        }

        List<Ticket> tickets = groupTicketRequestDTO.getTickets();
        for (Ticket ticketDTO : tickets) {
            Ticket ticket = new Ticket();
            ticket.setEvent(event);
            ticket.setUser(user);
            ticket.setQuantity(ticketDTO.getQuantity());
            ticket.setGroupPurchase(true);
            ticketRepository.save(ticket);
        }

        return tickets;
    }

    @Transactional
    public void cancelTicket(Long id, User user) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new CustomException("Ticket not found"));

        if (!ticket.getUser().equals(user)) {
            throw new CustomException("You are not authorized to cancel this ticket");
        }

        ticketRepository.delete(ticket);
    }
}
