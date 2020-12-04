package com.example.farmmarketback.service;

import com.example.farmmarketback.repository.UploadRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UploadService {

    @Autowired
    private UploadRepo uploadRepo;

    public void uploadFile(byte [] file){
        uploadRepo.uploadFile(file);
    }
}
