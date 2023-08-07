package com.example.bffshare.core.mergeresponse.cartprocessors.viewcart;

import com.example.bffshare.api.cart.viewallitems.ViewAllOperation;
import com.example.bffshare.api.cart.viewallitems.ViewCartRequest;
import com.example.bffshare.api.cart.viewallitems.ViewCartResponse;
import com.example.bffshare.persistence.entity.User;
import com.example.bffshare.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewCartOperationProcessor implements ViewAllOperation {

    private final UserRepository userRepository;
    @Override
    public ViewCartResponse process(ViewCartRequest operationInput) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));

        return ViewCartResponse.builder()
                .userId(currentUser.getUuid())
                .items(currentUser.getCart().getItemMap())
                .cartPrice(currentUser.getCart().getSumPrice())
                .build();
    }
}
