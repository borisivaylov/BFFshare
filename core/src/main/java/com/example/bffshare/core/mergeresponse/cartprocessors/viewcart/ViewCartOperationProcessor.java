package com.example.bffshare.core.mergeresponse.cartprocessors.viewcart;

import com.example.bffshare.api.cart.viewallitems.ViewAllOperation;
import com.example.bffshare.api.cart.viewallitems.ViewCartRequest;
import com.example.bffshare.api.cart.viewallitems.ViewCartResponse;
import com.example.bffshare.persistence.entity.User;
import com.example.bffshare.persistence.repository.UserRepository;
import com.example.storageservice.persistence.repository.OnSaleItemRepository;
import com.example.storageservice.persistence.repository.ShipmentRepository;
import com.example.storageservice.restexport.ZooStorageRestExport;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ViewCartOperationProcessor implements ViewAllOperation {

    private final UserRepository userRepository;
   // private final OnSaleItemRepository onSaleItemRepository;
    //private final ShipmentRepository shipmentRepository;
    private final ZooStorageRestExport zooStorageRestExport;
    @Override
    public ViewCartResponse process(ViewCartRequest operationInput) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByEmail(authentication.getName()).orElseThrow(()-> new UsernameNotFoundException("user not found"));

        System.out.println(currentUser.getCart().getSumPrice());

        currentUser.getCart().getItemMap().forEach((key, value)-> {

            if (zooStorageRestExport.onSaleResult(key).isOnSale()) {
                double actualPrice = zooStorageRestExport.getItemByIdReference(key).getPrice();
                Integer discount = zooStorageRestExport.getItemDiscount(key).getDiscount();
                double onSalePrice = (actualPrice * (1 - discount / 100)) * value;

                currentUser.getCart().setSumPrice(currentUser.getCart().getSumPrice() - (actualPrice - onSalePrice));
            }
        });

        return ViewCartResponse.builder()
                .userId(currentUser.getUuid())
                .items(currentUser.getCart().getItemMap())
                .cartPrice(currentUser.getCart().getSumPrice())
                .build();
    }
}
