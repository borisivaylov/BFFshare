package com.example.bffshare.api.cart.emptycart;

import com.example.bffshare.api.base.OperationResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Map;
import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmptyCartContentsResponse implements OperationResult {

    private UUID userId;
    private UUID cartId;
    private Map<UUID,Integer> items;
    private double price;
}
