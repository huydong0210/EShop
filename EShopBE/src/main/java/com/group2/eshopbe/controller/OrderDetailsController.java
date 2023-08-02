package com.group2.eshopbe.controller;

import com.group2.eshopbe.DTO.Mapper;
import com.group2.eshopbe.DTO.OrderDetailsDTO;
import com.group2.eshopbe.common.Constants;
import com.group2.eshopbe.entity.EUser;
import com.group2.eshopbe.entity.OrderDetails;
import com.group2.eshopbe.entity.Product;
import com.group2.eshopbe.payload.response.ResponseObject;
import com.group2.eshopbe.repository.OrderDetailsRepository;
import com.group2.eshopbe.repository.ProductRepository;
import com.group2.eshopbe.repository.UserRepository;
import com.group2.eshopbe.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailsController {
    @Autowired
    OrderDetailsRepository orderDetailsRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @GetMapping
    public ResponseEntity<ResponseObject> getAllOrderDetailsInCart(){
        List<OrderDetails> orderDetailsList = userRepository.findByUsername(SecurityUtils.getCurrentUserLogin().get()).get().getOrderDetailsList();
        List<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<>();
        for (OrderDetails orderDetails : orderDetailsList){
            if (orderDetails.getStatus().equals(Constants.IN_CART)) {
                orderDetailsDTOS.add(Mapper.buildOrderDetailsDTO(orderDetails));
            }
        }
        return ResponseEntity.ok(new ResponseObject(ResponseObject.SUCCESS, "", Mapper.convertObjectToJson(orderDetailsDTOS)));
    }
    @DeleteMapping("/{productID}")
    public ResponseEntity<ResponseObject> deleteOrderDetailsInCartByProductID(@PathVariable Long productID){
        orderDetailsRepository.deleteOrderDetailsByProductId(productID);
        return null;

    }

    @PostMapping("/products/{id}")
    public ResponseEntity<ResponseObject> addProductToCart(@PathVariable Long id) {
        EUser user = userRepository.findByUsername(SecurityUtils.getCurrentUserLogin().get()).get();
        AtomicBoolean checkOrderDetailsExisted = new AtomicBoolean(false);
        user.getOrderDetailsList().stream().forEach(orderDetails -> {
            if (orderDetails.getProduct().getId() == id && orderDetails.getStatus().equals(Constants.IN_CART)) {
                orderDetails.setAmount(orderDetails.getAmount() + 1);
                orderDetailsRepository.save(orderDetails);
                checkOrderDetailsExisted.set(true);
            }
        });
        OrderDetails result =null;
        if (checkOrderDetailsExisted.get() == false) {
            OrderDetails orderDetails = new OrderDetails();
            Product product = productRepository.findById(id).get();
            orderDetails.setProduct(product);
            orderDetails.setAmount(1);
            orderDetails.setStatus(Constants.IN_CART);
            orderDetails.setUser(user);
            result =orderDetailsRepository.save(orderDetails);
        }
        return ResponseEntity.ok(new ResponseObject(ResponseObject.SUCCESS, "", ""));
    }

}
