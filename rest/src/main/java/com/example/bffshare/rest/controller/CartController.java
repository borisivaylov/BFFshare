package com.example.bffshare.rest.controller;

import com.example.bffshare.api.cart.additem.AddItemToCartRequest;
import com.example.bffshare.api.cart.additem.AddItemToCartResponse;
import com.example.bffshare.api.cart.emptycart.EmptyCartContentsRequest;
import com.example.bffshare.api.cart.emptycart.EmptyCartContentsResponse;
import com.example.bffshare.api.cart.purchase.PurchaseRequest;
import com.example.bffshare.api.cart.purchase.PurchaseResult;
import com.example.bffshare.api.cart.removefromcart.RemoveItemRequest;
import com.example.bffshare.api.cart.removefromcart.RemoveItemResponse;
import com.example.bffshare.api.cart.viewallitems.ViewCartRequest;
import com.example.bffshare.api.cart.viewallitems.ViewCartResponse;
import com.example.bffshare.core.mergeresponse.cartprocessors.addItem.AddItemOperationProcessor;
import com.example.bffshare.core.mergeresponse.cartprocessors.emptycart.EmptyCartOperationProcessor;
import com.example.bffshare.core.mergeresponse.cartprocessors.purchase.PurchaseOperationProcessor;
import com.example.bffshare.core.mergeresponse.cartprocessors.removeitem.RemoveItemOperationProcessor;
import com.example.bffshare.core.mergeresponse.cartprocessors.viewcart.ViewCartOperationProcessor;
import com.example.bffshare.persistence.entity.User;
import com.example.bffshare.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final AddItemOperationProcessor addItemOperationProcessor;
    private final RemoveItemOperationProcessor removeItemOperationProcessor;
    private final ViewCartOperationProcessor viewCartOperationProcessor;
    private final EmptyCartOperationProcessor emptyCartOperationProcessor;
    private final PurchaseOperationProcessor purchaseOperationProcessor;

    @PostMapping("/addItem")
    AddItemToCartResponse addItem(@RequestBody AddItemToCartRequest addItemToCartRequest){
        return addItemOperationProcessor.process(addItemToCartRequest);
    }
    @PostMapping("/removeItem/{uuid}")
    RemoveItemResponse removeItem(@PathVariable UUID uuid) throws Exception {
        return removeItemOperationProcessor.process(RemoveItemRequest.builder().itemId(uuid).build());
    }
    @PostMapping("/view")
    ViewCartResponse getCart(@AuthenticationPrincipal User currentUser) throws Exception {
        return viewCartOperationProcessor.process(ViewCartRequest.builder().userId(currentUser.getUuid()).build());
    }

    @PostMapping("/empty")
    EmptyCartContentsResponse emptyCart(@AuthenticationPrincipal User currentUser) throws Exception {
        return emptyCartOperationProcessor.process(EmptyCartContentsRequest.builder().userId(currentUser.getUuid()).build());
    }

    @PostMapping ("/emptybff")
    EmptyCartContentsResponse emptyCartBff(UUID uuid) throws Exception {
        return  emptyCartOperationProcessor.process(EmptyCartContentsRequest.builder().userId(uuid).build());
    }
    @PostMapping("/purchase")
    PurchaseResult purchase(@AuthenticationPrincipal User user) throws Exception {
        return purchaseOperationProcessor.process(PurchaseRequest.builder().currentUserId(user.getUuid()).build());
    }



}
