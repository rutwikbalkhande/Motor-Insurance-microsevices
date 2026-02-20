package com.example.Claim_Module.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="filehandling")
@Data
public class FileHandling {

     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private Long id;

     @Column(nullable = false)
     private Long userId;

    private String filename;

    private String fileType;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] data;
}
