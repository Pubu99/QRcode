package com.etqr.sbqrcodedemo.model.order;

import com.etqr.sbqrcodedemo.utils.EmailService;
import com.etqr.sbqrcodedemo.utils.QRCodeGenerator;
import com.google.zxing.WriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final EmailService emailService;

    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public Order addOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable("id") Long id) {
        Order order = orderService.findById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/generateQR/{orderId}")
    public ResponseEntity<String> generateQR(@PathVariable("orderId") Long orderId) throws IOException, WriterException, MessagingException {
        Order order = orderService.findById(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        String qrCodePath = QRCodeGenerator.generateQRCode(order);
        emailService.sendEmailWithAttachment(
                order.getUserInfo().getEmail(), // Email from UserInfo
                "EasyTicket.LK | Your Event E-Ticket",
                "Attached: E-Ticket (QR Code) for Your Order—Click, Book and Enjoy!",
                new File(qrCodePath)
        );

        return ResponseEntity.ok("QR code generated and email sent successfully.");
    }
}
