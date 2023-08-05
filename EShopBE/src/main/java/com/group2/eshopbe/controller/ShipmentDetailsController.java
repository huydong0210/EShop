package com.group2.eshopbe.controller;

import com.group2.eshopbe.DTO.Mapper;
import com.group2.eshopbe.DTO.ShipmentDetailsDTO;
import com.group2.eshopbe.entity.EUser;
import com.group2.eshopbe.entity.OrderDetails;
import com.group2.eshopbe.entity.ShipmentDetails;
import com.group2.eshopbe.payload.response.ResponseObject;
import com.group2.eshopbe.repository.OrderDetailsRepository;
import com.group2.eshopbe.repository.ShipmentDetailsRepository;
import com.group2.eshopbe.repository.UserRepository;
import com.group2.eshopbe.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/shipment-details")
public class ShipmentDetailsController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShipmentDetailsRepository shipmentDetailsRepository;

    @GetMapping
    public ResponseEntity<ResponseObject> getAllShipmentDetails() {
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

    @PostMapping
    public ResponseEntity<ResponseObject> addNewShipmentDetails(@RequestBody ShipmentDetailsDTO shipmentDetailsDTO) {
        ShipmentDetails shipmentDetails = new ShipmentDetails();
        EUser user = userRepository.findByUsername(SecurityUtils.getCurrentUserLogin().get()).get();

        shipmentDetails.setUser(user);
        shipmentDetails.setPhone(shipmentDetailsDTO.getPhone());
        shipmentDetails.setAddress(shipmentDetailsDTO.getAddress());
        shipmentDetails.setFullName(shipmentDetailsDTO.getFullName());

        shipmentDetailsRepository.save(shipmentDetails);

        return ResponseEntity.ok(new ResponseObject(
                ResponseObject.SUCCESS,
                "",
                ""
        ));
    }
}
