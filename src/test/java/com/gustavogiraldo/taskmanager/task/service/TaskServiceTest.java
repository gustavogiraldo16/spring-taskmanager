package com.gustavogiraldo.taskmanager.task.service;

import com.gustavogiraldo.taskmanager.task.entity.Task;
import com.gustavogiraldo.taskmanager.task.entity.TaskPriority;
import com.gustavogiraldo.taskmanager.task.entity.TaskStatus;
import com.gustavogiraldo.taskmanager.task.repository.TaskRepository;
import com.gustavogiraldo.taskmanager.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    public Task taskPrepared;

    public Task taskModifiedPrepared;

    public User userPrepared;

    public final String TASK_ID_PREPARED = "3a29ae7f-3fb3-428c-aa50-9f411c438aaf";

    @BeforeEach
    void setUp() {

        userPrepared = new User();
        userPrepared.setId("3d7561e2-3b82-4cde-af46-7dc8f844d8e4");
        userPrepared.setName("gustavo");
        userPrepared.setEmail("prueba7@prueba.com");

        taskPrepared = new Task();
        taskPrepared.setId("3a29ae7f-3fb3-428c-aa50-9f411c438aaf");
        taskPrepared.setTitle("Prueba");
        taskPrepared.setDescription("Prueba descripción");
        taskPrepared.setStatus(TaskStatus.PENDING);
        taskPrepared.setPriority(TaskPriority.LOW);
        taskPrepared.setDueDate(LocalDate.of(2025, 3, 28));
        taskPrepared.setUser(userPrepared);
        taskPrepared.setCreatedAt(LocalDateTime.of(2025, 3, 28, 15, 59));
        taskPrepared.setUpdatedAt(LocalDateTime.of(2025, 3, 28, 15, 59));

    }

    @Test
    void getAllTasksTest() {
        List<Task> taskMock = new ArrayList<>(List.of(taskPrepared));
        when(taskRepository.findAll()).thenReturn(taskMock);
        List<Task> tasks = taskService.getAllTasks();
        assertEquals(1, tasks.size());
    }

    @Test
    void getTaskByIdTest() {
        when(taskRepository.findById(TASK_ID_PREPARED)).thenReturn(Optional.ofNullable(taskPrepared));
        Optional<Task> taskOptional = taskService.getTaskById(TASK_ID_PREPARED);

        assertTrue(taskOptional.isPresent(), "La tarea debería estar presente");

        // Si está presente, mapeamos el Optional a un Task
        taskOptional.ifPresent(task -> {
            // Verificamos que el ID de la tarea que se obtuvo es el esperado
            assertEquals(TASK_ID_PREPARED, task.getId(), "El ID de la tarea no coincide");
        });
    }

    @Test
    void getTaskByIdNotFoundTest() {
        when(taskRepository.findById(TASK_ID_PREPARED)).thenReturn(Optional.empty());
        Optional<Task> taskOptional = taskService.getTaskById(TASK_ID_PREPARED);
        assertFalse(taskOptional.isPresent(), "La tarea no debería estar presente");
    }

    @Test
    void getTasksByUserTest() {
    }

    @Test
    void saveTaskTest() {
    }

    @Test
    void deleteTaskTest() {
    }

    @Test
    void existsByIdTest() {
    }
}