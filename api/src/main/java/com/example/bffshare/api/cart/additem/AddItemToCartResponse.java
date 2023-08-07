package com.example.bffshare.api.cart.additem;

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
public class AddItemToCartResponse implements OperationResult {

    @JsonProperty("userId")
    private UUID userId;
    @JsonProperty("cartContents")
    private Map<UUID,Integer> items;
    @JsonProperty("cartPrice")
    private double cartPrice;
}
