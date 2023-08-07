package com.example.bffshare.api.cart.purchase;

import com.example.bffshare.api.base.OperationInput;
import com.example.bffshare.persistence.entity.User;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequest implements OperationInput {

    private UUID currentUserId;

}
