package com.example.bffshare.api.cart.emptycart;

import com.example.bffshare.api.base.OperationInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmptyCartContentsRequest implements OperationInput {

    @JsonProperty("userId")
    private UUID userId;
}
