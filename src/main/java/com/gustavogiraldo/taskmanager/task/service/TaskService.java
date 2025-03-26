package com.gustavogiraldo.taskmanager.task.service;

import com.gustavogiraldo.taskmanager.task.entity.Task;
import com.gustavogiraldo.taskmanager.task.repository.TaskRepository;
import com.gustavogiraldo.taskmanager.user.entity.Role;
import com.gustavogiraldo.taskmanager.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(String id) {
        return taskRepository.findById(id);
    }

    public List<Task> getTasksByUser(User user) {
        if (user.getRole() == Role.ADMIN) {
            return taskRepository.findAll();
        }
        return taskRepository.findByUserId(user.getId());
    }


    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }
}
