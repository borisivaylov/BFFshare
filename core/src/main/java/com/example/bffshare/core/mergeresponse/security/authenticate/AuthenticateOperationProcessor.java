package com.example.bffshare.core.mergeresponse.security.authenticate;

import com.example.bffshare.api.security.authentication.AuthenticationOperation;
import com.example.bffshare.api.security.authentication.AuthenticationRequest;
import com.example.bffshare.api.security.authentication.AuthenticationResponse;
import com.example.bffshare.core.mergeresponse.security.JWTMultipleMethods.GetJWTUserName;
import com.example.bffshare.persistence.entity.User;
import com.example.bffshare.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticateOperationProcessor implements AuthenticationOperation {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final GetJWTUserName getJWTUserName;

    @Override
    public AuthenticationResponse process(AuthenticationRequest operationInput) throws Exception {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        operationInput.getEmail(),
                        operationInput.getPassword()
                )
        );
        User user  = userRepository.findByEmail(operationInput.getEmail())
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));

        System.out.println(user.getEmail());

        String jwtToken = getJWTUserName.generateToken(user);
        return AuthenticationResponse.builder()
                                                .token(jwtToken)
                                                .build();
    }
}
