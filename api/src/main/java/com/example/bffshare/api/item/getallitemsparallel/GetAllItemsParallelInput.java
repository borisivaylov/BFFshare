package com.example.bffshare.api.item.getallitemsparallel;

import com.example.bffshare.api.base.OperationInput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class GetAllItemsParallelInput implements OperationInput {
    @JsonProperty
    private String test;

}
