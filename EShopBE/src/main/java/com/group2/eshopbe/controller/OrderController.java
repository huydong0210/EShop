package com.group2.eshopbe.controller;

import com.group2.eshopbe.entity.Order;
import com.group2.eshopbe.payload.response.ResponseObject;
import com.group2.eshopbe.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/{userID}")
    public ResponseEntity<ResponseObject> getAllOrdersByUserID(@PathVariable Long userID) {
        List<Order> orders = orderRepository.findAllByUserID(userID);
        return ResponseEntity.ok(new ResponseObject(ResponseObject.SUCCESS, "", orders));
    }
}
