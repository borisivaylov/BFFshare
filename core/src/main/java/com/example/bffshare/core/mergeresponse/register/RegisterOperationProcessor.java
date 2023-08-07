package com.example.bffshare.core.mergeresponse.register;

import com.example.bffshare.api.security.register.RegisterOperation;
import com.example.bffshare.api.security.register.RegisterRequest;
import com.example.bffshare.api.security.register.RegisterResponse;
import com.example.bffshare.core.mergeresponse.JWTMultipleMethods.GetJWTUserName;
import com.example.bffshare.persistence.entity.Cart;
import com.example.bffshare.persistence.entity.Roles;
import com.example.bffshare.persistence.entity.User;
import com.example.bffshare.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterOperationProcessor implements RegisterOperation {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final GetJWTUserName getJWTUserName;
    @Override
    public RegisterResponse process(RegisterRequest registerRequest) throws Exception {
        User user = User.builder()
                .userName(registerRequest.getUserName())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .phone(registerRequest.getPhone())
                .cart(new Cart())
                .role(Roles.CUSTOMER)
                .build();
        userRepository.save(user);

        String jwtToken = getJWTUserName.generateToken(user);
        return RegisterResponse.builder().jwt(jwtToken).build();
    }
}
