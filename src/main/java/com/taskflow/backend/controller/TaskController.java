
package com.taskflow.backend.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import java.util.List;
import com.taskflow.backend.service.TaskService;
import com.taskflow.backend.entity.Task;
import com.taskflow.backend.dto.TaskRequest;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

 private final TaskService taskService;

 @GetMapping
 public ResponseEntity<List<Task>> getTasks(Authentication auth) {
  return ResponseEntity.ok(taskService.getTasks(auth.getName()));
 }

 @PostMapping
 public ResponseEntity<Task> createTask(Authentication auth,
   @RequestBody TaskRequest request) {
  return ResponseEntity.status(HttpStatus.CREATED)
   .body(taskService.createTask(auth.getName(), request));
 }

 @PutMapping("/{id}")
 public ResponseEntity<Task> updateTask(@PathVariable Long id,
   Authentication auth,
   @RequestBody TaskRequest request) {
  return ResponseEntity.ok(
   taskService.updateTask(id, auth.getName(), request));
 }

 @DeleteMapping("/{id}")
 public ResponseEntity<?> deleteTask(@PathVariable Long id,
   Authentication auth) {
  taskService.deleteTask(id, auth.getName());
  return ResponseEntity.noContent().build();
 }
}
