package com.example.trelloteamproject.awss3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.trelloteamproject.awss3.entity.AttachFile;
import com.example.trelloteamproject.awss3.repository.AttachFileRepository;
import com.example.trelloteamproject.board.entity.Board;
import com.example.trelloteamproject.board.repository.BoardRepository;
import com.example.trelloteamproject.card.entity.Card;
import com.example.trelloteamproject.card.repository.CardRepository;
import com.example.trelloteamproject.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static com.example.trelloteamproject.exception.ErrorCode.NOT_FOUND_FILE;

@Service
@Slf4j
@RequiredArgsConstructor
public class AttachFileServiceImpl implements AttachFileService {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AttachFileRepository attachFileRepository;
    private final AmazonS3 amazonS3;

    @Transactional
    @Override
    public AttachFile uploadFile(MultipartFile multipartFile){
        if (multipartFile == null || multipartFile.isEmpty()) {
            return null;
        }
        String fileName = createFileName(multipartFile.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        try(InputStream inputStream = multipartFile.getInputStream()){
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
        }
        String filePath = "https://trello-team-project.s3.ap-northeast-2.amazonaws.com/" + fileName;
        AttachFile attachFile = new AttachFile(fileName, filePath);
        attachFileRepository.save(attachFile);

        return attachFile;
    }

    @Override
    public AttachFile findByFileNameOrElseThrow(String fileName) {
        AttachFile attachFile = attachFileRepository.findByFileName(fileName).orElseThrow(() -> new NotFoundException(NOT_FOUND_FILE));
        return attachFile;
    }

    @Transactional
    @Override
    public void deleteFile(String fileName){
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
        AttachFile attachFile = findByFileNameOrElseThrow(fileName);
        attachFileRepository.delete(attachFile);
    }

    // 파일명을 난수화하기 위해 UUID 를 활용하여 난수를 돌린다.
    public String createFileName(String fileName){
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    //  "."의 존재 유무만 판단
    private String getFileExtension(String fileName){
        try{
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일" + fileName + ") 입니다.");
        }
    }
}
