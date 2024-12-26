package com.example.trelloteamproject.awss3;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class AwsS3FileController {
    private final AwsS3FileService awsS3Service;

    @PostMapping
    public ResponseEntity<String> uploadFile(MultipartFile multipartFile){
        return ResponseEntity.ok((awsS3Service.uploadFile(multipartFile)));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFile(@RequestParam String fileName){
        awsS3Service.deleteFile(fileName);
        return ResponseEntity.ok(fileName);
    }
}
