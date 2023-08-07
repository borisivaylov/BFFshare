package com.example.bffshare.api.cart.viewallitems;

import com.example.bffshare.api.base.OperationResult;
import lombok.*;

import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ViewCartResponse implements OperationResult {

    private UUID userId;
    private Map<UUID,Integer> items;
    private double cartPrice;
}
