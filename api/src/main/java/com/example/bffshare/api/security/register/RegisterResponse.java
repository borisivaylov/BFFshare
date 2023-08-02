package com.example.bffshare.api.security.register;

import com.example.bffshare.api.base.OperationResult;
import com.example.bffshare.persistence.entity.Roles;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterResponse implements OperationResult {


    private String jwt;
}
