package com.example.bffshare.api.cart.removefromcart;

import com.example.bffshare.api.base.OperationInput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemoveItemRequest implements OperationInput {
    private  UUID itemId;
}
