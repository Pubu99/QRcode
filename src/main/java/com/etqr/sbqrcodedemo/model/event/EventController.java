package com.etqr.sbqrcodedemo.model.event;

import com.etqr.sbqrcodedemo.utils.QRCodeGenerator;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {
    private final EventService eventService;

    @GetMapping
    public ResponseEntity<List<Event>> getEvents() throws IOException, WriterException {
        List<Event> events = eventService.getEvents();
        if (!events.isEmpty()) {
            for (Event event : events) {
                QRCodeGenerator.generateQRCode(event);
            }
        }
        return ResponseEntity.ok(events);
    }

    @PostMapping
    public Event addEvent(@RequestBody Event event) {
        return eventService.addEvent(event);
    }

    @GetMapping("/{id}")
    public Event findById(@PathVariable("id") Long id) {
        return eventService.findById(id);
    }
}
