package com.gustavogiraldo.taskmanager.task.controller;

import com.gustavogiraldo.taskmanager.task.dto.TaskResponseDTO;
import com.gustavogiraldo.taskmanager.task.entity.Task;
import com.gustavogiraldo.taskmanager.task.service.TaskService;
import com.gustavogiraldo.taskmanager.user.dto.UserResponseDTO;
import com.gustavogiraldo.taskmanager.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getTasks(@AuthenticationPrincipal User user) {
        List<Task> tasks = taskService.getTasksByUser(user);
        List<TaskResponseDTO> taskDTOs = tasks.stream()
                .map(this::convertToTaskDTO)
                .toList();
        return ResponseEntity.ok(taskDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id)
                .map(this::convertToTaskDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@AuthenticationPrincipal User user, @RequestBody Task task) {
        task.setUser(user);
        Task saveTask = taskService.saveTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToTaskDTO(saveTask));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@AuthenticationPrincipal User user, @PathVariable String id, @RequestBody Task task) {
        if(!taskService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        task.setId(id);
        task.setUser(user);
        Task saveTask = taskService.saveTask(task);
        return ResponseEntity.ok(convertToTaskDTO(saveTask));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        if(!taskService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    // Método para convertir una tarea en DTO
    private TaskResponseDTO convertToTaskDTO(Task task) {
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setDueDate(task.getDueDate());
        dto.setPriority(task.getPriority());
        dto.setCreatedAt(task.getCreatedAt());
        dto.setUpdatedAt(task.getUpdatedAt());

        UserResponseDTO userDto = new UserResponseDTO();
        userDto.setId(task.getUser().getId());
        userDto.setName(task.getUser().getName());
        userDto.setEmail(task.getUser().getEmail());
        dto.setUser(userDto);

        return dto;
    }
}
