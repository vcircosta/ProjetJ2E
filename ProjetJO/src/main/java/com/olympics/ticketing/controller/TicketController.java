package com.olympics.ticketing.controller;

import com.olympics.ticketing.dto.GroupTicketRequestDTO;
import com.olympics.ticketing.dto.TicketDTO;
import com.olympics.ticketing.model.Ticket;
import com.olympics.ticketing.model.User;
import com.olympics.ticketing.service.TicketService;
import com.olympics.ticketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Ticket> purchaseTicket(@RequestBody TicketDTO ticketDTO, @AuthenticationPrincipal User user) {
        Ticket ticket = ticketService.purchaseTicket(ticketDTO, user);
        return new ResponseEntity<>(ticket, HttpStatus.CREATED);
    }

    @PostMapping("/group")
    public ResponseEntity<List<Ticket>> purchaseGroupTickets(@RequestBody GroupTicketRequestDTO groupTicketRequestDTO, @AuthenticationPrincipal User user) {
        List<Ticket> tickets = ticketService.purchaseGroupTickets(groupTicketRequestDTO, user);
        return new ResponseEntity<>(tickets, HttpStatus.CREATED);
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> getUserTickets(@AuthenticationPrincipal User user) {
        List<Ticket> tickets = ticketService.getTicketsByUser(user);
        return new ResponseEntity<>(tickets, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelTicket(@PathVariable Long id, @AuthenticationPrincipal User user) {
        ticketService.cancelTicket(id, user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
