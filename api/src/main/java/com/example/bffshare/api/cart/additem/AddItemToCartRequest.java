package com.example.bffshare.api.cart.additem;

import com.example.bffshare.api.base.OperationInput;
import com.example.bffshare.api.base.OperationProcessor;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddItemToCartRequest implements OperationInput {

    @JsonProperty("itemId")
    private UUID itemId;
    @JsonProperty("quantity")
    private Integer quantity;
}
