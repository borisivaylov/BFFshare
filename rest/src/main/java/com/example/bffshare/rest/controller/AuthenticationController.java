package com.example.bffshare.rest.controller;

import com.example.bffshare.api.security.authentication.AuthenticationRequest;
import com.example.bffshare.api.security.authentication.AuthenticationResponse;
import com.example.bffshare.api.security.register.RegisterRequest;
import com.example.bffshare.api.security.register.RegisterResponse;
import com.example.bffshare.core.mergeresponse.authenticate.RegisterOperationProcessor;
import com.example.bffshare.core.mergeresponse.register.AuthenticateOperationProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticateOperationProcessor authenticateOperationProcessor;
    private final RegisterOperationProcessor registerOperationProcessor;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) throws Exception {
        return  ResponseEntity.ok(registerOperationProcessor.process(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        return  ResponseEntity.ok(authenticateOperationProcessor.process(authenticationRequest));
    }
    @GetMapping
    public ResponseEntity check(){
        return ResponseEntity.ok(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
