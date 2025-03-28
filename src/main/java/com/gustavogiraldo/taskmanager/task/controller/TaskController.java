package com.gustavogiraldo.taskmanager.task.controller;

import com.gustavogiraldo.taskmanager.task.dto.TaskResponseDTO;
import com.gustavogiraldo.taskmanager.task.entity.Task;
import com.gustavogiraldo.taskmanager.task.service.TaskService;
import com.gustavogiraldo.taskmanager.user.dto.UserResponseDTO;
import com.gustavogiraldo.taskmanager.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "BearerAuth") // Indica que este controlador requiere autenticación con JWT
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Tag(name = "Task", description = "API para gestionar tareas") // Etiqueta de Swagger para el grupo de endpoints
public class TaskController {

    private final TaskService taskService;

    @Operation(summary = "Obtener todas las tareas del usuario autenticado")
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getTasks(@AuthenticationPrincipal User user) {
        System.out.println("usuario " + user.getId());
        List<Task> tasks = taskService.getTasksByUser(user);
        List<TaskResponseDTO> taskDTOs = tasks.stream()
                .map(this::convertToTaskDTO)
                .toList();
        return ResponseEntity.ok(taskDTOs);
    }

    @Operation(summary = "Obtener una tarea por su ID")
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id)
                .map(this::convertToTaskDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva tarea")
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@AuthenticationPrincipal User user, @RequestBody Task task) {
        task.setUser(user);
        Task saveTask = taskService.saveTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToTaskDTO(saveTask));
    }

    @Operation(summary = "Actualizar una tarea existente")
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

    @Operation(summary = "Eliminar una tarea por su ID")
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
