package com.group2.eshopbe.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group2.eshopbe.DTO.Mapper;
import com.group2.eshopbe.DTO.ProductDTO;
import com.group2.eshopbe.entity.Product;
import com.group2.eshopbe.payload.response.ResponseObject;
import com.group2.eshopbe.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<ResponseObject> getAllProducts() throws JsonProcessingException {
        List<ProductDTO> productDTOList = new ArrayList<>();
        List<Product> products = productRepository.findAllProducts();
        products.stream().forEach(product -> {
            productDTOList.add(Mapper.buildProductDTO(product));
        });
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(ResponseObject.SUCCESS, "", Mapper.convertObjectToJson(productDTOList)));
    }
    @GetMapping("/{productID}")
    public ResponseEntity<ResponseObject> getProductDTOByID(@PathVariable Long productID){
        Product product = productRepository.findById(productID).get();
        return ResponseEntity.ok(new ResponseObject(
                ResponseObject.SUCCESS,
                "",
                Mapper.convertObjectToJson(Mapper.buildProductDTO(product))
        ));


    }
}
