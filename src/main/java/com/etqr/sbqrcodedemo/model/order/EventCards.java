package com.etqr.sbqrcodedemo.model.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventCards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // Ensure eventId is unique
    private String eventId;
    private String eventName;
    private String eventLocation;
    private String eventTime;
    private String eventDate;
    private String eventDescription;
    private String ticketDetails;
    private String eventCategory;
    private String flyerLink;
}
