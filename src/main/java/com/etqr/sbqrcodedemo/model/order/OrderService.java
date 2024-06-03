package com.etqr.sbqrcodedemo.model.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserInfoRepository userInfoRepository;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order findById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public UserInfo findUserInfoById(String userId) {
        return userInfoRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
