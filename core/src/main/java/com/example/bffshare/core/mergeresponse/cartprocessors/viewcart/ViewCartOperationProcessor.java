package com.example.bffshare.core.mergeresponse.cartprocessors.viewcart;

import com.example.bffshare.api.cart.viewallitems.ViewAllOperation;
import com.example.bffshare.api.cart.viewallitems.ViewCartRequest;
import com.example.bffshare.api.cart.viewallitems.ViewCartResponse;
import com.example.bffshare.persistence.entity.User;
import com.example.bffshare.persistence.repository.UserRepository;
import com.example.storageservice.api.Item.getItem.ItemResponse;
import com.example.storageservice.restexport.ZooStorageRestExport;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Recalculates cart price and returns full information of it

@Service
@RequiredArgsConstructor
public class ViewCartOperationProcessor implements ViewAllOperation {

    private final UserRepository userRepository;
    private final ZooStorageRestExport zooStorageRestExport;
    @Override
    public ViewCartResponse process(ViewCartRequest operationInput) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByEmail(authentication.getName()).orElseThrow(()-> new UsernameNotFoundException("user not found"));

        currentUser.getCart().setSumPrice(0);

        currentUser.getCart().getItemMap().forEach((key, value)-> {
            ItemResponse itemResponse = zooStorageRestExport.getStorageItemById(key.toString());
                currentUser.getCart().setSumPrice(currentUser.getCart().getSumPrice() + itemResponse.getPrice());
        });

        return ViewCartResponse.builder()
                .userId(currentUser.getUuid())
                .items(currentUser.getCart().getItemMap())
                .cartPrice(currentUser.getCart().getSumPrice())
                .build();
    }
}
