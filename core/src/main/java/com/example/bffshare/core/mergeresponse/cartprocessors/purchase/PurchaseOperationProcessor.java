package com.example.bffshare.core.mergeresponse.cartprocessors.purchase;

import com.example.bffshare.api.cart.emptycart.EmptyCartContentsRequest;
import com.example.bffshare.api.cart.purchase.PurchaseOperation;
import com.example.bffshare.api.cart.purchase.PurchaseRequest;
import com.example.bffshare.api.cart.purchase.PurchaseResult;
import com.example.bffshare.core.mergeresponse.cartprocessors.emptycart.EmptyCartOperationProcessor;
import com.example.bffshare.persistence.entity.User;
import com.example.bffshare.persistence.repository.UserRepository;
import com.example.storageservice.api.purchase.cartpurchase.StoragePurchaseRequest;
import com.example.storageservice.restexport.ZooStorageRestExport;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


 /* Checks the user, builds a purchase request and sends it to Storage service,
 where the purchase itself is executed, if the operation is successful,
 the user's cart is emptied. */

@Service
@RequiredArgsConstructor
public class PurchaseOperationProcessor implements PurchaseOperation {

     private  final ZooStorageRestExport zooStorageRestExport;
     private  final EmptyCartOperationProcessor emptyCartOperationProcessor;
     private  final UserRepository userRepository;
    @Override
    public PurchaseResult process(PurchaseRequest purchaseRequest) throws Exception {

        User user = userRepository.findById(purchaseRequest.getCurrentUserId()).
                orElseThrow(()-> new UsernameNotFoundException("No such user"));


        StoragePurchaseRequest storagePurchaseRequest = StoragePurchaseRequest.builder()
                        .userId(user.getUuid())
                        .items(user.getCart().getItemMap())
                        .cartId(user.getCart().getCartId())
                        .sumPrice(user.getCart().getSumPrice())
                        .build();


        String status = zooStorageRestExport.purchase(storagePurchaseRequest).getStatus();

        if (status.equals("ERR")){
            return PurchaseResult.builder().status("err").build();
        }

        emptyCartOperationProcessor.process(EmptyCartContentsRequest
                                            .builder().userId(user.getUuid()).build());

        return PurchaseResult.builder().status(status).build();
    }
}
