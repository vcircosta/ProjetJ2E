package com.olympics.ticketing.dto;

public class TicketDTO {

    private Long eventId;
    private Long userId;
    private int quantity;
    private boolean groupPurchase;

    // Getters and Setters

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isGroupPurchase() {
        return groupPurchase;
    }

    public void setGroupPurchase(boolean groupPurchase) {
        this.groupPurchase = groupPurchase;
    }
}
