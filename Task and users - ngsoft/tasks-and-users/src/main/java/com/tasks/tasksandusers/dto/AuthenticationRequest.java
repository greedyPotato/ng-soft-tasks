package com.tasks.tasksandusers.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthenticationRequest {
    
    @NotNull
    @Size(max = 255)
    private String userName;

    @NotNull
    @Size(max = 255)
    private String password;
}
