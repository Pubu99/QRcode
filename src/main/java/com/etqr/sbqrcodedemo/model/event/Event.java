package com.etqr.sbqrcodedemo.model.event;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventName;

    private Long eventId;
    private Long ticketId;
    private Long bookingId;
    private String userName;
    private String nicNumber;
    private String mobileNo;
    private String email;

    public Event(String eventName, Long eventId, Long ticketId, Long bookingId, String userName, String nicNumber, String mobileNo, String email) {
        this.eventName = eventName;
        this.eventId = eventId;
        this.ticketId = ticketId;
        this.bookingId = bookingId;
        this.userName = userName;
        this.nicNumber = nicNumber;
        this.mobileNo = mobileNo;
        this.email = email;
    }
}
