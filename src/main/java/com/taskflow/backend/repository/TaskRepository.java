
package com.taskflow.backend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.taskflow.backend.entity.Task;
import com.taskflow.backend.entity.User;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
 List<Task> findByUser(User user);
}
