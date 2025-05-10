package com.gustavogiraldo.taskmanager.task.dto;

import com.gustavogiraldo.taskmanager.task.entity.TaskPriority;
import com.gustavogiraldo.taskmanager.task.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequestDTO {

    @NotBlank(message = "El título no puede estar vacío")
    private String title;

    private String description;

    @NotNull(message = "El estado es obligatorio")
    private TaskStatus status;

    @NotNull(message = "La fecha de vencimiento es obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @NotNull(message = "La prioridad es obligatoria")
    private TaskPriority priority;

    private String user_id;

}
