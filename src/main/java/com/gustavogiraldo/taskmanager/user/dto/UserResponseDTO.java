package com.gustavogiraldo.taskmanager.user.dto;

import lombok.Data;
import java.util.UUID;

@Data
public class UserResponseDTO {
    private String id;
    private String name;
    private String email;
}
