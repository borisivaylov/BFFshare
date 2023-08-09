package com.example.bffshare.api.item.getallitemsparallel;

import com.example.bffshare.api.base.OperationInput;
import com.example.bffshare.api.base.OperationResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllItemsParallelResult implements OperationResult {

    @JsonProperty
    private UUID itemId;
    @JsonProperty
    private long quantity;
    @JsonProperty
    private double price;
}
