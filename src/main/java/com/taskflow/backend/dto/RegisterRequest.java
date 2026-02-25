
package com.taskflow.backend.dto;
import lombok.*;

@Getter @Setter
public class RegisterRequest {
 private String fullName;
 private String email;
 private String password;
}
