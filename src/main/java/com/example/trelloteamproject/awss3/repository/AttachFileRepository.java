package com.example.trelloteamproject.awss3.repository;

import com.example.trelloteamproject.awss3.entity.AttachFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttachFileRepository extends JpaRepository<AttachFile, Long> {
    Optional<AttachFile> findByFileName(String fileName);
}
