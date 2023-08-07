package com.example.bffshare.api.cart.purchase;

import com.example.bffshare.api.base.OperationResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseResult implements OperationResult {

    @JsonProperty("Purchase status:")
    private String status;
}
