package com.group2.eshopbe.controller;

import com.group2.eshopbe.entity.OrderDetails;
import com.group2.eshopbe.payload.response.ResponseObject;
import com.group2.eshopbe.repository.OrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailsController {
    @Autowired
    OrderDetailsRepository orderDetailsRepository;
    @GetMapping("/{orderID}")
    public ResponseEntity<ResponseObject> getAllOrderDetailsByOrderId(@PathVariable Long orderID){
        List<OrderDetails> orderDetailsList = orderDetailsRepository.findOrderDetailsByOderID(orderID);
        return ResponseEntity.ok(new ResponseObject(ResponseObject.SUCCESS, "", orderDetailsList));
    }

}
