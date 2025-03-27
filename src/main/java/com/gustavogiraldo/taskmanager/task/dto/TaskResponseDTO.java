package com.gustavogiraldo.taskmanager.task.dto;

import com.gustavogiraldo.taskmanager.task.entity.TaskPriority;
import com.gustavogiraldo.taskmanager.task.entity.TaskStatus;
import com.gustavogiraldo.taskmanager.user.dto.UserResponseDTO;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TaskResponseDTO {
    private String id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDate dueDate;
    private TaskPriority priority;
    private UserResponseDTO user;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
