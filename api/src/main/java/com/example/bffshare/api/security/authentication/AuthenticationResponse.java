package com.example.bffshare.api.security.authentication;

import com.example.bffshare.api.base.OperationInput;
import com.example.bffshare.api.base.OperationResult;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse implements OperationResult {

    private String token;

}
