package com.example.bffshare.core.mergeresponse.cartprocessors.purchase;

import com.example.bffshare.api.cart.emptycart.EmptyCartContentsRequest;
import com.example.bffshare.api.cart.purchase.PurchaseOperation;
import com.example.bffshare.api.cart.purchase.PurchaseRequest;
import com.example.bffshare.api.cart.purchase.PurchaseResult;
import com.example.bffshare.core.mergeresponse.cartprocessors.emptycart.EmptyCartOperationProcessor;
import com.example.bffshare.persistence.entity.Cart;
import com.example.bffshare.persistence.entity.User;
import com.example.bffshare.persistence.repository.CartRepository;
import com.example.bffshare.persistence.repository.UserRepository;
import com.example.storageservice.api.purchase.cartpurchase.StoragePurchaseRequest;
import com.example.storageservice.restexport.ZooStorageRestExport;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PurchaseOperationProcessor implements PurchaseOperation {

     private  final ZooStorageRestExport zooStorageRestExport;
     private  final   EmptyCartOperationProcessor emptyCartOperationProcessor;
     private final UserRepository userRepository;
    @Override
    public PurchaseResult process(PurchaseRequest purchaseRequest) throws Exception {

        User user = userRepository.findById(purchaseRequest.getCurrentUserId()).
                orElseThrow(()-> new UsernameNotFoundException("No such user"));


        StoragePurchaseRequest storagePurchaseRequest = StoragePurchaseRequest.builder()
                .userId(user.getUuid())
                        .items(user.getCart().getItemMap())
                                .cartId(user.getCart().getCartId())
                                        .sumPrice(user.getCart().getSumPrice()).build();


       zooStorageRestExport.purchase(storagePurchaseRequest);

       emptyCartOperationProcessor.process(EmptyCartContentsRequest.builder().userId(user.getUuid()).build());


        return PurchaseResult.builder().status("Success").build();
    }
}
