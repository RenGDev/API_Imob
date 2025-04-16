package com.lorenzo.api_imoveis.controller;

import com.lorenzo.api_imoveis.services.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        return cloudinaryService.uploadFile(file);
    }
}