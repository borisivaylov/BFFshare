package com.example.bffshare.api.security.authentication;

import com.example.bffshare.api.base.OperationInput;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest implements OperationInput {

    private String email;

    private String password;
}
