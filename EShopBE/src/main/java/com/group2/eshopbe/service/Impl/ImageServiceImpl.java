package com.group2.eshopbe.service.Impl;

import com.group2.eshopbe.entity.Image;
import com.group2.eshopbe.repository.ImageRepository;
import com.group2.eshopbe.service.ImageService;
import com.group2.eshopbe.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository repository;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        Image imageData = repository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData != null) {
            return "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    @Override
    public byte[] downloadImage(Long id) {
        Optional<Image> dbImageData = repository.findById(id);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }
}
