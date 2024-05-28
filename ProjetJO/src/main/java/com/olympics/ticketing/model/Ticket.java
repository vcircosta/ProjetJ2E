package com.olympics.ticketing.model;

import javax.persistence.*;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private boolean groupPurchase;

    // Constructors, Getters and Setters

    public Ticket() {
    }

    public Ticket(Event event, User user, int quantity, boolean groupPurchase) {
        this.event = event;
        this.user = user;
        this.quantity = quantity;
        this.groupPurchase = groupPurchase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
