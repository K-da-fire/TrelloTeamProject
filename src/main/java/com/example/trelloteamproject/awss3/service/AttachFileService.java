package com.example.trelloteamproject.awss3.service;

import com.example.trelloteamproject.awss3.entity.AttachFile;
import com.example.trelloteamproject.card.entity.Card;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AttachFileService {
    AttachFile uploadFile(MultipartFile multipartFile);

    AttachFile findByFileNameOrElseThrow(String fileName);

    void deleteFile(String fileName);
}
