package com.C10G14.WorldFitBackend.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    boolean checkImage (MultipartFile image);
    String uploadImage (MultipartFile image, String userEmail);
}
