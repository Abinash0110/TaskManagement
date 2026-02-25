
package com.taskflow.backend.dto;
import lombok.*;
import java.time.LocalDate;
import com.taskflow.backend.entity.Status;

@Getter @Setter
public class TaskRequest {
 private String title;
 private String description;
 private LocalDate dueDate;
 private Status status;
}
