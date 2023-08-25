package com.example.bffshare.core.mergeresponse.cartprocessors.emptycart;

import com.example.bffshare.api.cart.emptycart.EmptyCartContentsOperation;
import com.example.bffshare.api.cart.emptycart.EmptyCartContentsRequest;
import com.example.bffshare.api.cart.emptycart.EmptyCartContentsResponse;
import com.example.bffshare.persistence.entity.User;
import com.example.bffshare.persistence.repository.UserRepository;
import com.example.storageservice.restexport.ZooStorageRestExport;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Empty all cart contents of the logged user

@Service
@RequiredArgsConstructor
public class EmptyCartOperationProcessor implements EmptyCartContentsOperation {

    private final UserRepository userRepository;
    @Override
    public EmptyCartContentsResponse process(EmptyCartContentsRequest operationInput) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));

        currentUser.getCart().getItemMap().clear();
        currentUser.getCart().setSumPrice(0);
        userRepository.save(currentUser);

        return EmptyCartContentsResponse.builder()
                .cartId(currentUser.getCart().getCartId())
                .userId(currentUser.getUuid())
                .items(currentUser.getCart().getItemMap())
                .price(currentUser.getCart().getSumPrice())
                .build();
    }
}
