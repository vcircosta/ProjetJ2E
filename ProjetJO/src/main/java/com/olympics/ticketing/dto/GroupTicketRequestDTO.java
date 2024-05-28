package com.olympics.ticketing.dto;

import com.olympics.ticketing.model.Ticket;

import java.util.List;

public class GroupTicketRequestDTO {

    private Long eventId;
    private List<Long> userIds;
    private int quantity;

    // Getters and Setters

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Ticket> getTickets() {
        // Cette méthode pourrait être implémentée pour créer des objets Ticket à partir des informations fournies
        // Elle pourrait être utilisée dans le service TicketService pour acheter des billets en groupe
        // Mais dans ce cas, elle n'est pas nécessaire car la création des billets est gérée directement dans le service
        return null;
    }
}
