package com.gustavogiraldo.taskmanager.task.repository;

import com.gustavogiraldo.taskmanager.task.entity.Task;
import com.gustavogiraldo.taskmanager.task.entity.TaskPriority;
import com.gustavogiraldo.taskmanager.task.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, String> {
    List<Task> findByUserId(String userId);

    @Query("SELECT t FROM Task t " +
            "WHERE (:status IS NULL OR t.status = :status) " +
            "AND (:priority IS NULL OR t.priority = :priority)")
    List<Task> findAllWithOptionalFilters(@Param("status") TaskStatus status,
                                          @Param("priority") TaskPriority priority);

    @Query("SELECT t FROM Task t " +
            "WHERE t.user.id = :userId " +
            "AND (:status IS NULL OR t.status = :status) " +
            "AND (:priority IS NULL OR t.priority = :priority)")
    List<Task> findByUserIdWithOptionalFilters(@Param("userId") String userId,
                                               @Param("status") TaskStatus status,
                                               @Param("priority") TaskPriority priority);

}
