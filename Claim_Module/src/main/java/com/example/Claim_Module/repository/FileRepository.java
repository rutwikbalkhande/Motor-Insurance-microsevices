package com.example.Claim_Module.repository;

import com.example.Claim_Module.entity.FileHandling;
import org.hibernate.Internal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface FileRepository extends JpaRepository<FileHandling,Long> {

    Optional<FileHandling> findFirstByUserId(Long userId);
}
