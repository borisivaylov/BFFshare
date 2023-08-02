package com.example.bffshare.api.security.register;

import com.example.bffshare.api.base.OperationInput;
import com.example.bffshare.persistence.entity.Roles;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest implements OperationInput {

    private UUID uuid;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    @Enumerated
    private Roles role = Roles.CUSTOMER;

}
