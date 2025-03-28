package com.gustavogiraldo.taskmanager.task.repository;

import com.gustavogiraldo.taskmanager.task.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findByUserId(String userId);
}
