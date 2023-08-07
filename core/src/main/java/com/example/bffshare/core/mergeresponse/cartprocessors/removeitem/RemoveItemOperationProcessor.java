package com.example.bffshare.core.mergeresponse.cartprocessors.removeitem;

import com.example.bffshare.api.cart.removefromcart.RemoveItemOperation;
import com.example.bffshare.api.cart.removefromcart.RemoveItemRequest;
import com.example.bffshare.api.cart.removefromcart.RemoveItemResponse;
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
public class RemoveItemOperationProcessor implements RemoveItemOperation {

    private final UserRepository userRepository;
    private final ZooStorageRestExport zooStorageRestExport;
    @Override
    public RemoveItemResponse process(RemoveItemRequest removeItemRequest) throws Exception {

        double sumprice = 0;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByEmail(authentication.getName())
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));

        currentUser.getCart().getItemMap().remove(removeItemRequest.getItemId());

        for (Map.Entry<UUID,Integer> entry : currentUser.getCart().getItemMap().entrySet()) {

            ItemResponse item = zooStorageRestExport.getStorageItemById(String.valueOf(entry.getKey()));
            sumprice = sumprice + entry.getValue()* item.getPrice();
        }
        currentUser.getCart().setSumPrice(sumprice);
        userRepository.save(currentUser);

        return RemoveItemResponse.builder()
                .userId(currentUser.getUuid())
                .items(currentUser.getCart().getItemMap())
                .cartPrice(currentUser.getCart().getSumPrice())
                .build();
    }
}
