package com.example.bffshare.api.cart.viewallitems;

import com.example.bffshare.api.base.OperationInput;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewCartRequest implements OperationInput {

    private UUID userId;

}
