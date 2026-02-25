
package com.taskflow.backend.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
public class Task {

 @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @Column(nullable=false)
 private String title;

 private String description;

 @Column(nullable=false)
 private LocalDate dueDate;

 @Enumerated(EnumType.STRING)
 private Status status = Status.TODO;

 @ManyToOne
 @JoinColumn(name="user_id")
 private User user;
}
