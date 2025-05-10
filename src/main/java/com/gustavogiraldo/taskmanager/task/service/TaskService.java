package com.gustavogiraldo.taskmanager.task.service;

import com.gustavogiraldo.taskmanager.task.entity.Task;
import com.gustavogiraldo.taskmanager.task.entity.TaskPriority;
import com.gustavogiraldo.taskmanager.task.entity.TaskStatus;
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

    public List<Task> getTasksByUser(User user, String statusStr, String priorityStr) {
        TaskStatus status = parseEnum(TaskStatus.class, statusStr);   // Usamos el método para 'status'
        TaskPriority priority = parseEnum(TaskPriority.class, priorityStr); // Usamos el método para 'priority'

        if (user.getRole() == Role.ADMIN) {
            return taskRepository.findAllWithOptionalFilters(status, priority);
        }
        return taskRepository.findByUserIdWithOptionalFilters(user.getId(), status, priority);
    }

    public Task saveTask(Task task) {
        if (task.getUser() == null) {
            throw new IllegalArgumentException("El usuario no puede estar vacio");
        }
        return taskRepository.save(task);
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }

    public boolean existsById(String id) {
        return taskRepository.existsById(id);
    }

    private <T extends Enum<T>> T parseEnum(Class<T> enumClass, String value) {
        if (value == null) {
            return null;
        }
        try {
            return Enum.valueOf(enumClass, value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Valor inválido para " + enumClass.getSimpleName() + ": " + value);
        }
    }

}
