package com.example.bffshare.core.mergeresponse.cartprocessors.addItem;

import com.example.bffshare.api.cart.additem.AddItemToCartOperation;
import com.example.bffshare.api.cart.additem.AddItemToCartRequest;
import com.example.bffshare.api.cart.additem.AddItemToCartResponse;
import com.example.bffshare.persistence.entity.User;
import com.example.bffshare.persistence.repository.UserRepository;
import com.example.storageservice.api.Item.getItem.ItemResponse;
import com.example.storageservice.restexport.ZooStorageRestExport;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddItemOperationProcessor implements AddItemToCartOperation {


    private final UserRepository userRepository;
    private final ZooStorageRestExport zooStorageRestExport;

    @Override
    public AddItemToCartResponse process(AddItemToCartRequest addItemToCartRequest) {

        double sumprice = 0;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));

        if (currentUser.getCart().getItemMap().containsKey(addItemToCartRequest.getItemId()))
        {
            currentUser.getCart().getItemMap()
                    .put(addItemToCartRequest.getItemId(),currentUser.getCart().getItemMap()
                            .get(addItemToCartRequest.getItemId()) + addItemToCartRequest.getQuantity());
        }

        currentUser.getCart().getItemMap()
                .put(addItemToCartRequest.getItemId(),addItemToCartRequest.getQuantity());


        for (Map.Entry<UUID,Integer> entry : currentUser.getCart().getItemMap().entrySet()) {

            ItemResponse item = zooStorageRestExport.getStorageItemById(String.valueOf(entry.getKey()));
            sumprice = sumprice + entry.getValue()* item.getPrice();
        }

        currentUser.getCart().setSumPrice(sumprice);
        userRepository.save(currentUser);


        return AddItemToCartResponse.builder()
                .userId(currentUser.getUuid())
                .items(currentUser.getCart().getItemMap())
                .cartPrice(sumprice)
                .build();
    }
}
