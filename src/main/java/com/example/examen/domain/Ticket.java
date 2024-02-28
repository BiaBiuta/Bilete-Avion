package com.example.examen.domain;

import java.time.LocalDateTime;

public class Ticket extends Entity<Long>{
    String useranme;
    Long flightId;
    LocalDateTime purchaseTime;

    public String getUseranme() {
        return useranme;
    }

    public void setUseranme(String useranme) {
        this.useranme = useranme;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public Ticket(String useranme, Long flightId, LocalDateTime purchaseTime) {
        this.useranme = useranme;
        this.flightId = flightId;
        this.purchaseTime = purchaseTime;
    }
}
