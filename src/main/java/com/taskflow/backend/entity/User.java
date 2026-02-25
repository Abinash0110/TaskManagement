
package com.taskflow.backend.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class User {

 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @Column(nullable=false)
 private String fullName;

 @Column(unique=true, nullable=false)
 private String email;

 @Column(nullable=false)
 private String passwordHash;

 private LocalDateTime createdAt = LocalDateTime.now();
}
