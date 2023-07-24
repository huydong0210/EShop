package com.group2.eshopbe.controller;

import com.group2.eshopbe.payload.response.ResponseObject;
import com.group2.eshopbe.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    @Autowired
    ImageService imageService;
    @PostMapping("/product/{id}")
    public ResponseEntity<ResponseObject> uploadImage(@RequestParam("image")MultipartFile file , @PathVariable Long id) throws IOException {
        String uploadImage= imageService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(ResponseObject.SUCCESS, "",uploadImage));
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable Long id) throws IOException{
        byte[] imageData = imageService.downloadImage(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }
}
