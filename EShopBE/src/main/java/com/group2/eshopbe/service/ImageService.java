package com.group2.eshopbe.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Service
@Transactional
public interface ImageService {
    public String uploadImage(MultipartFile file) throws IOException;
    public byte[] downloadImage(Long id);
}
