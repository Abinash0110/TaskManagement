
package com.taskflow.backend.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import com.taskflow.backend.repository.*;
import com.taskflow.backend.entity.*;
import com.taskflow.backend.dto.*;

@Service
@RequiredArgsConstructor
public class TaskService {

 private final TaskRepository taskRepository;
 private final UserRepository userRepository;

 public List<Task> getTasks(String email) {
  User user = userRepository.findByEmail(email).orElseThrow();
  return taskRepository.findByUser(user);
 }

 public Task createTask(String email, TaskRequest request) {
  User user = userRepository.findByEmail(email).orElseThrow();
  Task task = new Task();
  task.setTitle(request.getTitle());
  task.setDescription(request.getDescription());
  task.setDueDate(request.getDueDate());
  task.setStatus(request.getStatus());
  task.setUser(user);
  return taskRepository.save(task);
 }

 public Task updateTask(Long id, String email, TaskRequest request) {
  Task task = taskRepository.findById(id).orElseThrow();
  if(!task.getUser().getEmail().equals(email))
   throw new RuntimeException("Forbidden");
  task.setTitle(request.getTitle());
  task.setDescription(request.getDescription());
  task.setDueDate(request.getDueDate());
  task.setStatus(request.getStatus());
  return taskRepository.save(task);
 }

 public void deleteTask(Long id, String email) {
  Task task = taskRepository.findById(id).orElseThrow();
  if(!task.getUser().getEmail().equals(email))
   throw new RuntimeException("Forbidden");
  taskRepository.delete(task);
 }
}
