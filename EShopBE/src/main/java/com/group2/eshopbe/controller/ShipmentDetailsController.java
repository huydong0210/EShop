package com.group2.eshopbe.controller;

import com.group2.eshopbe.DTO.Mapper;
import com.group2.eshopbe.DTO.ShipmentDetailsDTO;
import com.group2.eshopbe.entity.ShipmentDetails;
import com.group2.eshopbe.payload.response.ResponseObject;
import com.group2.eshopbe.repository.UserRepository;
import com.group2.eshopbe.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/shipment-details")
public class ShipmentDetailsController {
    @Autowired
    UserRepository userRepository;
    @GetMapping
    public ResponseEntity<ResponseObject> getAllShipmentDetails(){
        List<ShipmentDetails> shipmentDetailsList = userRepository.findByUsername(SecurityUtils.getCurrentUserLogin().get())
                .get().getShipmentDetailsList();
        List<ShipmentDetailsDTO> shipmentDetailsDTOS = new ArrayList<>();
        shipmentDetailsList.stream().forEach(shipmentDetails -> {
            shipmentDetailsDTOS.add(Mapper.buildShipmentDetailsDTO(shipmentDetails));
        });
        return ResponseEntity.ok(new ResponseObject(
                ResponseObject.SUCCESS,
                "",
                Mapper.convertObjectToJson(shipmentDetailsDTOS)
        ));

    }
}
