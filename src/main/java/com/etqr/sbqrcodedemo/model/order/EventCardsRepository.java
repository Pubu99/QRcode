package com.etqr.sbqrcodedemo.model.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCardsRepository extends JpaRepository<EventCards, Long> {
    // You can define additional query methods here if needed
}
